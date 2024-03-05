package aps.Gold.G4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Minji Lee
 * @date 20240221
 * @link https://www.acmicpc.net/problem/15683
 * @keyword_solution bfs + 구현
 * @input  (1 ≤ N, M ≤ 8) 
 * @output  cctv 방향 변환에 따른 사각지대의 최소 값
 * @time_complex  
 * @perf 
 */
class Cctv {
	int r ;
	int c;
	int type; 
	
	public Cctv(int r, int c, int type) {
		this.r = r;
		this.c = c;
		this.type = type;
	}
}

public class G4_15683 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int R, C;
	static int result = 1000000;
	
	static int[][] board;
	static List<Cctv> cctvList = new ArrayList<>();
	
	// 방향에 대한 정규화를 진행한다. 
	// 상 :0, 하: 1, 좌: 2, 우 :3
	static int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	static int[][] one = {{0},{1},{2},{3}}; // 4
	static int[][] two = {{0,1},{2,3}}; // 2
	static int[][] three = {{0,3}, {1,2}, {0, 2}, {1, 3}}; // 4
	static int[][] four = {{0,2,3},{1,2,3},{2,0,1},{3,0,1}}; // 4

	
	
	public static void main(String[] args) throws IOException {
		
		tokens = new StringTokenizer(br.readLine());
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		board = new int[R][C];
		
		for(int i = 0; i < R; i++) {
			tokens = new StringTokenizer(br.readLine());
			for(int j = 0; j < C; j++) {
				board[i][j] = Integer.parseInt(tokens.nextToken());
				if(board[i][j] > 0 && board[i][j] != 6) {
					Cctv tmp = new Cctv(i,j,board[i][j]);
					if(tmp.type != 5) {
						cctvList.add(tmp);
					}
				}
			}
		}
		
		// 고정 cctv 설치 5
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				switch (board[i][j]) {	
				case 5:
					for(int d5 = 0; d5 < 4; d5++) {
						int dr = direction[d5][0];
						int dc = direction[d5][1];
						settingCCTV(i,j, dr,dc);
					}
					break;
				}
			}
		}
		
		int[][] newboard = new int[R][C];
		newboard = board;
		
		dfs(0, newboard);

		System.out.println(result);
	}
	
	private static void dfs(int numcctv, int[][] board) {
		if(cctvList.size() == numcctv) {
			checkingZero(board);
			return;
		}
		
		int[][] range = null;

		switch (cctvList.get(numcctv).type) {
		case 1:
			range = one;
			break;
		case 2:
			range = two;		
			break;
		case 3:
			range = three;
			break;
		case 4:
			range = four;
			break;

		}
		
		for (int i = 0; i < range.length; i++) {
			int[][] tmp_board = new int[R][C];
			
			for(int first = 0; first < R; first++) {
				tmp_board[first] = Arrays.copyOf(board[first],board[first].length );
			}


			for(int j = 0; j < range[i].length; j++) {
				int d = range[i][j];
				int dr = direction[d][0];
				int dc = direction[d][1];
				
				settingCCTV(cctvList.get(numcctv).r, cctvList.get(numcctv).c, dr,dc);
				
			}
			
			dfs(numcctv+1, board);
			
			for(int first = 0; first < R; first++) {
				board[first] = Arrays.copyOf(tmp_board[first],tmp_board[first].length );
			}


		}


	}

	private static void settingCCTV(int r, int c, int dr, int dc) {
		int rx = dr + r;
		int ry = dc + c;
		
		while(rx >= 0 && rx < R && ry >= 0 && ry < C) {
			if(board[rx][ry] == 6) {
				break;
			}
			if(board[rx][ry] == 0) {
				board[rx][ry] = -1;
			}
			
			rx += dr;
			ry += dc;
		}
	}
	
	private static void checkingZero(int[][] board) {
		int cnt = 0;
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(board[i][j] == 0) {
					cnt++;
					if(result < cnt) {
						return;
					}
				}
			}
		}
		
		result = Math.min(result, cnt);
		
	}

}