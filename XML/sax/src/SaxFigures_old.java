
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;


class MyContentHandler implements ContentHandler // mycontenthandler�� contenthandler�� ������ ���̸� 
// ��������ʴ��� 11���� �޼ҵ尡 �ٵ��ִ�. 
{

	 
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		 
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endPrefixMapping(String prefix) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processingInstruction(String target, String data) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void skippedEntity(String name) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startDocument() throws SAXException {
		
		// TODO Auto-generated method stub
		System.out.println("XML ���� ����");
		// startDocument�� ȣ���ߴ� -> �� SAX �ļ��� ȣ���ߴ�. 
		
	}

	@Override
	public void startElement(String uri, String localName, String qName /*������Ʈ ���� ����*/,
            Attributes attributes)
	{
		if(qName.equals("circle"))
		{
			String sr = attributes.getValue("r");
			float radius = Float.valueOf(sr).floatValue();
			float area = (float)Math.PI*radius*radius;
			System.out.println("Circle : Radius = " + radius + 
					" Area = " + area);
		 
		}
		if(qName.equals("rectangle"))
		{
			String sh = attributes.getValue("h");
			String sw = attributes.getValue("w");
			float h = Float.valueOf(sh).floatValue();
			float w = Float.valueOf(sw).floatValue();
			
			float area = (float)w*h;
			System.out.println("Rectangle");
			System.out.println("rectangle : " + h * w);
			
			
		}
	}

	@Override
	public void startPrefixMapping(String prefix, String uri) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDocumentLocator(Locator locator) {
		// TODO Auto-generated method stub
		
	}
	
}

public class SaxFigures_old 
{
	   
      public static void main(String[] args)
      {
         try{
        	 SAXParserFactory factory =  SAXParserFactory.newInstance();
        	 SAXParser parser = factory.newSAXParser();
        	
        	 System.out.println("File : " + args[0]);
             XMLReader xmlReader = parser.getXMLReader();
             
             xmlReader.setContentHandler(new MyContentHandler());
             xmlReader.parse(args[0]);
         }
         catch(Exception e){
            e.printStackTrace(System.err);
         }
      }
   }
