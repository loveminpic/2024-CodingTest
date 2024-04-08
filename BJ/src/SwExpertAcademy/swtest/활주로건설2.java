package SwExpertAcademy.swtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 활주로건설2 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	static int TC;
	static int N, X;
	static int[][] board;
	static int result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		TC = Integer.parseInt(br.readLine());
		for (int t = 1; t <= TC; t++) {
			tokens = new StringTokenizer(br.readLine());
			N = Integer.parseInt(tokens.nextToken());
			X = Integer.parseInt(tokens.nextToken());

			board = new int[N][N];
			result = 0;

			for (int i = 0; i < N; i++) {
				tokens = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(tokens.nextToken());
				}
			}

			int[] tmp = new int[N];
			for (int i = 0; i < N; i++) {
				tmp = board[i];
				solved(tmp);
			}

			for (int i = 0; i < N; i++) {
				tmp = new int[N];
				for (int j = 0; j < N; j++) {
					tmp[j] = board[j][i];
				}
				solved(tmp);
			}

			sb.append("#" + t + " ").append(result).append("\n");
		}
		System.out.println(sb);
	}

	private static void solved(int[] tmp) {
		boolean[] visited = new boolean[N];		
		int curr = tmp[0];
		
		for(int i = 1; i < N ; i++) {
			
			if(curr == tmp[i]) continue; 
		
			// 큰 -> 작은 
			if(curr - tmp[i] == 1) {
				if(i+X >= N) return;
				for(int j = i+1; j < i+X; j++) {
					if(tmp[j] == tmp[j-1] && !visited[j] ) {
						visited[j] = true;
					}else {
						return;
					}
				}
				// 작은 -> 큰
			}else if(tmp[i] - curr == 1) {
				if(i-X < 0) return;
				for(int j = i-X+1; j < i; j++) {
					if(tmp[j] == tmp[j-1] && !visited[j]) {
						visited[j] = true;
					}else {
						return;
					}
				}
			}
			else {
				return;
			}
		}
		
		result ++;
	}

}
