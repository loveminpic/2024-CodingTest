package SwExpertAcademy.swtest;
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
	static int max = 0;
	static int result = Integer.MAX_VALUE;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens;
		int TC = Integer.parseInt(br.readLine().trim());
		
		for(int t = 1; t <= TC; t++) {
			N = Integer.parseInt(br.readLine().trim());
			board = new int[N][N];
			result = Integer.MAX_VALUE;
			max = 0;
			list = new ArrayList<>();
			
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
				dfs(0, 0, 0);
			}
			sb.append("#" + t +" " + result + "\n");
		}
		System.out.println(sb);
	}
	
	private static void dfs(int idx, int cnt , int coreCnt) {
		
		if(idx == list.size()) {
			if( max < coreCnt) {
				max = coreCnt;
				result = cnt; 
			}else if( max == coreCnt) {
				result = Math.min(result, cnt);
			}
			return;
		}
		
		
		int r = list.get(idx).r;
		int c = list.get(idx).c;
		
		
		for(int d = 0; d < 4; d++) {
			boolean check = true;
			int rx = r;
			int ry = c;
			while(true) {
				rx += direction[d][0];
				ry += direction[d][1];
				if(rx < 0 || rx >= N || ry < 0 || ry >= N) break;
				if(board[rx][ry] > 0) {
					check = false;
					break;
				}
			}
			
			if(check) {
				int tmp = settingboard(r, c ,d ,2);
				dfs(idx+1, cnt+tmp, coreCnt+1);
				settingboard(r, c, d, 0);
			}
			
		}
		dfs(idx+1, cnt, coreCnt);
	}
	
	private static int settingboard(int r, int c, int d, int setNum) {
		int tmp = 0;
		int rx = r;
		int ry = c;
		while(true) {
			rx += direction[d][0];
			ry += direction[d][1];
			if(rx < 0 || rx >= N || ry < 0 || ry >= N) break;
		
			tmp++;
			board[rx][ry] = setNum;
		
		}
		return tmp;
	}
}
