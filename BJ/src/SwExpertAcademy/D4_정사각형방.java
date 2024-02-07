package src.SwExpertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pos{
	int r;
	int c;
	public Pos(int r, int c) {
		this.r = r;
		this.c = c;
		
	}
}

public class D4_정사각형방 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int TC ;
	static StringTokenizer tokens;
	static int[][] board;
	static int[][] result; 
	static int N ;
	static int mx; 
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		TC = Integer.parseInt(br.readLine());
		
		for(int i = 1; i <= TC; i++) {
			N =  Integer.parseInt(br.readLine());
			board = new int[N+1][N+1];
			result = new int[N+1][N+1];
			mx = 0;
			
			for(int j = 1; j <= N; j++) {
				tokens = new StringTokenizer(br.readLine());
				for(int h = 1; h <=N ; h++) {
					int tmp =  Integer.parseInt(tokens.nextToken());
					board[j][h] = tmp;
				}
				
			}
			
			for(int r = 1; r <= N; r++) {
				for(int c = 1; c <=N; c++) {
					bfs(r,c);
				}
			}
			int realone = N * N;
			for(int r = 1; r <= N; r++) {
				for(int c = 1; c <=N; c++) {
					if (result[r][c] == mx) {
						realone = Math.min(realone, board[r][c]);
					}
				}
			}
			sb.append("#").append(i).append(" ").append(realone).append(" ").append(mx+1);
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	public static void bfs(int r, int c) {
		Queue<Pos> q = new LinkedList<>();
		q.add(new Pos(r,c));
		
		while(!q.isEmpty()) {
			int[] x = {0,1,0,-1};
			int[] y = {1,0,-1,0};
			Pos tmp = q.poll();
			
			for(int i = 0; i < 4; i++) {
				int rx = tmp.r + x[i];
				int ry = tmp.c + y[i];
				
				if(rx <= 0 || ry <= 0 || rx > N || ry > N) continue;
				if(board[tmp.r][tmp.c]+1 == board[rx][ry]) {
					result[r][c] += 1;
					mx = Math.max(result[r][c], mx);
					q.add(new Pos(rx,ry));
				}
			}

		}
	}

}
