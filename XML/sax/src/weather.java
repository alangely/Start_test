import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

class weather {
	String desc;
	double temp;  
	String city;
	
	String getDesc()
	{
	   return this.desc;	
	}
	void setDesc(String desc)
	{
		this.desc = desc;
	}
	double getTemp()
	{
	   return this.temp;	
	}
	void setTemp(double temp)
	{
		this.temp = temp;
	}
	String getCity()
	{
	   return this.city;	
	}
	void setCity(String city)
	{
		this.city = city;
	}
	public String toXML() {
		String str;
		
		str = "<?xml version=\"1.0\" encoding=\"euc-kr\" standalone=\"no\"?>";
		str += "<weather>" + "<city>" + getCity() + "</city>";
		str += "<desc>" + getDesc() + "</desc>";
		str += "<temp>"+ getTemp()+"</temp>" +"</weather>";
		
		return str;
	}
	public void toDOM() {
		try {
		  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		  DocumentBuilder builder = dbf.newDocumentBuilder();
		  Document doc = builder.newDocument();
		  
		  Element weather = doc.createElement("weather");
		  Element city = doc.createElement("city");
		  Element des = doc.createElement("desc");
		  Element ta = doc.createElement("ta");
		  
		  doc.appendChild(weather);
		  weather.appendChild(city);
		  weather.appendChild(des);
		  weather.appendChild(ta);
		  city.appendChild(doc.createTextNode(getCity()));
		  des.appendChild(doc.createTextNode(getDesc()));
		  ta.appendChild(doc.createTextNode(Double.toString(getTemp())));
		  
		  TransformerFactory factory = TransformerFactory.newInstance();
		  Transformer transformer = factory.newTransformer();
		  DOMSource source = new DOMSource(doc);
		  StreamResult result = new StreamResult(new File("dom2xml.xml"));
		  transformer.transform(source, result);
	    }
		catch(Exception e) { e.printStackTrace(); }
	}
	
}
