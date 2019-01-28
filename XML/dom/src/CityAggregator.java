import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

   
public class CityAggregator {

	// add  weather information getting from  a set of elements local 
	void addWeatherList(Element element, List <weather> weatherList)
	{
	   
	      weather w = new weather();
	       //a child element to process
	      String cityStr = element.getFirstChild().getNodeValue();
	      String descAttr = element.getAttribute("desc");
	      String taAttr = element.getAttribute("ta");
	      
	      w.city = cityStr;
	      w.desc = descAttr;
	      w.temp = Double.parseDouble(taAttr);
	    
	      weatherList.add(w);
	   
	}
	
    public weather getWeatherByCity(String city, List <weather> weatherList)
	{
		weather w = null;
		 Iterator<weather> iter = weatherList.iterator();
		    while(iter.hasNext())
		    {
		         w= (weather)iter.next();
		        
		         if (city.equals(w.city)) 
		        	 return w;
		        
		    }
		    return null; 
		       
	}
	
	
	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		CityAggregator cityAggr = new CityAggregator();
		List <weather> weatherList = new ArrayList<weather>();
		weather w = new weather();
		w.city = "cheonan";
		w.desc = "cloudy";
		w.temp = 10.4;
		
		 
		weatherList.add(w);
		weather w1 = cityAggr.getWeatherByCity("cheonan",weatherList );
		
		System.out.println("city:" + w1.city + "desc:" + w1.desc + "temp:" + w1.temp);
	  
	}

}
