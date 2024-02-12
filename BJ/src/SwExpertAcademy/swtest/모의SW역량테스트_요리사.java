package src.SwExpertAcademy.swtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Minji Lee
 * @date 20240208
 * @link 
 * @keyword_solution  
 * @input 
 * @output   
 * @time_complex  
 * @perf 
 */

public class 모의SW역량테스트_요리사 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int T;
	static int N;
	static int[][] board;
	static boolean[] choosed;
//	static List<Integer> result;
	
	static int minimum = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <=T ; t++) {
			N = Integer.parseInt(br.readLine());
			board = new int[N+1][N+1];
			
			for(int i = 1; i <=N; i++) {
				tokens = new StringTokenizer(br.readLine());
				for(int j = 1; j <= N; j++) {
					board[i][j] = Integer.parseInt(tokens.nextToken());
				}
			}
			// ----------------input end------------------
			choosed = new boolean[N+1];
//			result = new ArrayList<>();
			combi(1,0);
			
//			for(int i = 0; i < result.size(); i++) {
//				for(int j = i+1 ; j < result.size(); j++) {
//					minimum = Math.min(minimum, Math.abs(result.get(i)-result.get(j)));
//				}
//			}
			sb.append("#").append(t).append(" ").append(minimum).append("\n");
			
		}
		
		System.out.println(sb);
	}
	
	private static void combi(int start, int cnt) {
		if(cnt == N/2) {
			for(int i = 0; i < choosed.length; i++) {
				if(d)
			}
			System.out.println(Arrays.toString(choosed));
//			
			return;
		}
		
		for(int i = start ; i <= N; i++) {
			if(!choosed[i]) {
				choosed[i] = true;
				combi(i+1, cnt+1);
				choosed[i] = false;
			}
			
		}
	}
	
	

}
