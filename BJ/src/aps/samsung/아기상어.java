package aps.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 아기상어 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[][] board;
	static Shark shark= new Shark();
	static int timeTocallMom = 0;
	static int[] dx = {-1,0,0,1};
	static int[] dy = {0,-1,1,0};

	static public class Fish{
		int x;
		int y;
		int len;
		
		public Fish(int x, int y, int len) {
			super();
			this.x = x;
			this.y = y;
			this.len = len;
		}
		
	}
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
					board[i][j] = 0;
				}
			}
		}

		while(true) {
			int[] move = checkLen();
			if(move == null) {
				break;
			}
			shark.x = move[0];
			shark.y = move[1];
			board[move[0]][move[1]] = 0;
			
		}
		System.out.println(timeTocallMom);
	}

	private static int[] checkLen() {
		Queue<Fish> fq = new ArrayDeque<Fish>();
		int[] result = null;
		int[][] visited = new int[N][N];
		
		for(int i= 0; i < N; i++) {
			Arrays.fill(visited[i], -1);
		}
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {shark.x, shark.y});
		visited[shark.x][shark.y] = 0;

		while(!q.isEmpty()) {
			int[] current = q.poll();
			
			for(int i = 0; i < 4; i++) {
				int rx = current[0] + dx[i];
				int ry = current[1] + dy[i];
				if(rx < 0 || ry < 0 || rx >=N || ry>= N) continue;
				if(visited[rx][ry] >= 0) continue;
				if(board[rx][ry] == 0 || board[rx][ry] == shark.size) {
					visited[rx][ry] = visited[current[0]][current[1]] + 1;
					q.offer(new int[] {rx,ry});
					continue;
				}
				if(board[rx][ry] < shark.size) {
					fq.offer(new Fish(rx,ry,visited[current[0]][current[1]] + 1));

				}
			}

		}
		
		if(fq.isEmpty()) return null;
		int x = 30;
		int y = 30;
		int distance = 1000;
		
		for(Fish fish : fq) {
			if(fish.len < distance) {
				distance = fish.len;
				x = fish.x;
				y = fish.y;
			}else if (fish.len == distance) {
				if(fish.x < x) {
					x = fish.x;
					y = fish.y;
					distance = fish.len;
				}else if(fish.x == x) {
					if(fish.y < y) {
						x = fish.x;
						y = fish.y;
						distance = fish.len;
					}
				}
			}
		}
		
		timeTocallMom += distance;
		shark.count++;
		shark.check();
		
		return new int[] {x,y};
	}

}
