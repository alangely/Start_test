import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class BuildDom {

	static void toDOM(Document doc) throws TransformerException {
		  TransformerFactory factory = TransformerFactory.newInstance();
		  Transformer transformer = factory.newTransformer();
		  DOMSource source = new DOMSource(doc);
		  StreamResult result = new StreamResult(new File("dom2file.xml"));
		  transformer.transform(source, result);
    }

	public static void main(String[] args) throws ParserConfigurationException, TransformerException {
		// TODO Auto-generated method stub
		
		DocumentBuilderFactory dbf =  DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.newDocument();
		
		Element name = doc.createElement("name");
		Element first = doc.createElement("first");
		Element last = doc.createElement("last");
		Element alias = doc.createElement("alias");
		
		
		doc.appendChild(name);
		name.appendChild(first);
		name.appendChild(last);
		name.appendChild(alias);
		first.appendChild(doc.createTextNode("heejea"));
		last.appendChild(doc.createTextNode("song"));
		alias.appendChild(doc.createTextNode("¸Þ··"));
		
		
		name.setAttribute("city", "Cheonan");
		
		NodeList nameNL = doc.getElementsByTagName("name");
		int countName = nameNL.getLength();
		if (countName >0 && nameNL != null) {
		     Element firstElement = (Element)nameNL.item(0).getFirstChild();
		     Element lastElement = (Element)nameNL.item(0).getLastChild();
		     Element nextElement = (Element)nameNL.item(0).getNextSibling();
		     System.out.println("Frist name : " + firstElement.getFirstChild().getNodeValue());
		     System.out.println("Last name : " + lastElement.getFirstChild().getNodeValue());
		     }
		toDOM(doc);
	}

}
