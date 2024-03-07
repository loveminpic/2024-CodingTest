package aps.samsung;

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
		int s;	
		int d;
		
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
		
		board = new int[N][N];
		
		for(int i = 0; i < M ; i ++) {
			tokens = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(tokens.nextToken());
			int c = Integer.parseInt(tokens.nextToken());
			int w = Integer.parseInt(tokens.nextToken());
			int s = Integer.parseInt(tokens.nextToken());
			int d = Integer.parseInt(tokens.nextToken());
			q.add(new FireBall(r-1, c-1, w, s, d));
			board[r-1][c-1] += 1;
		}
		
		
		
		while(K-- > 0) {
			
			int size = q.size();
			List<int[]> callList = new ArrayList<>();
			boolean[][] visited = new boolean[N][N];
			
			while(size -- > 0) {
				FireBall cur = q.poll();
				board[cur.r][cur.c] -= 1;
				
				int rx = (cur.r + dx[cur.d] * (cur.s % N) + N)  % N;
				int ry = (cur.c + dy[cur.d] * (cur.s % N) + N)  % N;
				
				cur.r = rx;
				cur.c = ry;
				
				board[rx][ry] += 1;
				q.add(cur);	
				
				int[] m = new int[] {rx,ry};
				if(!visited[rx][ry]) {
					callList.add(m);
					visited[rx][ry] = true;
				}
			}
			
			for(int i = 0; i < callList.size(); i++) {
				int[] tmp = callList.get(i);
				if(board[tmp[0]][tmp[1]] >= 2) {
					happening(tmp[0],tmp[1]);
				}
			}
		}
		
		int result = 0;
		for(FireBall tmp : q) {
			result += tmp.w;
		}
		System.out.println(result);
	}
	
	private static void happening(int r, int c) {
		int size = q.size();
		int w = 0;
		int s = 0;
		
		int jjak = 0;
		int hole = 0;
		
		for(int i = 0; i < size; i++) {
			FireBall curr = q.poll();
			if(curr.r == r && curr.c == c) {
				w += curr.w;
				s += curr.s;
				if(curr.d % 2 == 0) {
					jjak += 1;
				}else {
					hole += 1;
				}
			}else {
				q.offer(curr);
			}
		}
		 
		w = w / 5;
		s = s / board[r][c];
		
		int[] d;
		if(jjak == 0 || hole == 0) {
			d = new int[] {0,2,4,6};
		}else {
			d= new int[] {1,3,5,7};
		}
		
		if(w > 0) {
			for(int i = 0; i < 4 ; i++) {
				q.add(new FireBall(r, c, w, s, d[i]));	
			}
			board[r][c] = 4;
		}else {
			board[r][c] = 0;
		}
		
	}

}