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
	static int[][] visited;
	
	static Queue<Pos> tonaido ;
	static int result;
	static int count = 0;
	
	static public class Pos{

		int x;
		int y;
		int before = -1;
		
		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
	
		TC = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());
			
			board = new int[N][N]; // 초기화
			visited = new int[N][N]; // 초기화
			tonaido = new ArrayDeque<>(); // 초기화 
			result = 0;
			start = new int[2];
			end = new int[2];
			
			for(int i = 0; i < N; i++) {
				tokens = new StringTokenizer(br.readLine());
				for(int j = 0; j <N; j++) {
					board[i][j] = Integer.parseInt(tokens.nextToken());
					if(board[i][j] == 2) {
						board[i][j] = -2;
						tonaido.offer(new Pos(i,j));
					}
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
			
			sb.append("#").append(tc).append(" ").append(result-1).append("\n");
		}
		System.out.println(sb);
	}

	private static void bfs() {
		int[] dx = {1,-1,0,0};
		int[] dy = {0,0,1,-1};
		
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {start[0], start[1]});
		visited[start[0]][start[1]] = 1;
		
		while(!q.isEmpty()) {
			count++;
			int size = q.size();
			while(size --> 0) {
				int[] current = q.poll();

				for(int i = 0; i < 4; i++) {
					int rx = current[0] + dx[i];
					int ry = current[1] + dy[i];
					
					if(rx < 0 || ry < 0 || rx >= N || ry >= N) continue;
					if(visited[rx][ry] > 0 || board[rx][ry] == 1) continue;
					if(board[rx][ry] == 0) {
						visited[rx][ry] = visited[current[0]][current[1]] + 1;
						q.offer(new int[] {rx,ry});
					}else if(board[rx][ry] < 0) {
						q.offer(current);
					}
					if(rx == end[0] && ry == end[1]) {
						result = visited[rx][ry];
						return;
					}
				}
			}
			removeTonaido();
		}	
		
	}

	private static void removeTonaido() {
		int size = tonaido.size();
		
		for(int i = 0; i < size; i++) {
			int[] tmp = tonaido.poll();
			if(board[tmp[0]][tmp[1]] == -2) {
				board[tmp[0]][tmp[1]] = -1;
			}else if(board[tmp[0]][tmp[1]] == -1) {
				board[tmp[0]][tmp[1]] = 0;
			}else if(board[tmp[0]][tmp[1]] == 0) {
				board[tmp[0]][tmp[1]] = -2;
			}
			tonaido.add(tmp);
		}
	}

}
