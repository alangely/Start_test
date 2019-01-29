import java.util.Scanner;

public class Test_create_class { // class 제작
	// member 변수 생성, 속성 
	public int count;
	public int count_S;
	public int count_D;
	public String Food;	
	public String Spicy;
	public String Delivery;
	public int price;
	String Check;
	
	Scanner scan = new Scanner(System.in);
	
	public Test_create_class() { // 생성자 생성
		System.out.println("안녕하세요 AA 식당입니다.");
		System.out.println("음식 : 1. 떡볶이 " +  " 2. 피자 " + " 3. 양념치킨 " + " 4. 스파게티 ");
		System.out.println("맵기 : 1. 기본 " + " 2. 맵게 " + " 3. 아주맵게 " + " 4. 폭탄맛 ");
		System.out.println("배달&포장: 1. 매장식사 " + " 2. 포장 " + " 3. 배달 ");
		
	}
	
	public String Start() {  // 반환
		System.out.println("--Start--");
		if(count == 1)
		{
			Food = "떡볶이";
			if(count_S == 1) price = 18000; Spicy = "기본"; Delivery = "매장식사";
			if(count_S == 2) price = 18000; Spicy = "맵게"; Delivery = "매장식사";
			if(count_S == 3) price = 18000; Spicy = "아주맵게"; Delivery = "매장식사";		
			if(count_S == 4) price = 19000;	Spicy = "폭탄맛"; Delivery = "매장식사";
			
			if(count_D == 2) {price = price - 1000; Delivery = "포장";}
			else if(count_D == 3) price = price + 2000; Delivery = "배달";
		}
		else if(count == 2)
		{
			Food = "피자";
			if(count_S == 1) price = 21000; Spicy = "기본"; Delivery = "매장식사";
			if(count_S == 2) price = 21000; Spicy = "맵게"; Delivery = "매장식사";
			if(count_S == 3) price = 21000; Spicy = "아주맵게";	Delivery = "매장식사";		
			if(count_S == 4) price = 22000;	Spicy = "폭탄맛";	Delivery = "매장식사";
			if(count_D == 2) {price = price - 1000; Delivery = "포장";}
			else if(count_D == 3) price = price + 2000; Delivery = "배달";
		}else if(count == 3)
		{
			Food = "양념치킨";
			if(count_S == 1) price = 19000; Spicy = "기본"; Delivery = "매장식사";
			if(count_S == 2) price = 19000; Spicy = "맵게"; Delivery = "매장식사";
			if(count_S == 3) price = 19000; Spicy = "아주맵게"; Delivery = "매장식사";		
			if(count_S == 4) price = 20000;	Spicy = "폭탄맛";	Delivery = "매장식사";
			if(count_D == 2) {price = price - 1000; Delivery = "포장";}
			else if(count_D == 3) price = price + 2000; Delivery = "배달";
		}else if(count == 4)
		{
			Food = "스파게티";
			if(count_S == 1) price = 11000; Spicy = "기본"; Delivery = "매장식사";
			if(count_S == 2) price = 11000; Spicy = "맵게"; Delivery = "매장식사";
			if(count_S == 3) price = 11500; Spicy = "아주맵게"; Delivery = "매장식사";		
			if(count_S == 4) price = 12000;	Spicy = "폭탄맛";	Delivery = "매장식사";
			if(count_D == 2) {price = price - 1000; Delivery = "포장";}
			else if(count_D == 3) price = price + 2000; Delivery = "배달";
		}
		return "Hello";
	}
	
	public void information() {
		System.out.println("----Information----");
		System.out.println("-Food : " + Food);
		System.out.println("-Spicy : " + Spicy);
		System.out.println("-Delivery : " + Delivery);
		System.out.println("-Price : " + price);
	}
	
	public void Stop() {  // 반환 X
		System.out.println("--Stop--");
		System.out.println("--배달되었습니다.--");
	}
	
}
