import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class xml_porject {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringElementContentWhitespace(true);
		factory.setValidating(true);
		
		//��ü ����
		DB_Conn dc = new DB_Conn();
		
		String Name = "";
		String Copy = "";
		String Licence = "";
		String Use = "";
		
		int sum=0;
		int cate;
		
		try {
		//����ó�� �ʼ�
		DocumentBuilder builder = factory.newDocumentBuilder();
		//�Ľ��� xml ���� �̸� ����
		Document document = builder.parse("sw_list.xml");
		
	
		//1. ������Ʈ �о����
		//��Ʈ ������Ʈ
		Element eRoot = document.getDocumentElement();
		
		NodeList nlswlist = eRoot.getElementsByTagName("sw_list");
		int items = nlswlist.getLength();
		
		for(int i=0; i<items; i++) {
			Element esw = (Element) nlswlist.item(i);
			//2�� ������Ʈ(ó�� - ���� - ���� - ������ ����)
			Element eName = (Element) esw.getFirstChild();
			Element eCopy = (Element) eName.getNextSibling();
			Element eLicence = (Element) eName.getNextSibling().getNextSibling();
			Element eUse = (Element) esw.getLastChild();
			Text tName = (Text) eName.getFirstChild();
			Text tCopy = (Text) eCopy.getFirstChild();
			Text tLicence = (Text) eLicence.getFirstChild();
			Text tUse = (Text) eUse.getFirstChild();
			Name = tName.getData();
			Copy = tCopy.getData();
			Licence = tLicence.getData();
			Use = tUse.getData();
			
			//select

			dc.select("select * from swdb where duse= " + "'" + Use + "'");
			dc.rs.beforeFirst();
			
			while(dc.rs.next()) {
				sum += 1;
			}
			
			switch(Use) {
				case "develop" :
					cate = 10000;
					break;
				case "game" :
					cate = 20000;
					break;
				case "graphic" :
					cate = 30000;
					break;
				case "network" :
					cate = 40000;
					break;
				case "database" :
					cate = 50000;
					break;
				case "multimedia" :
					cate = 60000;
					break;
				case "security" :
					cate = 70000;
					break;
				case "server" :
					cate = 80000;
					break;
				case "activeX" :
					cate = 90000;
					break;
				case "utility" :
					cate = 100000;
					break;
				case "office" :
					cate = 110000;
					break;
				case "publication" :
					cate = 120000;
					break;
				case "analysis" :
					cate = 130000;
					break;
				case "economic" :
					cate = 140000;
					break;
				default :
					cate = 990000;
					break;
			}
			
			//update
			dc.update("insert into swdb values(" + (cate+sum) + ", '"  + Name + "','" + Copy + "','" + Licence + "','"  + Use +"')");
			sum = 0;
			cate = 0;
		}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
						
		//���� ����
		dc.close();
		
	}

}
