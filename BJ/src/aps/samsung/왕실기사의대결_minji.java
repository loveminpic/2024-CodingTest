package aps.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 왕실기사의대결_minji {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	// 0,1,2,3 위 오른쪽 아래 왼
	static int[] dx = { -1,0,1,0};
	static int[] dy = {0,1,0,-1};
	
	static int L,N,Q;
	static int[][] board; // 함정 과 벽
	static int[][] command; // 명령
	static List<Knight> knightList = new ArrayList<Knight>();
	
	static public class Knight{
		int r,c,h,w,k;
		boolean die = false;
		int damage = 0;
		
		public Knight(int r, int c, int h, int w, int k) {
			super();
			this.r = r;
			this.c = c;
			this.h = h;
			this.w = w;
			this.k = k;
		}
		
		public void dieCheck() {
			if(this.k <= damage) {
				this.die = true;
			}
		}
		
		
	}
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		L = Integer.parseInt(tokens.nextToken());
		N = Integer.parseInt(tokens.nextToken());
		Q = Integer.parseInt(tokens.nextToken());
		board = new int[L][L]; // original board
		command = new int[Q][2]; 
		
		for(int i = 0; i < L ; i++) {
			tokens = new StringTokenizer(br.readLine());
			for(int j = 0; j < L ; j++) {
				board[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		
		for(int i = 0; i < N; i++) {
			tokens = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(tokens.nextToken());
			int c = Integer.parseInt(tokens.nextToken());
			int h = Integer.parseInt(tokens.nextToken());
			int w = Integer.parseInt(tokens.nextToken());
			int k = Integer.parseInt(tokens.nextToken());
			knightList.add(new Knight(r-1, c-1, h, w, k));
			
		}
		
		
		for(int i = 0; i < Q; i++) {
			tokens = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(tokens.nextToken());
			int d = Integer.parseInt(tokens.nextToken());
			command[i] = new int[] {num-1,d};
		}
		
		/// ---- input
//		System.out.println("1");
//		for (int i = 0; i < L ; i++) {
//			System.out.println(Arrays.toString(board[i]));
//		}
//		System.out.println("2");
//		for (int i = 0; i < L ; i++) {
//			System.out.println(Arrays.toString(personBoard[i]));
//		}
//		
//		for(int[] val : command) {
//			System.out.println(Arrays.toString(val));
//		}
		
		for(int q = 0; q < Q; q++) {
			int num = command[q][0]; // 기사 넘버(리스트 순서)
			int d = command[q][1];   // 움직일 방향 
			Knight curr = knightList.get(num);
			
			boolean result = checkPossible(num);
			
			

			
			
		}
		
	}

}
