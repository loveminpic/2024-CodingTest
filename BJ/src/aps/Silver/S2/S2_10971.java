package aps.Silver.S2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class S2_10971 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens ;
	static int[][] board;
	static List<int[]> list = new ArrayList<>();
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		board = new int[N+1][N+1];
		for(int i = 1; i <= N; i++) {
			tokens = new StringTokenizer(br.readLine());
			for(int j =1; j <=N; j++) {
				board[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		
		permutation(0, new boolean[N],new int[N]);
		for(int[] tmp : list) {
			System.out.println(Arrays.toString(tmp));
		}

	}

	private static void permutation(int cnt,boolean[] visited, int[] choosed) {
		if(cnt == N) {
			list.add(choosed);
			return;
		}
		for(int i = 1; i <=N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				choosed[cnt] = i;
				permutation(cnt+1, visited, choosed);
				visited[i] = false;
			}
		}
	}

}
