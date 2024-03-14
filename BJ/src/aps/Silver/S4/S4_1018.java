package aps.Silver.S4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S4_1018 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int result = Integer.MAX_VALUE;
	static int R,C;
	static boolean[][] board;
	
	public static void main(String[] args) throws IOException {
		
		tokens = new StringTokenizer(br.readLine());
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		board= new boolean[R][C];
		for(int i = 0; i < R; i++) {
			String tmp = br.readLine();
			for(int j = 0 ; j < C; j++) {
				if(tmp.charAt(j) == 'W') {
					board[i][j] = true;
				}else {
					board[i][j] = false;
				}
			}
		}
		
		for(int i = 0; i < R; i++) {
			if(R-i < 8) break;
			for(int j = 0; j < C; j++) {
				if(C-j < 8) break;
				solved(i,j,true);
				solved(i,j,false);
			}
		}
		
		System.out.println(result);
	}

	private static void solved(int r, int c, boolean start) {
		int cnt = 0;
		boolean check = start;
		
		for(int i = r; i < r+8; i++) {
			for(int j = c; j < c+8; j++) {
				if(check != board[i][j]) cnt++;
				check = !check;
				if(j == c+7) {
					check = !check;
				}
			}
		}
		result = Math.min(result, cnt);
	}



}
