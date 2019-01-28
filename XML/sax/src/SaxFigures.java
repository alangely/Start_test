import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

   public class SaxFigures extends DefaultHandler
   { // 어뎁터 클레스 : 필요한 클레스만 사용하는 클래스를 말함
	   // Saxfigures에는 startElement빼고는 사용하지않는다. 
	   // 필요한 내용만 사용하는 것을 말한다. 
	   
	    
       public void startElement(String uri, String localName, String qName,
                                Attributes attributes)
       {
          if(qName.equals("circle"))
          {
             String sr = attributes.getValue("r");
             float radius = Float.valueOf(sr).floatValue();
             float area = (float)Math.PI*radius*radius;
             System.out.println("Circle : Radius = " + radius + 
                                " Area = " + area);
          }
          else if(qName.equals("ellipse"))
          {
             String sw = attributes.getValue("w");
             String sh = attributes.getValue("h");
             float width = Float.valueOf(sw).floatValue();
             float height = Float.valueOf(sh).floatValue();
             float area = (float)Math.PI*(width/2)*(height/2);
             System.out.println("Ellipse : Width = " + width + 
                                " Height = " + height + 
                                " Area = " + area);
          }
          else if(qName.equals("rectangle"))
          {
             String sh = attributes.getValue("h");
             String sw = attributes.getValue("w");
             float width = Float.valueOf(sw).floatValue();
             float height = Float.valueOf(sh).floatValue();
             float area = width * height;
             System.out.println("Rectangle : Width = " + width +
                                " Height = " + height +
                                " Area = " + area);
          }
          else if(qName.equals("triangle"))
          {
             String sh = attributes.getValue("h");
             String sw = attributes.getValue("w");
             float width = Float.valueOf(sw).floatValue();
             float height = Float.valueOf(sh).floatValue();
             float area = (width * height)/2;
             System.out.println("Triangle : Width = " + width +
                                " Height = " + height +
                                " Area = " + area);
          }
      }

      public static void main(String[] args)
      {
         try{
        	 SAXParserFactory factory =  SAXParserFactory.newInstance();
        	 SAXParser parser = factory.newSAXParser();
        	
        	 SaxFigures SAXHandler = new SaxFigures();
        	 System.out.println("File : " + args[0]);
             parser.parse(new File(args[0]),SAXHandler);
         }
         catch(Exception e){
            e.printStackTrace(System.err);
         }
      }
   }
