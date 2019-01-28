import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

   
public class kmaWeather {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 
	
	Document doc = null;
	List <weather> weatherList = new ArrayList<weather>();
	CityAggregator  cityAggr;  
	
	private  void buildCityTemp()
	{

		  URL kmaURL;
		  cityAggr = new CityAggregator();
		 
	      try {
			 kmaURL = new URL("http://www.kma.go.kr/XML/weather/sfc_web_map.xml");
		     InputStream in;
			 in  = kmaURL.openStream();
			 DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			 
			 DocumentBuilder db = dbf.newDocumentBuilder(); 
			 doc = db.parse(in); 
			 in.close(); 
		     if(doc != null){
		    	   NodeList list = doc.getElementsByTagName("local");
		    	   for (int k = 0; k < list.getLength(); k++){
		    		   cityAggr.addWeatherList((Element)list.item(k), weatherList) ;
		    	   }
		     } // if
		    	  
	      } 
          catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		  }catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		  } catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
	      } catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	// 명령어 라인으로 부터 전달받은 지역명의 날씨정보를 제공함 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		CityAggregator cityAggr = new CityAggregator();
		List <weather> weatherList = new ArrayList<weather>();
		
		kmaWeather kma = new kmaWeather();
		kma.buildCityTemp();
		String city = args[0]; 
				 
		weather w = (weather) cityAggr.getWeatherByCity(city, kma.weatherList);
		 
		
		if (w != null) {
 				System.out.print(w.toXML());
 				w.toDOM();
		}
		else {
				System.out.print("<result>there is no weather infomation" + weatherList.size() +" </result>");
				
		}
	}
	
}

 