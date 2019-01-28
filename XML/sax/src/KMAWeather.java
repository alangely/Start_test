import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class KMAWeather {
	
	static List <weather> weatherList = new ArrayList<weather>();
	CityAggregator  cityAggr;  
	
	private  void buildCityTemp()
	{

		  URL kmaURL;
		  cityAggr = new CityAggregator();
		 
	      try {
			 kmaURL = new URL("http://www.kma.go.kr/XML/weather/sfc_web_map.xml");
		     InputStream in;
			 in  = kmaURL.openStream();
			 KmaSAX sax = new KmaSAX();
			 sax.readXML(in); 
			 in.close();   
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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		CityAggregator cityAggr = new CityAggregator();
		KMAWeather kma = new KMAWeather();
		kma.buildCityTemp();
		String city = args[0]; 	
		weather w = (weather) cityAggr.getWeatherByCity(city, weatherList);
		 	
		if (w != null) {
 				System.out.print(w.toXML());
 				w.toDOM();
		}
		else {
				System.out.print("<result>there is no weather infomation" + weatherList.size() +" </result>");
				
		}
	}
	
}

 