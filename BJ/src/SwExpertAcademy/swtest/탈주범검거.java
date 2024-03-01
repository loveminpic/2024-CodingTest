package src.SwExpertAcademy.swtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 탈주범검거 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	
	static int TC, R,C,HR,HC,L;
	static int[][] board;
	static int[][] pipe = {{0},{1,2,3,4},{1,2},{3,4},{1,4},{2,4},{2,3},{1,3}};
	static boolean[][] visited;
	// 상 하 좌 우 
	static int[][] d = {{0,0},{-1,0},{1,0},{0,-1},{0,1}} ;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		TC = Integer.parseInt(br.readLine());
		
		for(int tc =1; tc <= TC; tc++) {
			tokens = new StringTokenizer(br.readLine());
			R = Integer.parseInt(tokens.nextToken());
			C = Integer.parseInt(tokens.nextToken());
			HR = Integer.parseInt(tokens.nextToken());
			HC = Integer.parseInt(tokens.nextToken());
			L = Integer.parseInt(tokens.nextToken());
			
			board = new int[R][C];
			visited = new boolean[R][C];
			
			for(int i = 0; i < R;i++) {
				tokens = new StringTokenizer(br.readLine());
				for(int j = 0; j < C; j++) {
					board[i][j] = Integer.parseInt(tokens.nextToken());
				}
			}
			
			bfs();
			// --- INPUT END ---
			int result = 0;
			for(boolean[] i : visited) {
				for(boolean j : i) {
					if(j) result++;
				}
			}
			
			sb.append("#" + tc + " " + result + "\n");
			
		}
		System.out.println(sb);
	}

	private static void bfs() {
		Queue<int[]> q = new ArrayDeque<int[]>();
		int[] tmp = {HR, HC};
		q.offer(tmp);
		visited[HR][HC] = true;

		while(!q.isEmpty()) {
			L--;
			int size = q.size();
			if(L == 0) {
				return;
			}
			while(size-- > 0) {
				int[] curr = q.poll();
				int currPipeNum = board[curr[0]][curr[1]];
				for(int i = 0; i < pipe[currPipeNum].length; i++) {
					int dnum = pipe[currPipeNum][i];
					int rx = curr[0] + d[dnum][0];
					int ry = curr[1] + d[dnum][1];
	
					if(rx < 0 || rx >= R || ry < 0 || ry >= C) continue;
					if(visited[rx][ry] || board[rx][ry] == 0) continue;
					int rxryPipeNum = board[rx][ry];
					boolean check = checking(dnum,rxryPipeNum);
					
					if(check) {
						visited[rx][ry] = true;
						q.offer(new int[] {rx,ry});
					}
				}
			}
		}
	}

	private static boolean checking(int dnum, int rxryNum) {
		List<Integer> arr = new ArrayList<>();
		
		for(int m = 0; m < pipe[rxryNum].length; m++) {
			arr.add(pipe[rxryNum][m]);
		}
		
		switch (dnum) {
		case 1: 
			if(arr.contains(2)) return true;
			break;
		case 2 :
			if(arr.contains(1)) return true;
			break;
		case 3:
			if(arr.contains(4)) return true;
			break;
		case 4 :
			if(arr.contains(3)) return true;
			break;
		}

		return false;
	}

}
