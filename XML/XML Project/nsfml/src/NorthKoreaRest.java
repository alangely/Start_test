import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class NorthKoreaRest {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
		DocumentBuilderFactory factory2 = DocumentBuilderFactory.newInstance();
		factory2.setIgnoringElementContentWhitespace(true);
		factory2.setValidating(true);	
		
		DB_Conn db = new DB_Conn();

		String North_Korea_img ="";
		String North_Korea_name ="";
		String North_Korea_location =""; 
		String North_Korea_menu ="";
		String North_Korea_number ="";
		String North_Korea_Explanation ="";
		
		int sum=0;
		int sw;

		try {
		DocumentBuilder builder = factory2.newDocumentBuilder();
		//�Ľ��� xml ���� �̸� ����
		Document document = builder.parse("NSFML.xml");
		

		Element eRoot = document.getDocumentElement();
		NodeList NorthKrest = eRoot.getElementsByTagName("North_Korean_rest");
		
		System.out.println("test");
		int tems = NorthKrest.getLength();
		for(int i=0; i<tems; i++)
		{
			
			Element eNKrest = (Element) NorthKrest.item(i);
			//2�� ������Ʈ(ó�� - ���� - ���� - ������ ����)
			Element eNorth_Korea_img = (Element) eNKrest.getFirstChild();
			Element eNorth_Korea_name = (Element) eNorth_Korea_img.getNextSibling();
			Element eNorth_Korea_location = (Element) eNorth_Korea_img.getNextSibling().getNextSibling();
			Element eNorth_Korea_menu = (Element) eNorth_Korea_img.getNextSibling().getNextSibling().getNextSibling();
			Element eNorth_Korea_number = (Element) eNorth_Korea_img.getNextSibling().getNextSibling().getNextSibling().getNextSibling();
			Element eNorth_Korea_Explanation = (Element) eNKrest.getLastChild();
			
			
			Text tNorth_Korea_img = (Text) eNorth_Korea_img.getFirstChild();
			Text tNorth_Korea_name = (Text) eNorth_Korea_name.getFirstChild();
			Text torth_Korea_location = (Text) eNorth_Korea_location.getFirstChild();
			Text tNorth_Korea_menu = (Text) eNorth_Korea_menu.getFirstChild();
			Text tNorth_Korea_number = (Text) eNorth_Korea_number.getFirstChild();
			Text tNorth_Korea_Explanation = (Text) eNorth_Korea_Explanation.getFirstChild();
			
			North_Korea_img = tNorth_Korea_img.getData();
			North_Korea_name = tNorth_Korea_name.getData();
			North_Korea_location = torth_Korea_location.getData();
			North_Korea_menu = tNorth_Korea_menu.getData();
			North_Korea_number = tNorth_Korea_number.getData();
			North_Korea_Explanation = tNorth_Korea_Explanation.getData();
			
			
			db.select("select * from nkrest where North_Korea_name= " + "'" + North_Korea_name + "'");
			db.rs.beforeFirst();
			
			while(db.rs.next()) {
				sum += 1;
			}
				
				switch(North_Korea_name) {
					case "�ݷ��" :
						sw = 10000;
						break;
					case "�ɶ�" :
						sw = 20000;
						break;
					case "�����" :
						sw = 30000;
						break;
					case "��ȵ�������" :
						sw = 40000;
						break;
					case "���������" :
						sw = 50000;
						break;
					case "������" :
						sw = 60000;
						break;
					case "�ﰢ��" :
						sw = 70000;
						break;
				}
				//update
				db.update("insert into nkrest values('"  + North_Korea_img + "', '" + North_Korea_name + "', '" + North_Korea_location + "', '"  + North_Korea_menu +"', '"+ North_Korea_number +"', '"+ North_Korea_Explanation +"')");
				sum = 0;
				sw = 0;
			}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
							
			//���� ����
			db.close();
			
			
		}
	}
