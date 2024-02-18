package src.Silver.S1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class S1_16918 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static int R,C,N;
	static char[][] board ;
	static char[][] visited;
	
	public static void main(String[] args) throws IOException {
		
		tokens = new StringTokenizer(br.readLine());
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		N = Integer.parseInt(tokens.nextToken());
		
		board = new char[R][C];
		
		for(int i = 0; i < R; i++) {
			String tmp = br.readLine().toString();
			board[i] = tmp.toCharArray();
//			System.out.println(Arrays.toString(board[i]));
		
		}
		
		visited = new char[R][C];
		int cnt = 1;
		
		while(cnt < N) {
			// 처음 폭탄 위치가 필요 그리고, 그 위치에서 4방으로 폭탄이 터짐. 
			if(cnt == N) {
				break;
			}
			
			cnt++;
			
			if(cnt == N) {
				for(int i = 0; i< board.length; i++) {
					Arrays.fill(board[i], 'O');
				}
				break;
			}
			cnt++;
			boomBoom();
			if(cnt== N) {
				break;
			}
		}
		
		for(char[] tmp : board) {
			for(char tmp2 : tmp) {
				sb.append(tmp2);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static void boomBoom() {
		
		for(int i = 0; i < R; i ++) {
			for(int j = 0; j < C; j++) {
				if(board[i][j] == 'O') {
					rangeBoom(i,j);
				}
			}
		}
		changedVisited();
		
	}


	private static void rangeBoom(int r, int c) {
		visited[r][c] = 'O';
		int[] dx = {-1,1,0,0};
		int[] dy = {0,0,-1,1};
			
		for(int i = 0; i < 4; i++) {
			int dr = dx[i] + r;
			int dc = dy[i] + c;
			
			if(dr < 0 || dr >= R || dc < 0 || dc >= C) continue;
			visited[dr][dc] = 'O';
		}
		
	}
	private static void changedVisited() {
//		board = new char[R][C];
		for(int i = 0; i < R; i ++) {
			for(int j = 0; j< C; j++) {
				if(visited[i][j] == 'O') {
					board[i][j] = '.';
					visited[i][j] = '.';
				}else {
					board[i][j] = 'O';
					visited[i][j] = 'O';
				}
			}
		}

	}

}
