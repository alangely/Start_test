import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
 
// xml문서 내에 엘리먼트의 개수를 출력해주는 프로그램이다. 즉 답의 경우에는 8이나오게된다. 
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
		
		if(qName.equals("rectangle")) // qname이 rectangle일 경우에 사각형 엘리먼트에 대한 출력하기 위한 변수 eltRectCount를 1씩 증가시켜준다. 
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
		System.out.println("전체 엘리먼트 수 : " + eltCount);
		System.out.println("전체 사각형 엘리먼트 수 : " + eltRectCount);
		System.out.println("전체 원 엘리먼트 수 : " + eltCircleCount);

	}
	public static void main(String args[]) throws ParserConfigurationException, SAXException, IOException {
		
		SAXParserFactory  sf =  SAXParserFactory.newInstance();
		SAXParser sp =  sf.newSAXParser();
		sp.parse(args[0], new CountSax());
		
	}
}