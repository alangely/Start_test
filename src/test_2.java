import java.util.Scanner;

public class test_2 {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("최고&최저 기온 입력 : ");
		Float temper_up = scan.nextFloat();
		Float temper_under = scan.nextFloat();
		System.out.println("현재 온도를 입력 : ");
		Float temp = scan.nextFloat();
		Float temper;
		temper_up = (temp < temper_up) ? temper_up : temp;
		temper_under = (temp > temper_under) ? temper_under : temp;
		System.out.println("현재 최고 : " + temper_up);
		System.out.println("현재 최저 : " + temper_under);
		System.out.println("현재 온도 : " + temp);
	}
}
