package aps.Silver.S2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class S2_6603 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens ;
	static StringBuilder sb = new StringBuilder();
	static int [] list ; 
	static int[] choose;
	static boolean[] visited;
	
	static int number = 6;
	
	public static void main(String[] args) throws IOException {
		
		while(true) {
			tokens = new StringTokenizer(br.readLine());
			int T = Integer.parseInt(tokens.nextToken());
			
			if(T == 0){
				break;
			}
			
			list = new int[T] ;
			choose = new int[number]; 
			visited = new boolean[T];
			
			for(int i = 0; i < T; i++) {
				list[i] = Integer.parseInt(tokens.nextToken());
			}
			
			combi(0,0);
			sb.append("\n");
			
		}
		System.out.println(sb);

	}
	public static void combi(int cnt, int start) {
		if(cnt == number) {
			
			for(int temp : choose) {
				sb.append(temp).append(" ");
			}
			sb.append("\n");
			return ;
		}
		
		for(int i = start; i < list.length; i++) {
			if(!visited[i]) {
				visited[i] = true;
				choose[cnt] = list[i];
				combi(cnt+1, i+1);
				visited[i] = false;
				
			}
			
		}
	
		
	}

}
