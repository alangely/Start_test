import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;


public class  AddNewBook {

	 
	 public static void addNewBook(Document doc, String title,String publisher,String price) {
	        Element newBookNode = doc.createElement("book");
	        newBookNode.setAttribute("id","b8");
	        newBookNode.setAttribute("kind","소설");
	        Element titleNode = doc.createElement("title");
	        newBookNode.appendChild(titleNode);
	        Text titletextNode = doc.createTextNode(title);
	        titleNode.appendChild(titletextNode);

	        Element publisherNode = doc.createElement("publisher");
	        newBookNode.appendChild(publisherNode);
	        Text publishertextNode = doc.createTextNode(publisher);
	        publisherNode.appendChild(publishertextNode);

	        Element priceNode = doc.createElement("price");
	        newBookNode.appendChild(priceNode);
	        Text pricetextNode = doc.createTextNode(price);
	        priceNode.appendChild(pricetextNode);
	        
	        Element root = doc.getDocumentElement();
	        root.appendChild(newBookNode);
	 }
	 
	 public static void addAttributeID(Document doc, String id) {
	        Element root = doc.getDocumentElement();
	        Element book = (Element)root.getFirstChild();
	        book.setAttribute("id", id);
	 }
	 
	  
	 public static void main(String args[]) throws Exception
	{
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringElementContentWhitespace(true);
		factory.setValidating(true);
		DocumentBuilder builder = factory.newDocumentBuilder();	
		
		 
		Document document = builder.parse("book_list.xml");
		Element eRoot = document.getDocumentElement();

 		addNewBook( document, "MyLife",  "백석", "1000");
 		addAttributeID(document, "b9");
		
		System.out.println("** Book Titles **");
		NodeList nlTitles = eRoot.getElementsByTagName("title");
		int items = nlTitles.getLength();
		//System.out.println(items);
		for(int i=0; i<items; i++)
		{
			Element eTitle1 = (Element) nlTitles.item(i);
			Text tTitle1 = (Text) eTitle1.getFirstChild();
			String title1 = tTitle1.getData();
			System.out.println(title1);
		}
		 
	}
	
}