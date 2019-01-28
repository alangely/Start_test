// SAXExample.java 
// Example of using SAX for XML parsing
//
// 11 October 2004 
// 2007 1st semister 

 
 
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
 

public class SAXCoffee extends DefaultHandler {
	
	boolean isPrice; 
	float totalPrice = (float)0.0;
	
	// SAX ContentHandler methods =====================================
	
	/* Receive notice of start of document. */
	public void startDocument() throws SAXException {
		System.out.println("startDocument");
		isPrice = false;
		
	}

	/* Receive notice of end of document. */
	public void endDocument() throws SAXException {
		System.out.println("endDocument \n" + "Total Price = " +  totalPrice);
		
	}
	
	/* Receive notice of start of XML element. */
	public void startElement (String namespaceURI,
							  String localName,
							  String qName, 
							  Attributes atts)
	{
		System.out.println("Start Element:   " + qName);
		if (qName.equals("price")) { // 엘리먼트의 값이 price면 true가 된다.
			isPrice = true;
		}  
			
	}

	/* Receive notice of end of XML element. */
	public void endElement(String namespaceURI, 
						   String localName,
						   String qName)
	throws SAXException {
		System.out.println("End Element:   " + qName);
		if (qName.equals("price")) {
			isPrice = false;
		}
	}

	/* Receive notice of character data (text not in an XML element). */
	public void characters (char[] ch, int start, int length)
	// 엘리먼트의 내용은 characters라는 메소드가 호출될때 출력된다. 
	// trim()은 공백을 제거해줄때 사용
	// isPrice = 분류형 변수 
	// 커피의 가격 정보를 얻어옴 
	// 엘리먼트의 내용만 만나면 Characters를 불러옴 
	// 캐릭터스로 들어왔다는 것은 price라는 엘리먼트를 들어왔다는 것을 의미함. isPrice는 True로 바꿈 
	// 11.95는 IsPrice가 True이기 때문에 11.95는 커피의 가격정보이다는 것을 알 수 있다. 
	// Characters-> 엘리먼트의 내용이 커피 이름인지 비용인지를 알아야되며, 
	throws SAXException {
		String s = new String(ch, start, length);
		s = s.trim();
		
		if (isPrice ==true) {
		   float price = Float.parseFloat(s);
		   totalPrice = totalPrice + price;
		}

		
	}	// end SAX ContentHandler methods =================================
	
	/** Test by running "java SAXExample <xmlfile>" */
	public static void main (String[] args) {
		if (args.length != 1) {
			System.err.println ("Usage: cmd filename");
			System.exit(1);
		}
		try {
			SAXCoffee SAXHandler = new SAXCoffee();
			try {
				 
				//SAX 1 approach:
				SAXParserFactory factory = SAXParserFactory.newInstance();
				SAXParser saxParser = factory.newSAXParser();
				saxParser.parse(args[0], SAXHandler);

				 
			} catch (Exception e) {
				e.printStackTrace();
			}
		 			
		} catch (Throwable t) {
			t.printStackTrace ();
		}
	}
	
}
