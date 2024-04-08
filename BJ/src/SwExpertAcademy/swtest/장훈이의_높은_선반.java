package SwExpertAcademy.swtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class 장훈이의_높은_선반 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	static int TC, N, B;
	static int Min;
	static int[] hight;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		TC = Integer.parseInt(br.readLine());
		for(int t = 1; t <= TC; t++) {
			tokens = new StringTokenizer(br.readLine());
			N = Integer.parseInt(tokens.nextToken());
			B = Integer.parseInt(tokens.nextToken());
			
			tokens = new StringTokenizer(br.readLine());
			hight = new int[N];
			for(int i = 0; i < N; i++) {
				hight[i] = Integer.parseInt(tokens.nextToken());
			}
			
			Min = Integer.MAX_VALUE;
			
			powerSet(0, new boolean[N]);
			sb.append("#").append(t).append(" ").append(Min).append("\n");
		}
		System.out.println(sb);
	}

	private static void powerSet(int cnt, boolean[] choose) {
		if(cnt == N) {
			check(choose);
			return;
		}
		
	
		choose[cnt] = true;
		powerSet(cnt+1, choose);
		choose[cnt] = false;
		powerSet(cnt+1, choose);
		
	}

	private static void check(boolean[] choose) {
		int num = 0;
		
		for(int i = 0; i <N; i++ ) {
			if(choose[i]) {
				num += hight[i];
			}
		}
		if(num >= B) {
			int result = Math.abs(B-num);
			Min = Math.min(result, Min);
		}
	}

}
