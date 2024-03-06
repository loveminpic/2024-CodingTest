package aps.Silver.S2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S2_1780 { 
	static int N ;
	static int[][] board;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int[] result = {0,0,0};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			tokens = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		
		recursive(0,0,N);
		for(int tmp : result) {
			System.out.println(tmp);
		}
	}

	private static void recursive(int r, int c, int len) {
		
		int start = board[r][c];
		boolean check = true;
		
		for(int i = r; i < r+len; i++) {
			for(int j = c; j < c+len; j++) {
				if(start != board[i][j]) {
					check = false;
					break;
				}
			}
		}
		
		if(check) {
			if(start == -1) {
				result[0] += 1;
			}else if( start == 0) {
				result[1] += 1;
			}else {
				result[2] += 1;
			}
			return;
		}
		
		len = len / 3;
		
		recursive(r,c,len);
		recursive(r,c+len,len);
		recursive(r,c+2*len,len);
		
		recursive(r+len,c,len);
		recursive(r+len,c+len,len);
		recursive(r+len,c+2*len,len);
		
		recursive(r+2*len,c,len);
		recursive(r+2*len,c+len,len);
		recursive(r+2*len,c+2*len,len);
		
	}

}
