import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class SubWay {
	
	
	static void ListAllStation(Element eRoot) {
		
		
		NodeList nlStatioms = eRoot.getElementsByTagName("station");
		
		System.out.println("*** 전체 역 ***");
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
	
	
	// 전체 역의 수 
	// 특정역에 대한 정보 얻기 
	// 시작역과 종착력 간의 역수 
	// 시작역에서 종창력 간의 소요시간 
	
	public static void main(String args[]) throws Exception
	{
		int direction;  // 1 : 김포공항, 2 : 터미널
		
		if (args.length < 2) {
			System.out.println("Usage : Subway  출발역   목적지열");
			System.exit(1);
		}
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringElementContentWhitespace(true);
		factory.setValidating(true);
		DocumentBuilder builder = factory.newDocumentBuilder();	// 예외 처리 필요 
		
		// XML 문서 파싱 
		Document document = builder.parse("subway_9_lap_time_dtd2.xml");
		Element eRoot = document.getDocumentElement();
		ListAllStation(eRoot);	
		
		
		// 출발역
		Element sStation = FindStation(eRoot, args[0]);
		if (sStation !=null)
			System.out.println( "출발역 " + args[0] + " : " + sStation.getAttribute("idx"));
		int startIdx = Integer.parseInt(sStation.getAttribute("idx"));
		
		//목적지열
		Element eStation = FindStation(eRoot, args[1]);
		if (eStation !=null)
			System.out.println( "목적지열" + args[1] +  " : " +eStation.getAttribute("idx"));
		int endIdx = Integer.parseInt(eStation.getAttribute("idx"));
		
		int diffIdx = (endIdx-startIdx);
		if (diffIdx < 0) {
			diffIdx = -diffIdx;  // 김포공항 방면
			direction = 1;
		}
		else
			direction = 2;
		// else 터미널 방면 
		if (direction == 1)
			System.out.print("(김포공항 방면)");
		else
			System.out.print("(터미널 방면)");
		System.out.println( args[0] +"-" + args[1] + " : " + (endIdx-startIdx) + "(경유역수)");
		
		
	 
		int  elapseTime = 0;
		Element cStation = sStation;
		while (cStation != eStation) {
			 
			Element eLapTime = (Element) cStation.getFirstChild().getNextSibling();
			Text tLap = (Text) eLapTime.getFirstChild();
			int iLap  = Integer.parseInt(tLap.getData());
			elapseTime += iLap;
			
			cStation = (Element) cStation.getNextSibling();		
		}
			
		System.out.println("전체 소요시간 = " +  elapseTime + " 분" );
		
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
				System.out.println("화장실 위치 : 외부");
			else if(toilet == "int")
				System.out.println("화장실 위치 : 내부");
			else
				System.out.println("화장실 위치 : 양쪽위치");

	}
	
}

		