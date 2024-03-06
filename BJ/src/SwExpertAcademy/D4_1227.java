package SwExpertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D4_1227 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[][] board;
	static StringTokenizer tokens;
	static int start_x, start_y;
	static int end_x,end_y; 
	static boolean[][] visited;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		
		for(int t = 1; t <= 10; t++) {
			board = new int[101][101];
			visited = new boolean[101][101];
			N = Integer.parseInt(br.readLine());
			for(int i = 1; i< 101; i++) {
				String tmp = br.readLine();
				for(int j = 1; j < 101; j++) {
					board[i][j]  = tmp.charAt(j-1) - '0';
					if(board[i][j] == 2) {
						start_x = i;
						start_y = j;
					}else if(board[i][j] == 3) {
						end_x = i;
						end_y = j;
					}
					
				}
			}
			boolean check = dfs(start_x,start_y);
			sb.append("#").append(t).append(" ");
			if(check) {
				sb.append(1).append("\n");
			}else {
				sb.append(0).append("\n");
			}
			
		}
		System.out.println(sb);
	
	}
	private static boolean dfs(int r, int c) {
		if(r == end_x && c == end_y) {
			return true;
		}
		
		for(int i = 0; i < 4; i++) {
			int rx = dx[i] + r;
			int ry = dy[i] + c;
			
			if(rx < 0 || rx > 101 || ry < 0 || ry > 101) continue;
			if(board[rx][ry] == 1 || visited[rx][ry]) continue;
			visited[rx][ry] = true;
			if(dfs(rx,ry)) {
				return true;
			}
			visited[rx][ry] = false;
		}
		
		
		return false;
	}

}
