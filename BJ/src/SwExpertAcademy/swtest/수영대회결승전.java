package SwExpertAcademy.swtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 수영대회결승전 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	static int TC, N;
	static int[] start;
	static int[] end;
	
	static int[][] board;
	static boolean[][] visited;
	
	static int result;
	static int count = 0;
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
	
		TC = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());
			
			board = new int[N][N]; // 초기화
			visited = new boolean[N][N]; // 초기화
			result = -1;
			start = new int[2];
			end = new int[2];
			
			for(int i = 0; i < N; i++) {
				tokens = new StringTokenizer(br.readLine());
				for(int j = 0; j <N; j++) {
					board[i][j] = Integer.parseInt(tokens.nextToken());
				}
			}
			
			tokens = new StringTokenizer(br.readLine());
			start[0] = Integer.parseInt(tokens.nextToken());
			start[1] = Integer.parseInt(tokens.nextToken());
			tokens = new StringTokenizer(br.readLine());
			end[0] = Integer.parseInt(tokens.nextToken());
			end[1] = Integer.parseInt(tokens.nextToken());
			
			//------------------ input end ------------------
			
		
			bfs();
			
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}

	private static void bfs() {
		int[] dx = {1,-1,0,0};
		int[] dy = {0,0,1,-1};
		
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(start);
		visited[start[0]][start[1]] = true;
		int time = 0; 
		
		while(!q.isEmpty()) {
			int size = q.size();
			
			while(size -- > 0) {
				int[] curr = q.poll();
				
				for(int i = 0; i < 4; i++) {
					int rx = dx[i] + curr[0];
					int ry = dy[i] + curr[1];
					
					if(rx < 0 || ry < 0 || rx >= N || ry >= N) continue;
					if(board[rx][ry] == 1) continue;
					if(visited[rx][ry]) continue;
					if(board[rx][ry] == 2) {
						if(time % 3 != 2) {
							q.offer(curr.clone());
							continue;
						}
					}
					if(rx == end[0] && ry == end[1]) {
						result = time + 1;
						return;
					}
					
					q.offer(new int[] {rx,ry});
					visited[rx][ry] = true;
				}
					
			}
			time++;
		}
		
	}

	

}
