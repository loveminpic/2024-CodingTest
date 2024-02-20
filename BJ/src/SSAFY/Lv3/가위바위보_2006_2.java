package src.SSAFY.Lv3;

public class 가위바위보_2006_2 {

	public static void main(String[] args) {
	
		for(int i = 1; i <= 5; i++) {
			System.out.printf("%3d", i );
		}
		System.out.printf("\n"); 
		System.out.printf("%3s", " "); 
		
		for(int i = 6; i <= 8; i++) {
			System.out.printf("%3d", i );
		}
		System.out.printf("%3s", " "); 
		System.out.printf("\n"); 
		
		System.out.printf("%3s", "      "); 
		System.out.printf("%3d", 9); 
		System.out.printf("%3s", "     "); 
		
		System.out.printf("\n"); 
		System.out.printf("%3s", " ");
		
		for(int i = 10; i <= 12; i++) {
			System.out.printf("%3d", i);
		}
		
	}
}
