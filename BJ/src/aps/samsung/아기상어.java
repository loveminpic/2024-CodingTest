package aps.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 아기상어 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[][] board;
	static Shark shark= new Shark();
	
	static int[] dx = {-1,0,0,1};
	static int[] dy = {0,-1,1,0};
	
	static public class Shark{
		int x;
		int y;
		int size = 2;
		int count = 0;
		
		public void check() {
			if(this.size == this.count) {
				this.size++;
				this.count = 0;
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			tokens = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N; j++) {
				board[i][j] = Integer.parseInt(tokens.nextToken());
				if(board[i][j] == 9) {
					shark.x = i;
					shark.y = j;
				}
			}
		}
		
		int maxLen = N *2 -2;
		int[] result;
		for(int i = 1; i <= maxLen; i++) {
			result = checkLen(i);
			if(result != null) break;
		}
		
		
	}

	private static int[] checkLen(int len) {
		int[] result = null;
		
		
		
		
		return result;
	}

}
