package SwExpertAcademy.swtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SwTest1952 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens ;
	static int[] coins = new int[4];
	static int[] months = new int[13];
	
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		for(int t = 1; t <= N; t++) {
			tokens = new StringTokenizer(br.readLine());
			for(int i = 0; i < 4; i++) {
				coins[i] = Integer.parseInt(tokens.nextToken());
			}
			tokens = new StringTokenizer(br.readLine());
			for(int i = 1; i <= 12 ; i++) {
				months[i] = Integer.parseInt(tokens.nextToken());
			}
			
			int result = greedy();
			sb.append("#").append(t).append(" ").append(result).append("\n");
			
		}
		System.out.println(sb);
	}

	private static int greedy() {
		int better  = 0;;
		if(coins[1] / coins[0] == 0) {
			better = coins[1] / coins[0];
		}else {
			better = coins[1] / coins[0] + 1;
		}
		
		int[] list = new int[13];
		
		for(int i = 1; i <= 12; i++) {
			if(months[i] == 0) {
				list[i] = 0;
			}else {
				if(months[i] < better) {
					list[i] = coins[0] * months[i];
				}else if(months[i] >= better) {
					list[i] = coins[1];
				}
				
			}
		}
		
		int[] dp = new int[13];
		
		for(int i = 1; i <= 12; i++) {	
			if(list[i] == 0) continue;
			dp[i] = list[i] + dp[i-1];
		}
		int result = 1000000;
		boolean check = true;
		
		for(int i = 1; i <= 12; i++) {
			if(list[i] != 0 && list[i-1] != 0 && list[i-2] != 0) {
				if(check) {
					result = dp[i];
					check = false;
				}else {
					result = Math.min(result, coins[2]+ dp[i-3]);
				}
			}
		}
		if(result > coins[3]) {
			return coins[3];
		}
		return result;
	}

}
