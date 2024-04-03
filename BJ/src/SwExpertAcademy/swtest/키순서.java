package SwExpertAcademy.swtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class 키순서 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int TC, N, M;
	static StringBuilder sb = new StringBuilder();
	static boolean[] result;
	static boolean[][] board_a, board_b;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		TC = Integer.parseInt(br.readLine());
		for(int t = 1; t <= TC; t++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			result = new boolean[N+1];
			board_a = new boolean[N+1][N+1];
			board_b = new boolean[N+1][N+1];
			
			for(int i = 0; i < M ; i++) {
				tokens = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(tokens.nextToken());
				int b = Integer.parseInt(tokens.nextToken());
				board_a[a][b] = true;
				board_b[b][a] = true;
			}

			for(int i = 1; i <= N; i++) {
				boolean[] visited = new boolean[N+1];
				visited[0] = true;
				visited = solved(visited, i, board_a);
				visited = solved(visited, i, board_b);
				if(check(visited, i)) {
					result[i] = true;
				};
			}
		
			int count = 0;
			for(boolean val : result) {
				if(val) count++;
			}
			sb.append("#"+ t + " ").append(count).append("\n");
		}
		System.out.println(sb);
	}
 
	private static boolean[] solved(boolean[] visited, int start, boolean[][] board) {
		visited[start] = true;
		Queue<Integer> q = new ArrayDeque<>();
		for(int i = 1; i <= N; i++) {
			if(!visited[i] && board[start][i]) {
				q.offer(i);
				visited[i] = true;
			}
		}
		
		while(!q.isEmpty()) {
			int col = q.poll();
			
			for(int i = 1; i <= N; i++) {
				if(!visited[i] && board[col][i]) {
					q.offer(i);
					visited[i] = true;
				}
			}
		}
		
		return visited;
	}

	private static boolean check(boolean[] visited, int before) {
	
		for(boolean c : visited) {
			if(c == false) {
				return false;
			}
		}
		
		return true;
	}

}
