package src.SwExpertAcademy;
/**
 * @author Minji Lee
 * @date 20240131
 * @link https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AY0LFFoqrIIDFAXz&contestProbId=AV5PobmqAPoDFAUq&probBoxId=AY0LFFoqrIMDFAXz+&type=PROBLEM&problemBoxTitle=0129%EC%A3%BC&problemBoxCnt=++5+
 * @keyword_solution 방향 체크, 로직 점검
 * @input 1<= N <= 10
 * @output 배열 출력
 * @time_complex O(N^2)
 * @perf  18624kb 103ms
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D2_달팽이숫자 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int T;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
	
		tokens = new StringTokenizer(br.readLine());
		T = Integer.parseInt(tokens.nextToken());
		
		for(int i = 0; i < T; i++) {
			tokens = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(tokens.nextToken());
			int[][] board = new int[N+1][N+1];
			
			board = makingNewBoard(board, N);
			
			sb.append("#").append(i+1).append("\n");
			
			for(int j = 1; j <= N; j++) {
				for(int h = 1; h <= N; h++) {
					sb.append(board[j][h]).append(" ");
				}
				sb.append("\n");
				
			}
		}
		System.out.println(sb);
	}

	public static int[][] makingNewBoard(int[][] board, int n) {
		int [][] newBoard = new int[n+1][n+1];
		for(int i= 1 ; i <= n ; i++) {
			newBoard[1][i] = i;
		}
		
		int dCnt = 1;
		int number = n;
		
		int rx = 1; 
		int ry = n;
		
		int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}};
		
		while(n > 1) {

			n--;
			
			for(int h = 0; h < 2; h++) {
				for(int i = 1; i <= n; i++) {
					number ++;
					int x = dir[dCnt][0];
					int y = dir[dCnt][1];
					rx += x;
					ry += y;
					newBoard[rx][ry] = number;
					}
				if(dCnt == 3) {
					dCnt = 0;
				}else {
					dCnt++;
				}
			}
			
		}
		return newBoard;
		
	}
}
