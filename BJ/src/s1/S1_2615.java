package s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author Minji Lee
 * @date 2024.01.29
 * @link https://www.acmicpc.net/problem/2615
 * @keyword_solution  
 * @input 
 * @output   
 * @time_complex  
 * @perf 
 */

public class S1_2615 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int N = 19;
	static int[][] map = new int[N][N];
	static int[][] board = new int[N][N];
	
	public static void bfs(int x, int y) {
		
	}
	
	public static void main(String[] args) throws IOException {
		
		// 배열에 담아서 인풋값 준비하기
		for(int i = 0; i < N; i++ ) {
			tokens = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		
		for(int i = 0; i< N; i++ ) {
			for(int j = 0; j < N; j++) {
				if(board[i][j] == 0 & map[i][j] != 0) {
					
				}
			}
		}
		

	}

}
