package src.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 마법사상어와파이어볼 {

	static class FireBall {
		int r;
		int c;
		int w;
		int d;
		int s;
		
		public FireBall(int r, int c, int w,  int s, int d) {
			super();
			this.r = r;
			this.c = c;
			this.w = w;
			this.s = s;
			this.d = d;
		
		}
	}
	
	static int[] dx = {-1,-1,0,1,1,1,0,-1};
	static int[] dy = {0,1,1,1,0,-1,-1,-1};
	static int N,M,K;
	static int[][] board;
	static Queue<FireBall> q = new ArrayDeque<FireBall>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		
		board = new int[N+1][N+1];
		
		for(int i = 0; i < M ; i ++) {
			tokens = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(tokens.nextToken());
			int c = Integer.parseInt(tokens.nextToken());
			int m = Integer.parseInt(tokens.nextToken());
			int s = Integer.parseInt(tokens.nextToken());
			int d = Integer.parseInt(tokens.nextToken());
			q.add(new FireBall(r, c, m, s, d));
			board[r][c] += 1;
		}
		
		while(K-- > 0) {
			
			int size = q.size();
			while(size -- > 0) {
				FireBall currBall = q.poll();
				board[currBall.r][currBall.c] -= 1;
				
				int rx = currBall.r + (dx[currBall.d] * currBall.s);
				int ry = currBall.c + (dy[currBall.d] * currBall.s);
				
				if(rx <= 0 || rx > N || ry <= 0 || ry > N ) {
					if(currBall.d == 0 || currBall.d == 4) {
						rx = rx % N;
					}else if (currBall.d == 6 || currBall.d == 2) {
						ry = ry % N;
					}else{
						continue;
					}
				}
				board[rx][ry] += 1;
				currBall.r = rx;
				currBall.c = ry;
				q.add(currBall);
			}
			
			for(int i = 0; i <= N; i++) {
				for(int j = 0; j <= N; j++) {
					if(board[i][j] >= 2) {
						happening(i,j, board);
					}
				}
			}
			
			
		}
		int result = 0;
		for(FireBall tmp : q) {
			result += tmp.w;
		}
		System.out.println(result);
	}
	
	private static void happening(int r, int c, int[][] board) {
		List<FireBall> list = new ArrayList<FireBall>();
		int size = q.size();
		for(int i = 0; i < size; i++) {
			FireBall curr = q.poll();
			if(curr.r == r && curr.c == c) {
				board[curr.r][curr.c] -= 1;
				list.add(curr);
			}else {
				q.add(curr);
			}
		}
		int w = 0;
		int s = 0;
		
		int jjak = 0;
		int hole = 0;
		
		for(int i = 0; i < list.size(); i++) {
			w += list.get(i).w;
			s += list.get(i).s;
			if(list.get(i).d % 2 == 0) {
				jjak += 1;
			}else {
				hole += 1;
			}
		}
		w = w/5;
		s = s/list.size();
		int[] d;
		if(jjak == 0 || hole == 0) {
			d = new int[] {0,2,4,6};
		}else {
			d= new int[] {1,3,5,7};
		}
		
		for(int i = 0; i < 4 ; i++) {
			FireBall tmp = new FireBall(r, c, w, s, d[i]);
			q.add(tmp);
			
		}
		board[r][c] += 4;
		board[r][c] -= list.size();
		
		size = q.size();
		for(int i = 0; i < size; i ++) {
			FireBall tmp = q.poll();
			if(tmp.w == 0) {
				board[tmp.r][tmp.c] -= 1;
			}else {
				q.add(tmp);
			}
		}
	}

}
