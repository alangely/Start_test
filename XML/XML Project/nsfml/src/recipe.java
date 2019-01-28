import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class recipe {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringElementContentWhitespace(true);
		factory.setValidating(true);
		
		//°´Ã¼ ¼±¾ð
		DB_Conn db = new DB_Conn();
		
		String Name = "";
		String Food_feature = "";
		String North_location = "";;
		String F_ingredients= "";
		String F_Recipe = "";
		String Recipe_order = "";
		String Recipe_time = "";
		String North_location_X = "";
		String North_location_Y = "";
		String Korea_Rest_X ="";
		String Korea_Rest_Y ="";
		String Korea_restaurant ="";
		
		int sum=0;
		int cate;
		
		try {
		//¿¹¿ÜÃ³¸® ÇÊ¼ö
		DocumentBuilder builder = factory.newDocumentBuilder();
		//ÆÄ½ÌÇÒ xml ¹®¼­ ÀÌ¸§ ¼³Á¤
		Document document = builder.parse("NSFML.xml");
		
	
		//1. ¿¤¸®¸ÕÆ® ÀÐ¾î¿À±â
		//·çÆ® ¿¤¸®¸ÕÆ®
		Element eRoot = document.getDocumentElement();
		NodeList NorthFRecipe = eRoot.getElementsByTagName("North_Food_Recipe");
		// int startIdx = Integer.parseInt(NorthFRecipe.getAttribute("idx");
		
		
		
		int items = NorthFRecipe.getLength();
		
		for(int i=0; i<items; i++) {
			Element eNorth = (Element) NorthFRecipe.item(i);
			//2Ãþ ¿¤¸®¸ÕÆ®(Ã³À½ - ÀÌÀü - ´ÙÀ½ - ¸¶Áö¸· ÀÐÀ½)
			Element eName = (Element) eNorth.getFirstChild();
			Element eFood_feature = (Element) eName.getNextSibling();
			Element eNorth_location = (Element) eName.getNextSibling().getNextSibling();
			//Element eamount = (Element) eF_number.getLastChild();
		
			//Element eF_ingredients = (Element) eF_number.getFirstChild();
			//Element eamount = (Element) eF_number.getLastChild();
			//Element eRecipe_order = (Element) eF_Recipe.getFirstChild();
			Element eRecipe_time = (Element) eName.getNextSibling().getNextSibling().getNextSibling();
			Element eNorth_location_X = (Element) eName.getNextSibling().getNextSibling().getNextSibling().getNextSibling();
			Element eNorth_location_Y = (Element) eName.getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling();
			Element eKorea_Rest_X = (Element) eName.getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling();
			Element eKorea_Rest_Y = (Element) eName.getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling();
			Element eKorea_restaurant = (Element) eName.getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling();
			
			Element eF_ingredients = (Element) eName.getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling();
//			Element eF_Recipe = (Element) eName.getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling();
			Element eF_Recipe = (Element) eNorth.getLastChild();
			Element eRecipe_order = (Element) eF_Recipe.getFirstChild();	
			
			// Element eNorth_location_Y = (Element) eNorth.getLastChild();
			
			Text tName = (Text) eName.getFirstChild();
			Text tFood_feature = (Text) eFood_feature.getFirstChild();
			Text tNorth_location = (Text) eNorth_location.getFirstChild();
			
			
			// Text tF_material = (Text) eF_material.getFirstChild();
			// Text tF_number = (Text) eF_number.getFirstChild();
			
			//Text tamount = (Text) eamount.getFirstChild();
			// Text tF_Recipe = (Text) eF_Recipe.getFirstChild();
		//	Text tRecipe_order = (Text) eRecipe_order.getFirstChild();
			Text tRecipe_time = (Text) eRecipe_time.getFirstChild();
			Text tNorth_location_X = (Text) eNorth_location_X.getFirstChild();
			Text tNorth_location_Y = (Text) eNorth_location_Y.getFirstChild();
			Text tKorea_Rest_X = (Text) eKorea_Rest_X.getFirstChild();
			Text tKorea_Rest_Y = (Text) eKorea_Rest_Y.getFirstChild();
			Text tKorea_restaurant = (Text)eKorea_restaurant.getFirstChild();
			Text tF_ingredients = (Text) eF_ingredients.getFirstChild();
			Text tRecipe_order = (Text) eRecipe_order.getFirstChild();
		//	Text tnumAttr = (text) numAttr.get();
	//		String numAttr = eRecipe_order.getAttribute("num");
			//Text tNorth_location_Y = (Text) eNorth_location_Y.getFirstChild();
			
			Name = tName.getData();
			Food_feature = tFood_feature.getData();
			North_location = tNorth_location.getData();
			// F_material = tF_material.getData();	
			// F_number = tF_number.getData();
			
		//	amount = tamount.getData();
			// F_Recipe = tF_Recipe.getData();	
		//	Recipe_order = tRecipe_order.getData();
			Recipe_time = tRecipe_time.getData();
			North_location_X = tNorth_location_X.getData();
			North_location_Y = tNorth_location_Y.getData();
			Korea_Rest_X = tKorea_Rest_X.getData();
			Korea_Rest_Y = tKorea_Rest_Y.getData();
			Korea_restaurant = tKorea_restaurant.getData();
			F_ingredients = tF_ingredients.getData();
			Recipe_order = tRecipe_order.getData();
			
			//select
			
			db.select("select * from nsfrec where Name= " + "'" + Name + "'");
			db.rs.beforeFirst();
			
			while(db.rs.next()) {
				sum += 1;
			}
			
			switch(Name) {
				case "Æò¾ç¿Â¹Ý" :
					cate = 10000;
					break;
				case "³ìµÎÁöÁü" :
					cate = 20000;
					break;
				case "¾ð°¨ÀÚ¶±" :
					cate = 30000;
					break;
				case "°«±èÄ¡" :
					cate = 40000;
					break;
				case "ÀüÃµ´ßÆ¢±â" :
					cate = 50000;
					break;
				case "´õ´öººÀ½" :
					cate = 60000;
					break;
				case "ÇØÁÖºñºö¹ä" :
					cate = 70000;
					break;
				case "¹Ù½º·¹±âµÎºÎÅÁ" :
					cate = 80000;
					break;
				default :
					cate = 990000;
					break;
			}
			//update
			db.update("insert into nsfrec values('"  + Name + "', '" + Food_feature + "','" + North_location + "','"  + F_ingredients +"', '"+ Recipe_order +"', '"+ Recipe_time +"', '"+ North_location_X +"', '"+ North_location_Y +"', '"+ Korea_Rest_X +"', '"+ Korea_Rest_Y +"', '" + Korea_restaurant + "')");
			sum = 0;
			cate = 0;
			
		}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
						
		//¿¬°á ÇØÁ¦
		db.close();
		
	}

}
