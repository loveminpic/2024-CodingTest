package SwExpertAcademy.swtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 벌꿀채취 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int N, M, result, TC,C;
	static int[][] board;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		TC = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <=TC; tc++) {
			tokens = new StringTokenizer(br.readLine());
			N = Integer.parseInt(tokens.nextToken());
			M = Integer.parseInt(tokens.nextToken());
			C = Integer.parseInt(tokens.nextToken());
			result = 0;
			board = new int[N][N];
			for(int i = 0; i < N; i++) {
				tokens = new StringTokenizer(br.readLine());
				for(int j = 0; j <N; j++) {
					board[i][j] = Integer.parseInt(tokens.nextToken());
				}
			}
			// ---- input ----
			
			solved(new boolean[N][N], new int[M * 2], 0);
			sb.append("#" +tc+ " " + result + "\n");
		}
		System.out.println(sb);
	}

	private static void solved(boolean[][] visited, int[] choosed , int cnt) {
		if(cnt == M*2) {
			int tmp = findingMax(choosed);
			result = Math.max(result, tmp);
			return;
		}
	
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(visited[i][j]) continue;
				if(j+M > N) continue;
				int count  = 0;
				boolean possible = checkPossible(visited, i, j); // 이미 선택된게 선택되지 않도록 확인 
				
				if(possible) {
					if(j+M <= N) { // M 만큼 갔을 때, 배열을 넘지 않는지 확인 
						for(int m = j ; m < j+M; m++) {
							choosed[cnt] = board[i][m];
							cnt++;
							count += board[i][m];
							visited[i][m] = true;
						}
						
						solved(visited, choosed, cnt);
						
						//원복 
						for(int m = j ; m < j+M; m++) {
							cnt--;
							visited[i][m] = false;
						}
					}
				}
				
				
			}
		}
	}

	private static int findingMax(int[] choosed) {
		int[] a = new int[M];
		int[] b = new int[M];
		
		for(int i = 0; i < choosed.length; i++) {
			if(i < M) {
				a[i] = choosed[i];
			}else {
				b[i-M] = choosed[i];
			}
		}
		int resultA = powerSet(a, new boolean[M], 0,0); // 해당 값의 부분집합을 구해서, C가 넘지 않는 max 값 찾도록 
		int resultB = powerSet(b, new boolean[M], 0,0);
		
		return resultA+resultB;
	}

	private static int powerSet(int[] a, boolean[] vs, int mx, int idx) {
		if(idx == M) {
			int cnt = 0;
			int rr = 0;
			for(int i= 0; i < vs.length; i++) {
				if(vs[i]) {
					cnt+= a[i];
					rr += a[i] * a[i];
				}
			}
			if(cnt <= C) {
				mx = Math.max(mx, rr);
			}
			return mx;
		}
	
		vs[idx] = true;
		mx = powerSet(a, vs, mx, idx+1);
		vs[idx] = false;
		mx = powerSet(a, vs, mx, idx+1);
		
		return mx;
	}

	private static boolean checkPossible(boolean[][] visited, int i, int j) {
		for(int m = j ; m < j+M; m++) {
			if(visited[i][m]) return false;
			
		}
		return true;
	}

}
