import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;


public class XML_Dom {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
			//0. 파싱 처리
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setIgnoringElementContentWhitespace(true);
			factory.setValidating(true);
			try {
			//예외처리 필수
			DocumentBuilder builder = factory.newDocumentBuilder();
			//파싱할 xml 문서 이름 설정
			Document document = builder.parse("NSFML.xml");
			
			
			//1. 엘리먼트 읽어오기
			//루트 엘리먼트
			Element  NorthFood = document.getDocumentElement();
			//루트 하위 엘리먼트(1층)
			Element North_location_name = (Element)  NorthFood.getFirstChild();
			//2층 엘리먼트(처음 - 이전 - 다음 - 마지막 읽음)
			Element North_Food_Recipe = (Element) North_location_name.getFirstChild();
			//Element eTitle = (Element) eBook.getPreviousSibling();			
			//Element eTitle = (Element) eBook.getNextSibling();
			//Element eTitle = (Element) eBook.getLastChild();
			Text tTitle = (Text) North_Food_Recipe.getFirstChild();
			String title = tTitle.getData();
			System.out.println(title);
			
			
			
			//2. eBook 엘리먼트의 속성을 읽음
			//String strAttr = eBook.getAttribute("속성명");

			
			
			//3. NodeList - xml 파일 내 Title 엘리먼트의 값을 전부 읽어옴
			//NodeList nlTitles = eRoot.getElementsByTagName("title");
			//int items = nlTitles.getLength();
			//System.out.println(items);
			//for(int i=0;  i < items; i++)
			//{
			//   Element eTitle1 = (Element) nlTitles.item(i);
			 //  Text tTitle1 = (Text) eTitle1.getFirstChild();
			 //  String title1 = tTitle1.getData();
			 //  System.out.println(title1);
			//}

			
			
			//4. 객체의 생성 및 추가
			//루트 엘리먼트 호출
			Element root = document.getDocumentElement();
			
			//Book 엘리먼트 생성
			//루트 엘리먼트 하위에 배치
			Element newBookNode = document.createElement("Book");
			root.appendChild(newBookNode);
			
			//title 엘리먼트 생성
			//Book 엘리먼트 하위에 배치
			Element titleNode = document.createElement("title");
			newBookNode.appendChild(titleNode);
			
			//title엘리먼트에 [책명] 텍스트 노드 추가 및 삽입
			Text titletextNode = document.createTextNode("책명");
			titleNode.appendChild(titletextNode);
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
