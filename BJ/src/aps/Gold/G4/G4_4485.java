package aps.Gold.G4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class G4_4485 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int N;
	static StringBuilder sb = new StringBuilder();

	static int cnt = 0;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	static int count =0;
	public static void main(String[] args) throws NumberFormatException, IOException {

		while (true) {
			cnt++;
			N = Integer.parseInt(br.readLine());
			if (N == 0) {
				break;
			}

			sb.append("Problem ").append(cnt).append(": ");
			int[][] board = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				tokens = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(tokens.nextToken());
				}
			}

			solved(board, new int[N][N]);

		}

		System.out.println(sb);
	}
	
	private static void solved(int[][] board, int[][] newBoard) {
	    boolean[][] visited = new boolean[N][N];
	    for(int i = 0; i < N; i++) {
	        Arrays.fill(newBoard[i], Integer.MAX_VALUE);
	    }
	    
	    newBoard[0][0] = board[0][0];
	    Queue<Pos> q = new ArrayDeque<>();
	    q.add(new Pos(0,0));
	    visited[0][0] = true; 

	    while(!q.isEmpty()) {
	        Pos current = q.poll();
	        
	        for(int i = 0; i < 4; i++) {
	            int rx = dx[i] + current.x;
	            int ry = dy[i] + current.y;
	            
	            if(rx < 0 || ry < 0|| rx >= N || ry >= N) continue;
	            if(newBoard[rx][ry] > newBoard[current.x][current.y] + board[rx][ry]) {
	                newBoard[rx][ry] = newBoard[current.x][current.y] + board[rx][ry];
	               
	                q.offer(new Pos(rx,ry));
	                   
	            }
	        }
	    }
	    sb.append(newBoard[N-1][N-1]).append("\n");
	}
	
	static public class Pos {
		int x;
		int y;

		
		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
			
		}
		

	}

}
