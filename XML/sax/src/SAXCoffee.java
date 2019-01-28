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
		if (qName.equals("price")) { // ������Ʈ�� ���� price�� true�� �ȴ�.
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
	// ������Ʈ�� ������ characters��� �޼ҵ尡 ȣ��ɶ� ��µȴ�. 
	// trim()�� ������ �������ٶ� ���
	// isPrice = �з��� ���� 
	// Ŀ���� ���� ������ ���� 
	// ������Ʈ�� ���븸 ������ Characters�� �ҷ��� 
	// ĳ���ͽ��� ���Դٴ� ���� price��� ������Ʈ�� ���Դٴ� ���� �ǹ���. isPrice�� True�� �ٲ� 
	// 11.95�� IsPrice�� True�̱� ������ 11.95�� Ŀ���� ���������̴ٴ� ���� �� �� �ִ�. 
	// Characters-> ������Ʈ�� ������ Ŀ�� �̸����� ��������� �˾ƾߵǸ�, 
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
