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
		Document document = builder.parse("subway_9_lap_time_dtd.xml");
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
			 
			Element eLapTime = (Element) cStation.getLastChild();
			Text tLap = (Text) eLapTime.getFirstChild();
			int iLap  = Integer.parseInt(tLap.getData());
			elapseTime += iLap;
			
			cStation = (Element) cStation.getNextSibling();		
		}
			
		System.out.println("��ü �ҿ�ð� = " +  elapseTime + " ��" );
		
	}
	
}

		