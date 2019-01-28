// SAXExample.java 
// Example of using SAX for XML parsing
//
// 11 October 2004 
// 2007 1st semister 

 

import java.io.InputStream;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
 

public class KmaSAX extends DefaultHandler {
	boolean isLocal; 
	String ta;
	String desc;
	
	 
	/** Read XML from input stream and parse, generating SAX events */
	public void readXML(InputStream in) {
		try {
			 
			//SAX 1 approach:
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			XMLReader xr = saxParser.getXMLReader();
			
			xr.setContentHandler(this);
			xr.parse(new InputSource(in));

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// SAX ContentHandler methods =====================================
	
	/* Receive notice of start of document. */
	public void startDocument() throws SAXException {
		isLocal = false;
	}

	/* Receive notice of end of document. */
	public void endDocument() throws SAXException {
	}
	
	/* Receive notice of start of XML element. */
	public void startElement (String namespaceURI,
							  String localName,
							  String qName, 
							  Attributes atts)
	{
		
		if (qName.equals("local")) {
			 ta = atts.getValue("ta"); // ta 속성정보값
			 desc = atts.getValue("desc"); // desc 속성정보값
			 isLocal =true;   	 // Local엘리먼트에 들어왔으며 true로 만들어준다.
		}  
			
	}

	/* Receive notice of end of XML element. */
	public void endElement(String namespaceURI, 
						   String localName,
						   String qName)
	throws SAXException {
	
		if (qName.equals("local")) {
			isLocal = false;
		}
	}

	/* Receive notice of character data (text not in an XML element). */
	public void characters (char[] ch, int start, int length)
	throws SAXException {
		String s = new String(ch, start, length);
		s = s.trim();
		weather w = new weather();
		if (isLocal==true) {
			w.setDesc(desc);
			w.setTemp(Double.parseDouble(ta));
			w.setCity(s);
			 // 지역정보를 얻어오고, 현재 바로전에 얻은 ta, desc라는 기후정보를 배열에다가 넣을 예정입니다. 
		    KMAWeather.weatherList.add(w);
		    
		}
		
		
	}	// end SAX ContentHandler methods =================================
	
	
}
