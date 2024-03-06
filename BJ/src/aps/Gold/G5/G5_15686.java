package aps.Gold.G5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
class Node3{
	int r;
	int c;
	
	Node3(int r, int c){
		this.r = r;
		this.c = c;
	}
}
public class G5_15686 {
	static int N,M;
	static int [][] board;
	static int [][] count_board;
	static int[] dr = { 1,-1,0,0};
	static int[] dc = {0,0,1,-1};
	static int result = 1000000;
	
	static List<Node3> store_list = new ArrayList<Node3>();
	static List<Node3> house_list = new ArrayList<Node3>();
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	public static void main(String[] args) throws IOException {
		
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		board = new int[N][N];
		count_board = new int[N][N];
		
		for(int i = 0 ; i < N ; i++) {
			tokens = new StringTokenizer(br.readLine());
			for(int j = 0; j< N; j++) {
				board[i][j] = Integer.parseInt(tokens.nextToken());
				if(board[i][j] == 2) {
					store_list.add(new Node3(i,j));	
				}else if(board[i][j] == 1) {
					house_list.add(new Node3(i,j));
				}
			}
		}
		
		// 우선 조합을 만든다!
		combination(0,0, new int[M]);

		System.out.println(result);
	}
	
	private static void combination(int start, int cnt, int[] choosed) {
		if(cnt == M) {
			findMin(choosed,new int[N][N]);
			return;
		}
	
		for(int i = start; i < store_list.size(); i++) {		
			choosed[cnt] = i;
			combination(i+1,cnt+1,choosed);

		}
		
	}

	private static void findMin(int[] choosed, int[][] visited ) {
		int cnt = 0;
		for(int i = 0; i < choosed.length; i++) {
			Node3 tmp = store_list.get(choosed[i]);
			visited[tmp.r][tmp.c] = 2;
		}
		
		for(int i = 0; i < house_list.size(); i++) {
			Node3 tmp = house_list.get(i);
			cnt += bfs(tmp.r,tmp.c, visited);
			if(cnt > result) {
				return;
			}
		}
		
		result = Math.min(result, cnt);
	}

	private static int bfs(int r, int c, int[][] newboard) {
		Queue<Node3> queue = new ArrayDeque<>();
		boolean[][] visited2  = new boolean[N][N];
		queue.add(new Node3(r,c));
		visited2[r][c] = true;
		
		while(!queue.isEmpty()) {
			Node3 tmp = queue.poll();
			
			for(int i = 0; i < 4; i++) {
				int rx = tmp.r + dr[i];
				int ry = tmp.c + dc[i];
				
				if(rx < 0 || rx >= N || ry < 0 || ry >=N) continue;
				if(visited2[rx][ry]) continue;
				if(newboard[rx][ry] == 2) {
					return Math.abs(r-rx)+ Math.abs(c-ry);
				}
				visited2[rx][ry] = true;
				queue.offer(new Node3(rx,ry));
			}
		 
		}
		
		return 0;
	}
	
}

