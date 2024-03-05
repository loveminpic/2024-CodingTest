package aps.Silver.S5;
/**
 * @author Minji Lee
 * @date 20240207
 * @link https://www.acmicpc.net/problem/2563
 * @keyword_solution  순회
 * @input 100 *100 이 넘지 않는 도화지 내에 색종이의 시작점
 * @output 색종이의 넓이
 * @time_complex O(N^2)
 * @perf  14184kb 116ms
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S5_2563 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int T;
	static StringTokenizer tokens;
	static int[][] board = new int[101][101];
	static int count = 0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		T = Integer.parseInt(br.readLine());
		
		for( int i = 0; i < T; i++) {
			tokens = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(tokens.nextToken());
			int b = Integer.parseInt(tokens.nextToken());
			for(int j = a; j < a+10; j++) {
				for(int h = b; h <b+10; h++) {
					if(board[j][h] == 0) {
						board[j][h] = 1;
						count++;
					}
							
					
				}
			}
		
		}
		System.out.println(count);
		
	}

}
