import java.util.Scanner;

public class Test_1 { // 구구단 Test
	public static void main(String[] args)
	{
		Scanner scan = new Scanner (System.in); 
		int sum;
		System.out.print("입력해주세요 : ");
		int temp = scan.nextInt();
		int i, j;
		for(i = 1; i <= temp; i++)
		{
			for(j = 1; j < 10; j++)
			{
				sum = i * j;
				System.out.println(i+"*"+j+"="+sum);
			}
		}
		
	}

}
