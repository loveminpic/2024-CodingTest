package src.SwExpertAcademy.d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class D3_햄버거다이어트 {
	static int T, N, L;
	static int[] r_score, r_kal;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens; 
	static StringBuilder sb = new StringBuilder();
	static int resultScore;

	
	public static void main(String[] args) throws NumberFormatException, IOException {
		T = Integer.parseInt(br.readLine());
		for(int t = 1; t <=T ; t++) {
			resultScore = 0;
			tokens = new StringTokenizer(br.readLine());
			N = Integer.parseInt(tokens.nextToken());
			L = Integer.parseInt(tokens.nextToken());
			
			r_score = new int[N];
			r_kal = new int[N];
			for(int i = 0; i < N; i++) {
				tokens = new StringTokenizer(br.readLine());
				r_score[i] = Integer.parseInt(tokens.nextToken());
				r_kal[i] = Integer.parseInt(tokens.nextToken());
		
			}
			
			boolean[] choose = new boolean[N];
			
			powerSet(0, choose);
			
			sb.append("#").append(t).append(" ");
			sb.append(resultScore).append("\n");
		}
		System.out.println(sb);
	}

	private static void powerSet(int cnt, boolean[] choose) {
		if(cnt == N) {
			findingMax(choose);
			return;
		}
		choose[cnt] = true;
		powerSet(cnt+1, choose);
		choose[cnt] = false;
		powerSet(cnt+1, choose);
	
	}

	private static void findingMax(boolean[] choose) {
		int sumKal = 0;
		int score = 0;
		for(int i = 0; i < N ; i++) {
			if(choose[i] == true) {
				score += r_score[i];
				sumKal += r_kal[i];
			}
			if(sumKal > L) {
				return;
			}
		}
		
		resultScore = Math.max(resultScore, score);
		
		
	}
}
