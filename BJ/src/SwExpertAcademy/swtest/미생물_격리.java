package SwExpertAcademy.swtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 미생물_격리 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	static int TC;
	static int N, M, K;
	static int result;
	
	// 상,하,좌,우
	static int[] dx = {0,-1,1,0,0};
	static int[] dy = {0,0,0,-1,1};
	static Queue<Sepo> sepoQ = new ArrayDeque<>();
	
	static public class Sepo {
		int x;
		int y;
		int sepoCount;
		int d;

		
		public Sepo(int x, int y, int sepoCount, int d) {
			super();
			this.x = x;
			this.y = y;
			this.sepoCount = sepoCount;
			this.d = d;
			
		}
		
		
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		TC = Integer.parseInt(br.readLine());
		for(int t = 1; t <=TC; t++) {
			tokens = new StringTokenizer(br.readLine());
			N = Integer.parseInt(tokens.nextToken());
			M = Integer.parseInt(tokens.nextToken());
			K = Integer.parseInt(tokens.nextToken());
			

			for(int i = 0; i < K; i++) {
				tokens = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(tokens.nextToken());
				int y = Integer.parseInt(tokens.nextToken());
				int cnt = Integer.parseInt(tokens.nextToken());
				int d = Integer.parseInt(tokens.nextToken());
				sepoQ.add(new Sepo(x,y,cnt,d));
			}
			while(M -- > 0) {
				
				moveSepos();
			
			}
			sb.append("#").append(t).append(" ").append(result);
		}
	}

	private static void moveSepos() {
		int[][] board = new int[N][N];
		for(int i = 0; i < N; i++) {
			
		}
		
		for(int i = 0; i < sepoQ.size(); i++ ) {
			Sepo curr = sepoQ.poll();
			
			int rx = curr.x + dx[curr.d];
			int ry = curr.y + dy[curr.d];
			
			if(rx == 0 || ry == N-1) {
				curr.sepoCount = curr.sepoCount / 2;
				curr.d = changedD(curr.d);
			}
			
			if(curr.sepoCount <= 0) continue;
			
			if(board[rx][ry] != 0) {
				
			}
			sepoQ.add(curr);
			
			
		}
		
		
	}

	private static int changedD(int d) {
		int newD = 0;
		switch(d) {
		case 1:
			newD = 2;
			break;
		case 2:
			newD = 1;
			break;
		case 3:
			newD = 4;
			break;
		case 4:
			newD = 3;
			break;
		}
		return newD;
	}

}
