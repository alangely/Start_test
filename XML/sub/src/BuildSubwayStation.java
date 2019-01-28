import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class BuildSubwayStation {
	
	
	static void toDOM(Document doc, String fname) throws TransformerException {
		  TransformerFactory factory = TransformerFactory.newInstance();
		  Transformer transformer = factory.newTransformer();
		  transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		  transformer.setOutputProperty(OutputKeys.ENCODING, "EUC-KR");

		  DOMSource source = new DOMSource(doc);
		  StreamResult result = new StreamResult(new File(fname));
		  transformer.transform(source, result);
    }
	
	static void ListAllStation(Element eRoot) {
		
		
		NodeList nlStatioms = eRoot.getElementsByTagName("station");
		
		System.out.println("*** List All Station ***");
		int items = nlStatioms.getLength();
		//System.out.println(items);
		for(int i=0; i<items; i++)
		{
			Element eStation = (Element) nlStatioms.item(i);
			Element eName = (Element) eStation.getFirstChild();
			Element eLapTime = (Element) eStation.getFirstChild().getNextSibling();
			Element etoilet = (Element) eStation.getFirstChild().getNextSibling().getNextSibling();
			Element estation_call_number = (Element) eStation.getFirstChild().getNextSibling().getNextSibling().getNextSibling();
			Element eLuggage_storage = (Element) eStation.getLastChild();
			
			Text tName = (Text) eName.getFirstChild();	
			Text tLapTime = (Text) eLapTime.getFirstChild();
			Text ttoilet = (Text) etoilet.getFirstChild();
			Text tstation_call_number = (Text) estation_call_number.getFirstChild();
			Text tLuggage_storage = (Text) eLuggage_storage.getFirstChild();
			
			String name = tName.getData();
			String LapTime = tLapTime.getData();
			String toilet = ttoilet.getData();
			String station_call_number = tstation_call_number.getData();
			String Luggage_storage = tLuggage_storage.getData();
			
			System.out.println("역 이름 : " + name + "분 :" + LapTime + "화장실 여부 : " + toilet + "역 전화번호 :" + station_call_number +"지하철 내 짐보관소 여부 : " + Luggage_storage);
		}
		
	}
	static Element FindStation(Element eRoot, String station) {
		
		boolean found = false;
		Element fStation = null;
		NodeList nlStatioms = eRoot.getElementsByTagName("station");
		int items = nlStatioms.getLength();
		//System.out.println(items);
		for(int i=0; i<items; i++)
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
	
    static Element FindLine(Element eRoot) {
		NodeList nlLines = eRoot.getElementsByTagName("line");
		int items = nlLines.getLength();
		 
		if (items >0 && nlLines != null )
		     return  (Element)nlLines.item(0);
		else 
		   return (Element)null;
	}
	
	
	public static void main(String args[]) throws Exception
	{
	 
		int direction;   
		
		if (args.length < 2)
			System.out.println("Usage : Subway  start end");
		 
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringElementContentWhitespace(true);
		factory.setValidating(true);
		DocumentBuilder builder = factory.newDocumentBuilder();	 
		
		 
		Document document = builder.parse("subway_9_lap_time_dtd.xml");
		Element eRoot = document.getDocumentElement();
		ListAllStation(eRoot);
		 
		Element sStation = FindStation(eRoot, args[0]);
		if (sStation !=null)
			System.out.println( "start staion" + args[0] + ":" + sStation.getAttribute("idx"));
		int startIdx = Integer.parseInt(sStation.getAttribute("idx"));
		   
		
		Element eStation = FindStation(eRoot, args[1]);
		if (eStation !=null)
			System.out.println( "end station " + args[1] +  ":" +eStation.getAttribute("idx"));
		int endIdx = Integer.parseInt(eStation.getAttribute("idx"));
		
		int diffIdx = (endIdx-startIdx);
		if (diffIdx < 0) {
			diffIdx = -diffIdx;   
			direction = 1;
		}
		else
			direction = 2;
		 
		if (direction == 1) {
			System.out.print("(up)");
			  System.out.println( args[0] +"-" + args[1] + " : " + -(endIdx-startIdx) + "(stops)");

		}
		else
		{
			System.out.print("(down)");
		    System.out.println( args[0] +"-" + args[1] + " : " + (endIdx-startIdx) + "(stops)");
		}
		
		
	 
		int  elapseTime = 0;
		Element cStation = sStation;
		while (cStation != eStation) {
			Element eLapTime = (Element) cStation.getFirstChild().getNextSibling();
			Text tLap = (Text) eLapTime.getFirstChild();
			int iLap  = Integer.parseInt(tLap.getData());
			
			elapseTime += iLap;
			
			if (direction != 1)
			     cStation = (Element) cStation.getNextSibling();	
			else
				 cStation = (Element) cStation.getPreviousSibling();
			


		}
		System.out.println("Total Elapsed Time = " +  elapseTime );	
		
		Element eLine = FindLine(eRoot);
		eStation = document.createElement("station");
		Element eName = document.createElement("name");
	    Element eLapTime = document.createElement("lap_time");
	    Element etoilet = document.createElement("toilet");
	    Element estation_call_number = document.createElement("station_call_number");
	    Element eLuggage_storage = document.createElement("Luggage_storage");
	    
	    
	    eLine.appendChild(eStation);
	    eStation.appendChild(eName);
	    eStation.appendChild(eLapTime);
	    eStation.setAttribute("idx","25");
	    eStation.appendChild(etoilet);
	    eStation.appendChild(estation_call_number);
	    eStation.appendChild(eLuggage_storage);

	    eName.appendChild(document.createTextNode("언주"));
	    eLapTime.appendChild(document.createTextNode("4"));
	    etoilet.appendChild(document.createTextNode("in"));
	    estation_call_number.appendChild(document.createTextNode("02-2656-0926"));
	    eLuggage_storage.appendChild(document.createTextNode("Yes"));

	    toDOM(document, "new_subway9.xml");
			
	}
	
}

		