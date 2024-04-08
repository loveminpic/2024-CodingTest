package SwExpertAcademy.swtest;

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
	static int[][] board;
	static int[][] personBoard;
	static List<Fighter> list = new ArrayList<Fighter>();
	
	static public class Fighter{
		int r,c,h,w,k;
		boolean die = false;
		int damage = 0;
		

		public Fighter(int r, int c, int h, int w, int k) {
			super();
			this.r = r;
			this.c = c;
			this.h = h;
			this.w = w;
			this.k = k;
		}
		
	}
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		L = Integer.parseInt(tokens.nextToken());
		N = Integer.parseInt(tokens.nextToken());
		Q = Integer.parseInt(tokens.nextToken());
		board = new int[L][L];
		personBoard = new int[L][L];
		
		for(int i = 0; i < L; i++) {
			Arrays.fill(personBoard[i],-1);
		}
		
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
			list.add(new Fighter(r-1, c-1, h, w, k));
			
			for(int a = r-1; a < r-1+h ; a++) {
				for(int b = c-1; b <c-1+w ; b++) {
					personBoard[a][b] = i;
				}
			}
		}
		
//		for(int i = 0; i < L; i++) {
//			System.out.println(Arrays.toString(personBoard[i]));
//		}
		
		while(Q -- > 0) {
			tokens = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(tokens.nextToken());
			int orderD = Integer.parseInt(tokens.nextToken());
			
			num -= 1; // 실제로 0번이 1번이니까. 
			
			moveFighter(num, orderD);
			
		}
		
		
	}
	
	private static void moveFighter(int num, int orderD) {
		Queue<int[]> q = new ArrayDeque<int[]>();
		
		for(int i = 0; i < L ; i++) {
			for(int j = 0; j < L ; j++) {
				if(personBoard[i][j] == num ) {
					q.add(new int[] { i,j,num});
				}
			}
		}
	}

}
