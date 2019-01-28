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
	
			//0. �Ľ� ó��
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setIgnoringElementContentWhitespace(true);
			factory.setValidating(true);
			try {
			//����ó�� �ʼ�
			DocumentBuilder builder = factory.newDocumentBuilder();
			//�Ľ��� xml ���� �̸� ����
			Document document = builder.parse("NSFML.xml");
			
			
			//1. ������Ʈ �о����
			//��Ʈ ������Ʈ
			Element  NorthFood = document.getDocumentElement();
			//��Ʈ ���� ������Ʈ(1��)
			Element North_location_name = (Element)  NorthFood.getFirstChild();
			//2�� ������Ʈ(ó�� - ���� - ���� - ������ ����)
			Element North_Food_Recipe = (Element) North_location_name.getFirstChild();
			//Element eTitle = (Element) eBook.getPreviousSibling();			
			//Element eTitle = (Element) eBook.getNextSibling();
			//Element eTitle = (Element) eBook.getLastChild();
			Text tTitle = (Text) North_Food_Recipe.getFirstChild();
			String title = tTitle.getData();
			System.out.println(title);
			
			
			
			//2. eBook ������Ʈ�� �Ӽ��� ����
			//String strAttr = eBook.getAttribute("�Ӽ���");

			
			
			//3. NodeList - xml ���� �� Title ������Ʈ�� ���� ���� �о��
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

			
			
			//4. ��ü�� ���� �� �߰�
			//��Ʈ ������Ʈ ȣ��
			Element root = document.getDocumentElement();
			
			//Book ������Ʈ ����
			//��Ʈ ������Ʈ ������ ��ġ
			Element newBookNode = document.createElement("Book");
			root.appendChild(newBookNode);
			
			//title ������Ʈ ����
			//Book ������Ʈ ������ ��ġ
			Element titleNode = document.createElement("title");
			newBookNode.appendChild(titleNode);
			
			//title������Ʈ�� [å��] �ؽ�Ʈ ��� �߰� �� ����
			Text titletextNode = document.createTextNode("å��");
			titleNode.appendChild(titletextNode);
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
