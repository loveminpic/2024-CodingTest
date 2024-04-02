package SwExpertAcademy.swtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 보호필름_2 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int TC, D, W, K;
	static int result;
	static StringBuilder sb = new StringBuilder();
	static int[][] board;

	public static void main(String[] args) throws NumberFormatException, IOException {
		TC = Integer.parseInt(br.readLine());

		for (int t = 1; t <= TC; t++) {
			tokens = new StringTokenizer(br.readLine());

			D = Integer.parseInt(tokens.nextToken()); // 세로
			W = Integer.parseInt(tokens.nextToken()); // 가로
			K = Integer.parseInt(tokens.nextToken()); // 연속되어야 하는 수
			board = new int[D][W];

			for (int i = 0; i < D; i++) {
				tokens = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					board[i][j] = Integer.parseInt(tokens.nextToken());
				}
			}
			result = Integer.MAX_VALUE;
		}	
	}

}
