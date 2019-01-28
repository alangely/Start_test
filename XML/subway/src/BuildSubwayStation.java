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
			Element eLapTime = (Element) cStation.getLastChild();
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
	    
	    eLine.appendChild(eStation);
	    eStation.appendChild(eName);
	    eStation.appendChild(eLapTime);
	    eStation.setAttribute("idx","25");

	    eName.appendChild(document.createTextNode("¾ðÁÖ"));
	    eLapTime.appendChild(document.createTextNode("4"));

	    toDOM(document, "new_subway9.xml");
			
	}
	

	
}

		