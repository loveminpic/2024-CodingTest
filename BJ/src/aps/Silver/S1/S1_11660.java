package aps.Silver.S1;
/**
 * @author Minji Lee
 * @date 20240131
 * @link https://www.acmicpc.net/problem/11660
 * @keyword_solution 디피~!
 * @input 
 * @output   
 * @time_complex O(n2)
 * @perf 121676kb 1280ms
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1_11660 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int T, C;
	static StringBuilder result = new StringBuilder();
	static StringTokenizer tokens;
	static int[][] board;
	static int[][] dpboard;
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		T = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		board = new int[T+1][T+1];
		
		for(int i = 1; i <= T; i++) {
			tokens = new StringTokenizer(br.readLine());
			for(int j = 1; j <=T; j++) {
				board[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		
		dpboard = new int[T+1][T+1];
		dpSetting();
		
		for(int i = 0; i <C; i++) {
			tokens = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(tokens.nextToken());
			int y1 = Integer.parseInt(tokens.nextToken());
			int x2 = Integer.parseInt(tokens.nextToken());
			int y2 = Integer.parseInt(tokens.nextToken());
			calculate(x1,y1,x2,y2);
			
			
		}
		System.out.println(result);
	}
	
	public static void dpSetting() {
		
		for(int i = 1 ; i <=T; i++) {
			dpboard[i][1] = board[i][1];
			for(int j = 2; j <=T ; j++) {
				dpboard[i][j] = dpboard[i][j-1] + board[i][j];
			}
			
		}
		
	}
	
	public static void calculate(int x1,int y1, int x2, int y2) {
		int sum = 0;
		
		for(int i = x1; i <=x2; i++) {
			int temp = dpboard[i][y2] - dpboard[i][y1-1];
			sum += temp;
		}
		
		
		result.append(sum).append("\n");
	}

}
