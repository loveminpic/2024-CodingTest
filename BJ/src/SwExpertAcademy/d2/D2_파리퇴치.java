package SwExpertAcademy.d2;
/**
 * @author Minji Lee
 * @date 20240201
 * @link https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AY0LFFoqrIIDFAXz&contestProbId=AV5PzOCKAigDFAUq&probBoxId=AY0LFFoqrIMDFAXz+&type=PROBLEM&problemBoxTitle=0129%EC%A3%BC&problemBoxCnt=++6+
 * @keyword_solution 구간합 
 * @input 
 * @output   
 * @time_complex O(N^2)
 * @perf  20328kb 130ms
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D2_파리퇴치 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	static int T, N, M;
	static int max = 0;
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		T = Integer.parseInt(tokens.nextToken());
		
		for(int i = 0; i < T; i++) {
			max = 0;
			tokens = new StringTokenizer(br.readLine());
			N = Integer.parseInt(tokens.nextToken());
			M = Integer.parseInt(tokens.nextToken());
			int[][] board = new int[N+1][N+1];
			
			for(int h = 1; h <= N; h++) {
				tokens = new StringTokenizer(br.readLine());
				for(int f = 1; f <= N; f++) {
					board[h][f] = Integer.parseInt(tokens.nextToken());
				}
			}
			
			int[][] newBoard = checking(N, board);
			findingBig(M, newBoard);
			sb.append("#").append(i+1).append(" ").append(max).append("\n");
		}
		System.out.println(sb);
		
	}
	
	public static void findingBig(int M, int[][] board) {
		int sum = 0;
		for(int i = M; i <= N; i++) {
			for(int j = M; j <=N ; j++) {
				sum = board[i][j];
				sum = sum - board[i-M][j] - board[i][j-M] + board[i-M][j-M];
				if(max < sum) {
					max = sum;
				}
			}
		}
	}
	
	
	public static int[][] checking(int N, int[][]board) {
		int[][] newBoard = new int[N+1][N+1];
		
		for(int i = 1; i <=N; i++) {
			for(int j = 1; j <=N ; j++) {
				newBoard[i][j] = newBoard[i-1][j]+ newBoard[i][j-1] + board[i][j] - newBoard[i-1][j-1];
			}
		}
		return newBoard;
	}

}
