package ssafy_class;

import java.util.Scanner;

public class FibonacciTest {
	static long callCnt1, callCnt2;
	static long[] memo;
	
	static long fibo1(int n) {
		callCnt1++;
		if(n < 2 )return n;
		return fibo1(n-1) + fibo1(n-2);
		
	}
	
	static long fibo2(int n) {
		callCnt2++;	
		if(n >= 2 && memo[n] == 0) {
			memo[n] = fibo2(n-1) + fibo2(n-2);
		}
		return memo[n];
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		memo = new long[N+1];
		memo[0] = 0;
		memo[1] =1 ;
		System.out.println(fibo2(N));
		System.out.println("Callcnt : " + callCnt2);
		System.out.println(fibo1(N));
		System.out.println("Callcnt : " + callCnt1);
	}
	

}
