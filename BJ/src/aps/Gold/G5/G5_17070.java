package aps.Gold.G5;
/**
 * @author Minji Lee
 * @date 2024.02.28
 * @link https://www.acmicpc.net/problem/17070
 * @keyword_solution dfs
 * @input  (3 ≤ N ≤ 16) / 방법의 수는 항상 1,000,000보다 작거나 같다.
 * @output   (N,N) 까지 도착가능한 경우의 수
 * @time_complex  
 * @perf 12556	264
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G5_17070 {
	static int N;
	static int[][] board;
	static int cnt = 0;
	static int[][] direction = {{0,1},{1,0},{1,1}}; // 0 : 가로, 1: 세로 , 2: 대각선  의 방향 x,y 더하는 값
	static int[][] possibleTo = {{0,2}, {1,2}, {0,1,2}}; // 0: 가로일때, 1: 세로일때 , 2: 대각선일때 갈수 있는 방향 

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];             // 입력 값
		
		for(int i = 0; i < N;  i++) {
			tokens = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		// -----------------------------입력값 END--------------------------------
		
		dfs(0,1,0); // 파이프는 두칸을 차지하고, 가로 방향이기 때문에 (0,1) 부터 체크 하도록 했음
		System.out.println(cnt);
	}

	private static void dfs(int r, int c, int d) {
		
		if(r == N-1 && c == N-1) {
			cnt++;
			return;
		}
		
		// 현재 방향에서 갈수 있는 방향의 갯수만큼 for 문
		for(int idx = 0; idx < possibleTo[d].length; idx++) {
			int num2 = possibleTo[d][idx];
			int rx = direction[num2][0] + r;
			int ry = direction[num2][1] + c;
			
			if(rx < N &&  ry < N) { 
				if(board[rx][ry] == 0) {
					if(num2 == 2 && (board[rx-1][ry] == 1 || board[rx][ry-1] == 1)) continue;
					dfs(rx,ry, num2);
				}
			}
		}
	
	}

}
