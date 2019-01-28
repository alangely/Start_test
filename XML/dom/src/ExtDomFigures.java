import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class ExtDomFigures
{ 
	  static int totalCircle;
	  static int totalRectangle;
	  static int totalEllipse;
	  
	  
	  static void DOM2File(Document doc, String fname) throws TransformerException {
		  TransformerFactory factory = TransformerFactory.newInstance();
		  Transformer transformer = factory.newTransformer();
		  transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		  transformer.setOutputProperty(OutputKeys.ENCODING, "EUC-KR");

		  DOMSource source = new DOMSource(doc);
		  StreamResult result = new StreamResult(new File(fname));
		  transformer.transform(source, result);
    }
	  
	  
	  public static void initializeTotalObject() {
		   totalCircle = 0;
		   totalRectangle = 0;
		   totalEllipse = 0;
	  }
	  public static void appendCircle(Document document){
		  System.out.println("append a cirecle ");
          // <circle x="50" y="50" r="10"/>
          
		    Element e = document.getDocumentElement();
		    System.out.println("root element = " + e.getNodeName());
		    
          Element e1 =  document.createElement("circle");
          e1.setAttribute("x", "50");
          e1.setAttribute("y", "50");
          e1.setAttribute("r", "10");
          e.appendChild(e1);
          traverse_tree(document);
	  }
	  
      public static void computeArea(String uri)
      {
         try{
        	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         		factory.setIgnoringElementContentWhitespace(true);
        		factory.setValidating(false);
     	    	DocumentBuilder builder = factory.newDocumentBuilder();	   
    		    Document document =builder.parse(uri);
            
    		    initializeTotalObject();
    		    traverse_tree(document);
    		    
    		    System.out.println("totalCircle =  " + totalCircle + " totalRectangle = " + totalRectangle + " totalEllipse = " +
    	        		  totalEllipse); 
    		    
    		    initializeTotalObject();
    		    appendCircle(document);
    		    System.out.println("totalCircle =  " + totalCircle + " totalRectangle = " + totalRectangle + " totalEllipse = " +
  	        		  totalEllipse); 
  		    
    		    DOM2File(document, "new_figures.xml");
                
         }
         catch (Exception e) {
            e.printStackTrace(System.err);
         }
      }

      private static void traverse_tree(Document doc)
      {
          
      	   //get a nodelist of <circle> elements
  		  NodeList nl = doc.getElementsByTagName("circle");
  		  
  		  if(nl != null && nl.getLength() > 0) {
  			 totalCircle += nl.getLength();
  			 for (int i = 0; i < nl.getLength(); i++) {
  			     Node node = nl.item(i);
                 NamedNodeMap attrs = node.getAttributes();
                 Attr attrib = (Attr)attrs.getNamedItem("r");
                 String sr = attrib.getValue();
                 float radius = Float.valueOf(sr).floatValue();
                 float area = (float)Math.PI*radius*radius;
                 System.out.println("Circle : Radius = " + radius + " Area = " + area);
  			 }
          }
  		 //get a nodelist of <rectangle> elements
  		 nl = doc.getElementsByTagName("rectangle");
  		
  		 if(nl != null && nl.getLength() > 0) {
  			totalRectangle += nl.getLength();
  			for (int i = 0; i < nl.getLength(); i++) {
			   Node node = nl.item(i);
               NamedNodeMap attrs = node.getAttributes();
               Attr attrib = (Attr)attrs.getNamedItem("w");
               String sw = attrib.getValue();
               attrib = (Attr)attrs.getNamedItem("h");
               String sh = attrib.getValue();
               float width = Float.valueOf(sw).floatValue();
               float height = Float.valueOf(sh).floatValue();
               float area = width * height;
               System.out.println("Rectangle : Width = " + width +
                                     ", Height = " + height + ", Area = " + area);
  			}
         }
  		//get a nodelist of <ellipse> elements
  		 nl = doc.getElementsByTagName("ellipse");
  		
  		 if(nl != null && nl.getLength() > 0) {
  			 totalEllipse += nl.getLength();
  			 for (int i = 0; i < nl.getLength(); i++) {
     		   Node node = nl.item(i);
  			   NamedNodeMap attrs = node.getAttributes();
		       Attr attrib = (Attr)attrs.getNamedItem("w");
               String sw = attrib.getValue();
               attrib = (Attr)attrs.getNamedItem("h");
               String sh = attrib.getValue();
               float width = Float.valueOf(sw).floatValue();
               float height = Float.valueOf(sh).floatValue();
               float area = (float)Math.PI*(width/2)*(height/2);
               System.out.println("Ellipse : Width = " + width +
                                     " Height = " + height + " Area = " + area); 
  			 }
          }
       }

       public static void main(String[] args)
       {
            computeArea(args[0]);
          
       }
    }