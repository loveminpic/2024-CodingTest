package SwExpertAcademy.swtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;


public class 보급로 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int TC, N;
	static int result;
	
	static int[] S,E;
	static int[][] board;
	static boolean[][] visited;
	static int[][] dp;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		TC = Integer.parseInt(br.readLine());
		for (int t = 1; t <= TC; t++) {
			N = Integer.parseInt(br.readLine());
			board = new int[N][N];
			visited = new boolean[N][N];
			result = Integer.MAX_VALUE;
			dp = new int[N][N];
			for(int i = 0; i < N; i++) {
				String tmp = br.readLine();
				char[] tmpChar = tmp.toCharArray();
				
				for(int j = 0; j < N; j++) {
					board[i][j] = tmpChar[j] -'0';
				}
			}
			
//			for(int[] f : board) {
//				System.out.println(Arrays.toString(f));
//			}
			
			bfs();
			
			sb.append("#" + t + " ").append(result).append("\n");
		}
		System.out.println(sb);
	}
	
	private static void bfs() {
		int[] dx = {1,-1,0,0};
		int[] dy = {0,0,1,-1};
		
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] {0,0,0});
		visited[0][0] = true;
		
		while(!q.isEmpty()) {
			int[] curr = q.poll();
			int len = curr[2];
			
			for(int i = 0; i < 4; i++) {
				int rx = curr[0] + dx[i];
				int ry = curr[1] + dy[i];
				
				if(rx < 0 || ry < 0 || rx >= N || ry >=N) continue;
				if(rx == N-1 && ry == N-1) {
					result = Math.min(result, len);
					continue;
				}
				if(!visited[rx][ry] || (visited[rx][ry] && dp[rx][ry] > len+ board[rx][ry])) {
					q.add(new int[] {rx,ry, len + board[rx][ry]});
					dp[rx][ry] = len + board[rx][ry];
					visited[rx][ry] = true;
				}
			}
			
		}
	}

}
