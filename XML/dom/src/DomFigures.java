import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.*;

public class DomFigures
{ 
      public static void computeArea(String uri)
      {
         try{
        	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
     		factory.setIgnoringElementContentWhitespace(true);
     		factory.setValidating(false);
     		DocumentBuilder builder = factory.newDocumentBuilder();	   
     		Document document = null;
     	    // XML  
     		if (uri != null)
    		   document = builder.parse(uri);
     		else
     		{
     			System.out.println("No URL for XML file");
     			System.exit(1);
            }
            
            traverse_tree(document);
         }
         catch (Exception e) {
            e.printStackTrace(System.err);
         }
      }

      private static void traverse_tree(Node node)
      {
         if(node == null) {
            return;
         }
         int type = node.getNodeType();
         switch (type) {
            case Node.DOCUMENT_NODE: {
               traverse_tree(((Document)node).getDocumentElement());
               break;
            }
            case Node.ELEMENT_NODE: {
               String elementName = node.getNodeName();
               NamedNodeMap attrs = node.getAttributes();
               if(elementName.equals("circle")) {
                  Attr attrib = (Attr)attrs.getNamedItem("r");
                  String sr = attrib.getValue();
                  float radius = Float.valueOf(sr).floatValue();
                  float area = (float)Math.PI*radius*radius;
                  System.out.println("Circle : Radius = " + radius + " Area = " + area);
               }
               else if(elementName.equals("rectangle")) {
                  Attr attrib = (Attr)attrs.getNamedItem("w");
                  String sw = attrib.getValue();
                  attrib = (Attr)attrs.getNamedItem("h");
                  String sh = attrib.getValue();
                  float width = Float.valueOf(sw).floatValue();
                  float height = Float.valueOf(sh).floatValue();
                  float area = width * height;
                  System.out.println("Rectangle : Width = " + width +
                                     " Height = " + height + " Area = " + area);
                }
                else if(elementName.equals("ellipse")) {
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
                NodeList childNodes = node.getChildNodes();     
                if(childNodes != null) {
                   int length = childNodes.getLength();
                   for (int loopIndex = 0; loopIndex < length ; loopIndex++)
                   {
                      traverse_tree(childNodes.item(loopIndex));
                   }
                }
                break;
             }
          }
       }

       public static void main(String[] args)
       {
          DomFigures.computeArea(args[0]);
       }
    }