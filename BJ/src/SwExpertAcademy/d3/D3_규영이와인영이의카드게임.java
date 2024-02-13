package src.SwExpertAcademy.d3;
/**
 * @author Minji Lee
 * @date 2024.02.13
 * @link  https://swexpertacademy.com/main/talk/solvingClub/problemView.do?contestProbId=AWgv9va6HnkDFAW0&solveclubId=AY0LFFoqrIIDFAXz&problemBoxTitle=0212%EC%A3%BC&problemBoxCnt=1&probBoxId=AY2gBgM6OSIDFAXh
 * @keyword_solution 완탐 순열
 * @input 
 * @output    
 * @time_complex 9!
 * @perf 메모리 20,476 kb, 소요시간 3,250 ms 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class D3_규영이와인영이의카드게임 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int TC ;
	static int[] A;
	static int[] B;
	static int winner;
	static int looser;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		TC = Integer.parseInt(br.readLine());
		for(int i = 1; i <= TC; i++) {
			tokens = new StringTokenizer(br.readLine());
			
			A = new int[10];
			B = new int[10];
			
			boolean[] list = new boolean[19];
			// 규영이 패
			for(int j = 1; j <= 9; j++) {
				A[j] = Integer.parseInt(tokens.nextToken());
				list[A[j]] = true;
			}
			// 인영이 패
			int b_cnt = 1;
			for(int j = 1; j <= 18; j++){
				if(list[j] == false) {
					B[b_cnt] = j; 
					b_cnt++;
				}
			}
			winner = 0;
			looser = 0;
			permutation(1, new boolean[B.length], new int[B.length]);
			
			sb.append("#").append(i).append(" ").append(winner).append(" ").append(looser);
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	private static void permutation(int cnt, boolean[] visited, int[]choosed) {
		if(cnt == choosed.length) {
			whosWin(choosed);
			return;
		}
		
		for(int i = 1; i < B.length; i++) {
			if(!visited[i]) {
				visited[i] = true;
				choosed[cnt] = B[i];
				permutation(cnt+1, visited, choosed);
				visited[i] = false;
			}
		}
	}

	private static void whosWin(int[] choosed) {
		int a = 0;
		int b = 0;
		
		for(int i = 1; i <= 9; i++) {
			if(choosed[i] > A[i]) {
				b += choosed[i]+ A[i];
			}else {
				a += choosed[i]+ A[i];
			}
		}
		
		if(a > b) {
			winner++;
		}else if(a<b) {
			looser++;
		}
		
	}

}
