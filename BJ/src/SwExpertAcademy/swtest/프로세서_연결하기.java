package src.SwExpertAcademy.swtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class 프로세서_연결하기 {
	static class Pos{
		int r;
		int c;
		
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}		
	}
	
	static List<Pos> list = new ArrayList<>();
	static int[][] board;
	static int[][] direction = {{0,1}, {0,-1}, {1, 0}, {-1,0}};
	
	static int N;
	static int result = 1000000;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens;
		int TC = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= TC; t++) {
			N = Integer.parseInt(br.readLine().trim());
			board = new int[N][N];
			result = 1000000;
			for(int i = 0; i< N; i++) {
				tokens = new StringTokenizer(br.readLine());
				for(int j= 0; j < N; j++) {
					int tmp  = Integer.parseInt(tokens.nextToken());
					board[i][j] = tmp;
					if(tmp == 1) {
						if( i > 0 && i < N-1 && j > 0 && j < N-1) {
							list.add(new Pos(i,j));
						}
					}
				}
			}
			// ---------------------------INPUT END-----------------------------
			if(list != null) {
				dfs(0, 0, new boolean[N][N]);
			}
			sb.append("#" + t +" " + result);
		}
		System.out.println(sb);
	}
	
	private static void dfs(int start, int cnt, boolean[][] visited) {
		if(start == N-1 ) {
			result = Math.min(result, cnt);
			return;
		}

		for(int i= start; i < list.size(); i++) {
			int r = list.get(i).r;
			int c = list.get(i).c;
			int tmp = 0; 
			for(int d = 0; d < 4; d++) {
			
				boolean[][] newVisited = new boolean[N][N];
				for(int h = 0; h < N; h++) {
					newVisited[h] = Arrays.copyOf(visited[h], visited[h].length);
				}
				
				tmp = 0;
				int rx = r + direction[d][0];
				int ry = c + direction[d][1];		
				while(true) {
					if(rx >= 0 && rx < N && ry >= 0 && ry<N && !visited[rx][ry]) {
						tmp++;
						newVisited[rx][ry] = true;
						rx += direction[d][0];
						ry += direction[d][1];
					}else {
						break;
					}
				}
				dfs(i+1, cnt+tmp, newVisited);
			}
			
		}
	}

}
