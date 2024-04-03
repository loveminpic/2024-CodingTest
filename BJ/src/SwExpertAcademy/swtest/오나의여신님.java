package SwExpertAcademy.swtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 오나의여신님 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens ;
	static int TC;
	static int N,M;
	static char[][] board;
	static int[][] visited;
	static int result;
	static Queue<int[]> qD = new ArrayDeque<int[]>();
	static Queue<int[]> qS = new ArrayDeque<int[]>();
	static int[] suyeon = new int[3];
	static int[] haven = new int[2];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		TC = Integer.parseInt(br.readLine());
		for(int t = 1; t <= TC; t++) {
			tokens = new StringTokenizer(br.readLine());
			N = Integer.parseInt(tokens.nextToken());
			M = Integer.parseInt(tokens.nextToken());
			
			board = new char[N][M];
			visited = new int[N][M];
			result = -1;
			qD = new ArrayDeque<int[]>();
			qS = new ArrayDeque<int[]>();
			
			for(int i = 0; i < N; i++) {
				String tmp = br.readLine();
				board[i] = tmp.toCharArray();
				for(int j = 0; j < M; j++) {
					if(board[i][j] == 'D') {
						haven[0] = i;
						haven[1] = j;
					}else if(board[i][j] == '*') {
						qD.add(new int[] {i,j});
					}else if(board[i][j] == 'S') {
						qS.add(new int[] {i,j});
						suyeon[0] = i;
						suyeon[1] = j;
						suyeon[2] = 0;
					}
				}
			}
			
			bfs();
			
			if(result == -1) {
				sb.append("#" + t + " ").append("GAME OVER").append("\n");
			}else {
				sb.append("#" + t + " ").append(result-1).append("\n");
			}
		}
		System.out.println(sb);
	}

	private static void bfs() {
		int[] dx = {0,0,1,-1};
		int[] dy = {1,-1,0,0};

		visited[suyeon[0]][suyeon[1]] = 1;
		
		while(!qS.isEmpty()) {
			int sizeD = qD.size();
			
			while(sizeD -- > 0) {
				int[] curr = qD.poll();
				for(int i = 0; i < 4; i++) {
					int rx = curr[0] + dx[i];
					int ry = curr[1] + dy[i];
					
					if(rx < 0 || ry < 0 || rx >= N || ry >= M) continue;
					if(board[rx][ry] == '*') continue;
					if(board[rx][ry] == 'X') continue;
					if(board[rx][ry] == 'D') continue;	
					qD.offer(new int[] {rx,ry});
					board[rx][ry] = '*';
				}
				
			}
			
			int sizeS = qS.size();
			
			while(sizeS -- > 0) {
				int[] curr = qS.poll();
				int len = visited[curr[0]][curr[1]];
				for(int i = 0; i < 4; i++) {
					int rx = curr[0] + dx[i];
					int ry = curr[1] + dy[i];
					
					if(rx < 0 || ry < 0 || rx >= N || ry >= M) continue;
					if(visited[rx][ry] > 0) continue;
					if(board[rx][ry] == '*') continue;
					if(board[rx][ry] == 'X') continue;
					if(board[rx][ry] == 'D') {
						result = len + 1;
						return;
					}

					qS.add(new int[] {rx,ry, len+1});
					visited[rx][ry] = len + 1;
				}
			}
			
			
		}
		
		
		
		
		
	}

}
