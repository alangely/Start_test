import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;


public class NsfmlUser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
		DocumentBuilderFactory factory3 = DocumentBuilderFactory.newInstance();
		factory3.setIgnoringElementContentWhitespace(true);
		factory3.setValidating(true);	
		
		DB_Conn db = new DB_Conn();

		String id ="";
		String pw ="";
		String name =""; 
		String phone ="";
		String email ="";
		String Master ="";
		
		int sum=0;
		int sw;

		try {
		DocumentBuilder builder = factory3.newDocumentBuilder();
		//파싱할 xml 문서 이름 설정
		Document document = builder.parse("NSFML_User.xml");
		

		Element eRoot = document.getDocumentElement();
		NodeList User = eRoot.getElementsByTagName("User_list");
		
		System.out.println("test");
		int items = User.getLength();
		for(int i=0; i<items; i++)
		{
			
			Element eUser = (Element) User.item(i);
			//2층 엘리먼트(처음 - 이전 - 다음 - 마지막 읽음)
			Element eid = (Element) eUser.getFirstChild();
			Element epw = (Element) eid.getNextSibling();
			Element ename = (Element) eid.getNextSibling().getNextSibling();
			Element ephone = (Element) eid.getNextSibling().getNextSibling().getNextSibling();
			Element eemail = (Element) eid.getNextSibling().getNextSibling().getNextSibling().getNextSibling();
			Element eMaster = (Element) eUser.getLastChild();
				
			
			Text tid= (Text) eid.getFirstChild();
			Text tpw = (Text) epw.getFirstChild();
			Text tname = (Text) ename.getFirstChild();
			Text tphone = (Text) ephone.getFirstChild();
			Text temail = (Text) eemail.getFirstChild();
			Text tMaster = (Text) eMaster.getFirstChild();
			
			id = tid.getData();
			pw = tpw.getData();
			name = tname.getData();
			phone = tphone.getData();
			email = temail.getData();
			Master = tMaster.getData();
			
			
			db.select("select * from nsfml where name= " + "'" + name + "'");
			db.rs.beforeFirst();
			
			while(db.rs.next()) {
				sum += 1;
			}
				
				switch(name) {
					case "송희재" :
						sw = 10000;
						break;
					case "송군호" :
						sw = 20000;
						break;
					case "김강현" :
						sw = 30000;
						break;
					case "장준영" :
						sw = 40000;
						break;
					case "공도일" :
						sw = 50000;
						break;
				}
				//update
				db.update("insert into nsfml values('"  + id + "', '" + pw + "', '" + name + "', '"  + phone +"', '"  + email +"',  '"+  Master +"')");
				sum = 0;
				sw = 0;
			}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
							
			//연결 해제
			db.close();
			
			
		}
	}

