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

public class 모의SW역량테스트_요리사  {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int T;
	static int N;
	static int[][] board;
	static boolean[] choosed;
	
	static int minimum ;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <=T ; t++) {
			minimum = Integer.MAX_VALUE;
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
			combi(1,0);
			sb.append("#").append(t).append(" ").append(minimum).append("\n");
			
		}
		
		System.out.println(sb);
	}
	
	private static void combi(int start, int cnt) {
		if(cnt == N/2) {
			findingBestBuger(choosed);
			return;
		}
		for(int i = start ; i <= N ; i++) {
			choosed[i] = true;
			combi(i+1, cnt+1);
			choosed[i] = false;
		}
	}
	
	private static List<Integer> permutation(int[] a, int cnt, List<Integer> list, boolean[] visited) {
		if(cnt == a.length) {
			return list;
		}
		
		for(int i = 0; i < a.length; i++) {
			if(!visited[i]) {
				visited[i] = true;
				list.add(a[i]);
				visited[i] = false;
			}
		}
		
		return list;
		
	}
	

	private static void findingBestBuger(boolean[] choosed2) {
		int[] a = new int[N/2];
		int[] b = new int[N/2];
		int a_count = 0;
		int b_count = 0; 
		
		for(int i = 1; i <= N; i++) {
			if(choosed2[i]) {
				a[a_count] = i;
				a_count++;
			}else {
				b[b_count] = i;
				b_count++;
			}
		}
		
		List<Integer> list_a;
		List<Integer> list_b;
		
		list_a = permutation(a,0,new ArrayList<>(), new boolean[N/2]);
		list_b = permutation(a,0,new ArrayList<>(), new boolean[N/2]);
		
		int sum_a  = 0;
		int sum_b = 0;
		
		for(int j = 0; j < N/2; j++) {
			
		}
	}
	
	
	
	

}
