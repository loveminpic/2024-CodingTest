package ssafy_class;

import java.util.Scanner;

public class SquareNumberTest {
	static int callCnt1;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		int n = sc.nextInt(); // 최대 10억가능
		System.out.println(exp1(x,n));
		System.out.println(callCnt1);
	}

	static long exp1(long x, int n) {
		callCnt1++;
		if(n == 1) return x;
		int half = n/2;
		long res = exp1(x, half);
		res *= res;
		return n%2== 0? res : res*x;
	}
}
