import java.util.Iterator;
import java.util.List;

   
public class CityAggregator {
	
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
}
