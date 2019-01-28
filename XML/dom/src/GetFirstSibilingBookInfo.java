import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/* 첫번쨰 책의 저자정보
 * 
 */
public class GetFirstSibilingBookInfo {
	public static void main(String args[]) throws Exception
	{
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringElementContentWhitespace(true);
		factory.setValidating(true);
		DocumentBuilder builder = factory.newDocumentBuilder();	// 예외 처리 필요 
		
		// XML 문서 파싱 
		Document document = builder.parse("book_list.xml");
		
		// 루트 엘리먼트 참조 얻기 
		Element eRoot = document.getDocumentElement();
 		Element eBook = (Element) eRoot.getFirstChild();
 		
 		Element eTitle = (Element) eBook.getFirstChild();
 		Element eAuthor = (Element) eTitle.getNextSibling();
 		Text tAuthor = (Text) eAuthor.getFirstChild();
 		String title = tAuthor.getData();
 		System.out.println(title);
	}
	
}