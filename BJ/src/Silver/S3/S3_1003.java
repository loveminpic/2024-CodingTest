package src.Silver.S3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S3_1003 {
	
	static class ZeroOne {
		int zero;
		int one;
		
		public ZeroOne(int zero, int one) {
			super();
			this.zero = zero;
			this.one = one;
		}
		
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static ZeroOne[] dp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		int N = Integer.parseInt(br.readLine());

		for(int i = 0; i < N; i++) {
			
			int num = Integer.parseInt(br.readLine());
			dp = new ZeroOne[num+1];

			dp[0] = new ZeroOne(1,0);

			if(num >= 1) {
				dp[1] = new ZeroOne(0,1);

			}
			fibo(num);
			System.out.println(dp[num].zero + " " + dp[num].one);
		
		}
		
		
	}
	private static void fibo(int num) {
		if(num == 1 || num == 0) {
			return;
		}
		for(int i = 2; i <= num ; i++) {
			dp[i] = new ZeroOne(dp[i-1].zero + dp[i-2].zero, dp[i-1].one + dp[i-2].one);			
		}
	}

}
