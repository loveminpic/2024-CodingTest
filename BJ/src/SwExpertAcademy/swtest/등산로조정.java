package SwExpertAcademy.swtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 등산로조정 {

	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens ;
	static StringBuilder sb = new StringBuilder();
	
	static int TC, N ,K;
	static int[][] board;
	static boolean[][] visited;
	static int mx = 0;
	static int result = 0;
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		TC = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= TC; tc++) {
			tokens = new StringTokenizer(br.readLine());
			N = Integer.parseInt(tokens.nextToken());
			K = Integer.parseInt(tokens.nextToken());
			result = 0;
			mx = 0;
			board = new int [N][N];
			visited = new boolean[N][N];
			for(int i = 0; i < N; i++) {
				tokens = new StringTokenizer(br.readLine());
				for(int j= 0; j < N; j++){
					board[i][j] = Integer.parseInt(tokens.nextToken());
					mx = Math.max(mx, board[i][j]);
					
				}
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++){
					if (board[i][j] == mx) {
						dfs(i,j,1,false);
					}
					
				}
			}
			sb.append("#" + tc +" " +result +"\n");
		}
		System.out.println(sb);
	}

	private static void dfs(int r, int c, int cnt, boolean used) {
		visited[r][c] = true;
		
		for(int i = 0; i < 4; i++) {
			int rx = r + dx[i];
			int ry = c + dy[i];
			if(rx < 0 || rx >= N || ry < 0 || ry >= N) continue;
			if(visited[rx][ry]) continue;
			if(board[rx][ry] < board[r][c]){
				dfs(rx,ry,cnt+1,used);
			}else {
				if(!used && board[rx][ry] - board[r][c] < K){
					int memory = board[rx][ry];
					int tmp = board[rx][ry] - board[r][c] + 1;
					if(tmp <= K) {
						board[rx][ry] -= tmp;
						if(board[rx][ry] < board[r][c]) {
							dfs(rx,ry,cnt+1,true);
						}else {
							result = Math.max(result, cnt);
						}
						board[rx][ry] = memory;
					}
				}else {
					result = Math.max(result, cnt);
				}
			}			
		}
		visited[r][c] = false;
	}

}
