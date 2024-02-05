package src.Class;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Testing {
	static int[] src = {1,2,3,4};
	static int N ; 
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int[] choosed;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		
		visited = new boolean[src.length];
		choosed = new int[N];
		
//		permutation(0, choosed, visited);
//		System.out.println("++++++++++++++++++++++++++");
//		duplicatePermutation(0,choosed);
//		combi(0,0,choosed,visited);
	}
	//npr -> 순서와 갯수가 필요. 
	
	public static void permutation(int cnt, int[] choosed, boolean[] visited) {
		if(cnt == choosed.length) {
			System.out.println(Arrays.toString(choosed));
			return;
		}
		
		for(int i = 0; i < src.length; i++) {
			if(!visited[i]) {
				visited[i] = true;
				choosed[cnt] = src[i];
				permutation(cnt+1, choosed, visited);
				visited[i] = false;
				
			}
		}
	}
	
	public static void duplicatePermutation(int cnt, int[] choosed) {
		if(cnt == choosed.length) {
			System.out.println(Arrays.toString(choosed));
			return;
		}
		
		for(int i = 0; i < src.length; i++) {
			choosed[cnt] = src[i];
			duplicatePermutation(cnt+1, choosed);
		}
		
	}
	
	public static void combi(int start, int cnt, int[]choosed, boolean[] visited) {
		if(cnt == choosed.length) {
			System.out.println(Arrays.toString(choosed));
			return;
		}
		for(int i = start; i < src.length; i++) {
			if(!visited[i]) {
				visited[i] = true;
				choosed[cnt] = src[i];
				combi(i, cnt+1, choosed, visited);
				visited[i] = false;
			}
		}
		
	}

}
