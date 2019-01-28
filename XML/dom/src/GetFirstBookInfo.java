import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;


public class GetFirstBookInfo {
	
	 
	 
	public static void main(String args[]) throws Exception
	{
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringElementContentWhitespace(true);
		factory.setValidating(true);
		DocumentBuilder builder = factory.newDocumentBuilder();	// ���� ó�� �ʿ� 
		
		// XML ���� �Ľ� 
		Document document = builder.parse("book_list.xml");
		
		// ��Ʈ ������Ʈ ���� ��� 
		Element eRoot = document.getDocumentElement();
		
                // ù���� å�� ���� ���
 		Element eBook = (Element) eRoot.getFirstChild();
 		Element eTitle = (Element) eBook.getFirstChild();
 		Text tTitle = (Text) eTitle.getFirstChild();
 		String title = tTitle.getData();
 		System.out.println(title);
 		 
		 
	}
	
}