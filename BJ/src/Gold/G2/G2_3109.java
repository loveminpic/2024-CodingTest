package src.Gold.G2;
/**
 * @author Minji Lee
 * @date 20240218
 * @link https://www.acmicpc.net/problem/3109
 * @keyword_solution dfs, 방문 순서! 
 * @input (1 ≤ R ≤ 10,000, 5 ≤ C ≤ 500)
 * @output  원웅이가 놓을 수 있는 파이프라인의 최대 개수
 * @time_complex  
 * @perf 51968	300
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G2_3109 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int R,C;
	static StringTokenizer tokens;
	static char[][] board;
	static boolean[][] visited;
	static int[] dx = {-1,0,1};
	static int[] dy = {1,1,1};
	static int result = 0;
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		
		board = new char[R][C];
		visited = new boolean[R][C];
		
		for(int i = 0; i < R; i++) {
			String tmp = br.readLine();
			board[i] = tmp.toCharArray();
//			System.out.println(board[i]);
		}
		
		for(int i = 0; i < R; i++) {
			dfs(i,0);
		}
		System.out.println(result);
	}


	private static boolean dfs(int r, int c) {

		for(int i =0; i < 3; i++) {
			int rx = r + dx[i];
			int ry = c + dy[i];
			
			if(rx <0 || rx >=R || ry <0 || ry >= C) continue;
			if(board[rx][ry] == '.') {
				if(ry == C-1) {
					result++;
					return true;
				}
				board[rx][ry] = '-';
				if(dfs(rx,ry)) return true;
			}
		}
		return false;
	}

}
