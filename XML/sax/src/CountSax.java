import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
 
// xml���� ���� ������Ʈ�� ������ ������ִ� ���α׷��̴�. �� ���� ��쿡�� 8�̳����Եȴ�. 
public class CountSax extends DefaultHandler
{
	int eltCount;
	int eltRectCount;
	int eltCircleCount;
	public void startDocument()
	{
		eltCount =0;
		eltRectCount =0;
		eltCircleCount =0;
	}
	  
	public void startElement(String url, String localName, 
			String qName, Attributes attr)
	{
		eltCount++;
		
		if(qName.equals("rectangle")) // qname�� rectangle�� ��쿡 �簢�� ������Ʈ�� ���� ����ϱ� ���� ���� eltRectCount�� 1�� ���������ش�. 
		{
			eltRectCount++;
		}
		else if(qName.equals("circle"))
		{
			eltCircleCount++;
		}
	}
	public void endDocument()
	{
		System.out.println("��ü ������Ʈ �� : " + eltCount);
		System.out.println("��ü �簢�� ������Ʈ �� : " + eltRectCount);
		System.out.println("��ü �� ������Ʈ �� : " + eltCircleCount);

	}
	public static void main(String args[]) throws ParserConfigurationException, SAXException, IOException {
		
		SAXParserFactory  sf =  SAXParserFactory.newInstance();
		SAXParser sp =  sf.newSAXParser();
		sp.parse(args[0], new CountSax());
		
	}
}