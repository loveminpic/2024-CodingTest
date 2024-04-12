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
	static List<Integer> updateNum = new ArrayList<>();
	
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
		
		
		for(int q = 0; q < Q; q++) {
			int num = command[q][0]; // 기사 넘버(리스트 순서)
			int d = command[q][1];   // 움직일 방향 
	
			boolean result = checkPossible(num, d);
			if(result) {
				// 좌표 변경해주기, 타격입은 기사들 체력 .. 없애주깅..ㅎ 
				System.out.println("q : " + q);
				for(int i = 0; i < updateNum.size(); i++) {
					System.out.println(updateNum.get(i));
				}
			}
			
			
		}
		
	}
	private static boolean checkPossible(int num, int d) {
		
		Knight curr = knightList.get(num);
		boolean flag = true; 
		
		int sr = curr.r + dx[d];
		int sc = curr.c + dy[d];
		
		int er = curr.r + curr.h - 1 + dx[d]; // 현재 방패 end 지점 
		int ec = curr.c + curr.w - 1 + dy[d]; 
		
		if(sr < 0 || sc <0 || sr >= L || sc >= L) return false; // 만약 배열 넘어가면 
		if(er < 0 || ec <0 || er >= L || ec >= L) return false; // 만약 배열 넘어가면
		
		// 만약 벽이면 리턴 
		for(int i = sr; i < sr+curr.h-1; i++) {
			for(int j = sc; j < sc+curr.w -1; j++) {
				if(board[i][j] == 2) return false;
			}
		}
		
				
		for(int i = 0; i < knightList.size(); i++) {
			if(i == num || knightList.get(i).die) continue;
			
			Knight checkNight = knightList.get(i);
			int sr2 = checkNight.r ;
			int sc2 = checkNight.c;
			
			int er2 = checkNight.r + checkNight.h - 1;
			int ec2 = checkNight.c + checkNight.w - 1;
			
			if(sr <= sr2 || er2 <= er || sc <= sc2 || ec2 <= ec) {
				flag = checkPossible(i, d);
			}else {
				break;
			}
			
			if(flag) {
				updateNum.add(num);
			}

		}
		
		return flag;
	}

}
