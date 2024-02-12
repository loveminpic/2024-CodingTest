package src.Gold.G5;

import java.util.Scanner;

public class G5_2023 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] start_num = {2,3,5,7};
		
		String temp;
		String sub;
		int primeCheck;
		
		for(int s = 0; s < 4; s++) {
			int start = (int) (start_num[s] * (Math.pow(10, N-1)));
			int end = (int) (start + Math.pow(10, N-1) - 1);
					
			for(int i = start; i <= end; i++) {
				temp = Integer.toString(i);
				boolean check = true;
				
				for(int j = 2; j <= N; j++) {
					sub = temp.substring(0, j);
					primeCheck = Integer.parseInt(sub);
					if(!isPrime(primeCheck)) {
						check = false;
						break;
					}
					
				}
				if(check) {
					System.out.println(i);
				}
			}
		}
		sc.close();
	}
	

	
	public static boolean isPrime(int n) {
		if(n < 2) return false;
		
		for(int i = 2; i < n; i++) {
			if(n%i == 0) {
				return false;
			}
		}
		
		return true;
		
	}
}
