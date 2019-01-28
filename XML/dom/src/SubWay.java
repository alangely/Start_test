import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class SubWay {
	
	
	static void ListAllStation(Element eRoot) {
		
		
		NodeList nlStatioms = eRoot.getElementsByTagName("station");
		
		System.out.println("*** ��ü �� ***");
		int items = nlStatioms.getLength();
		//System.out.println(items);
		for(int i=0; i<nlStatioms.getLength(); i++)
		{
			Element eStation = (Element) nlStatioms.item(i);
			Element eName = (Element) eStation.getFirstChild();
			
			Text tName = (Text) eName.getFirstChild();
			String name = tName.getData();
			System.out.println(name);
		}
		
	}
	static Element FindStation(Element eRoot, String station) {
		
		boolean found = false;
		Element fStation = null;
		NodeList nlStatioms = eRoot.getElementsByTagName("station");
		int items = nlStatioms.getLength();
		//System.out.println(items);
		for(int i=0; i<nlStatioms.getLength(); i++)
		{
			Element eStation = (Element) nlStatioms.item(i);
			Element eName = (Element) eStation.getFirstChild();
			
			Text tName = (Text) eName.getFirstChild();
			String name = tName.getData();
			
			if (name.equalsIgnoreCase(station) == true) {
				found = true;
				fStation = eStation;
			}
				
		}
		if (found == true) return  fStation;
		else 
		   return (Element)null;
			 
		
	}
	
	
	// ��ü ���� �� 
	// Ư������ ���� ���� ��� 
	// ���ۿ��� ������ ���� ���� 
	// ���ۿ����� ��â�� ���� �ҿ�ð� 
	
	public static void main(String args[]) throws Exception
	{
		int direction;  // 1 : ��������, 2 : �͹̳�
		
		if (args.length < 2) {
			System.out.println("Usage : Subway  ��߿�   ��������");
			System.exit(1);
		}
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringElementContentWhitespace(true);
		factory.setValidating(true);
		DocumentBuilder builder = factory.newDocumentBuilder();	// ���� ó�� �ʿ� 
		
		// XML ���� �Ľ� 
		Document document = builder.parse("subway_9_lap_time_dtd2.xml");
		Element eRoot = document.getDocumentElement();
		ListAllStation(eRoot);	
		
		
		// ��߿�
		Element sStation = FindStation(eRoot, args[0]);
		if (sStation !=null)
			System.out.println( "��߿� " + args[0] + " : " + sStation.getAttribute("idx"));
		int startIdx = Integer.parseInt(sStation.getAttribute("idx"));
		
		//��������
		Element eStation = FindStation(eRoot, args[1]);
		if (eStation !=null)
			System.out.println( "��������" + args[1] +  " : " +eStation.getAttribute("idx"));
		int endIdx = Integer.parseInt(eStation.getAttribute("idx"));
		
		int diffIdx = (endIdx-startIdx);
		if (diffIdx < 0) {
			diffIdx = -diffIdx;  // �������� ���
			direction = 1;
		}
		else
			direction = 2;
		// else �͹̳� ��� 
		if (direction == 1)
			System.out.print("(�������� ���)");
		else
			System.out.print("(�͹̳� ���)");
		System.out.println( args[0] +"-" + args[1] + " : " + (endIdx-startIdx) + "(��������)");
		
		
	 
		int  elapseTime = 0;
		Element cStation = sStation;
		while (cStation != eStation) {
			 
			Element eLapTime = (Element) cStation.getFirstChild().getNextSibling();
			Text tLap = (Text) eLapTime.getFirstChild();
			int iLap  = Integer.parseInt(tLap.getData());
			elapseTime += iLap;
			
			cStation = (Element) cStation.getNextSibling();		
		}
			
		System.out.println("��ü �ҿ�ð� = " +  elapseTime + " ��" );
		
			String toilet = null; 
			String station_call_number = null; 
			String Luggage_storage = null; 
			
			Element etoilet = (Element) eStation.getFirstChild().getNextSibling().getNextSibling();
			Element estation_call_number = (Element) etoilet.getNextSibling();
			Element eLuggage_storage = (Element) eStation.getLastChild();
			
			
			
			Text ttoilet  = (Text) etoilet.getFirstChild();
			Text tstation_call_number  = (Text) estation_call_number.getFirstChild();
			Text tLuggage_storage  = (Text) eLuggage_storage.getFirstChild();
			
			toilet = ttoilet.getData();
			station_call_number = tstation_call_number.getData();
			Luggage_storage = tLuggage_storage.getData();
			
			if(toilet == "out")
				System.out.println("ȭ��� ��ġ : �ܺ�");
			else if(toilet == "int")
				System.out.println("ȭ��� ��ġ : ����");
			else
				System.out.println("ȭ��� ��ġ : ������ġ");

	}
	
}

		