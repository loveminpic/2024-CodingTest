package src.Silver.S3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S3_15650 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	static int[] choose;
	
	static int N,M ; 
	
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		choose = new int[M];
		
		combi(0,1);
		System.out.println(sb);

	}
	
	public static void combi(int cnt, int start) {
		
		if(cnt == M) {
			for(int temp : choose) {
				sb.append(temp).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i = start; i <= N; i++) {
			choose[cnt] = i;
			combi(cnt+1, i+1);
		}
		
		
		
	}

}
