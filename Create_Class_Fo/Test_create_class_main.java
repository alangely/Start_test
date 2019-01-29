import java.util.Scanner;

public class Test_create_class_main {

	public static void main(String[] args) {
		Test_create_class Fo = new Test_create_class();
		Scanner scan = new Scanner(System.in);
		Fo.count = scan.nextInt();
		Fo.count_S = scan.nextInt();
		Fo.count_D = scan.nextInt();
		
		Fo.Start();
		Fo.Stop();
		Fo.information();
	}
}
