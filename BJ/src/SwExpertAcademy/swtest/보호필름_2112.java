package SwExpertAcademy.swtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class 보호필름_2112 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int TC, D, W, K;
	static int result;
	static List<int[]> combiList = new ArrayList<int[]>();
	static StringBuilder sb = new StringBuilder();
	static int[] a, b;
	static int[][] board;

	public static void main(String[] args) throws NumberFormatException, IOException {
		TC = Integer.parseInt(br.readLine());

		for (int t = 1; t <= TC; t++) {
			tokens = new StringTokenizer(br.readLine());

			D = Integer.parseInt(tokens.nextToken()); // 세로
			W = Integer.parseInt(tokens.nextToken()); // 가로
			K = Integer.parseInt(tokens.nextToken()); // 연속되어야 하는 수
			board = new int[D][W];
			result = 0;

			for (int i = 0; i < D; i++) {
				tokens = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					board[i][j] = Integer.parseInt(tokens.nextToken());
				}
			}
			result = Integer.MAX_VALUE;
			changeFilm(0, 0, new int [D]);
			
			
			sb.append("#" + t + " ").append(result).append("\n");
		}
		System.out.println(sb);
	}

	private static void changeFilm(int r, int injectCnt, int[] status) {

		// 2. 기저조건
		if (r == D) {
			// 상황을 파악하자.
			if(check(status)) {
				result = Math.min(result, injectCnt);
			}
			return;
		}

		// 1. 재귀조건 - 각각의 층에서 해볼 수 있는 시도
		for (int i = -1; i < 2; i++) {
			status[r] = i; // 현재 층에는 이 약을 쓰겠다.
			if (i == -1) {
				changeFilm(r + 1, injectCnt, status);
			}
			// 약을 주입하는 경우
			else {
				changeFilm(r + 1, injectCnt + 1, status);
			}
		}

	}

	private static boolean check(int[] status) {
		for (int c = 0; c < W; c++) {
			int base = 0;
			int k = 0;
			for (int d = 0; d < D; d++) {
				if (d == 0) {
					base = status[0] == -1 ? board[0][c] : status[0];
					k=1;
				}else {
					int target = status[d] == -1 ? board[d][c] : status[d];
					if(target==base) {
						k++;
						if(k==K) {
							break;
						}
					}else {
						base = target;
						k=1;
					}
				}
			}
			if(k<K) {
				return false;
			}
		}
		return true;
	}

}
