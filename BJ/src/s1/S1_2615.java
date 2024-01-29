package s1;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
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
	static int winner;
	static int a,b;

	
	public static boolean bfs(int x, int y, int win) {
		
		int cnt = 1;
		
		int[] i = {1,1,1,0,-1,-1,-1,0};
		int[] j = {1,0,-1,-1,-1,0,-1,1};

		for(int m = 0; m < 8; m++) {
			int rx = x + i[m];
			int ry = y + j[m];
			
			if(0 > rx | rx > N | 0 > ry | ry > N) continue;
			if(map[rx][ry] != win) continue;
			
			int temp_x = rx + i[m];
			int temp_y = ry + j[m];
			cnt++; 
			
			for(int p = 0; p < 3; p++) {
				if(map[temp_x][temp_y] == win) {
					temp_x += i[m];
					temp_y += j[m];
					cnt++; 
				}else {
					return false;
				}
			}
			
			if(cnt == 5) {
				winner = win;
				a = x + 1;
				b = y + 1;
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		
		// 배열에 담아서 인풋값 준비하기
		for(int i = 0; i < N; i++ ) {
			tokens = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		
		start : for(int i = 0; i< N; i++ ) {
			for(int j = 0; j < N; j++) {
				if(map[i][j] != 0) {
					boolean temp = bfs(i,j, map[i][j]);
					if(temp == true) {
						break start;
					}	
				}
			}
		}
		System.out.println(winner);
		System.out.println(a + " " + b);

	}

}
