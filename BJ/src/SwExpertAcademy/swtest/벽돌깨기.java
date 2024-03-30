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



public class 벽돌깨기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	static int result = Integer.MAX_VALUE; // 남은 벽돌의 갯수
	static List<int[]> perList;
	static int T,N,C,R;
	static int[][] board;
	
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			tokens = new StringTokenizer(br.readLine());
			N = Integer.parseInt(tokens.nextToken());
			C = Integer.parseInt(tokens.nextToken());
			R = Integer.parseInt(tokens.nextToken());
			
			result = Integer.MAX_VALUE;
			int[][] realBoard = new int[R][C];
			perList = new ArrayList<>();
			
			
			for(int i = 0; i < R; i++) {
				tokens = new StringTokenizer(br.readLine());
				for(int j = 0; j < C; j++) {
					realBoard[i][j] = Integer.parseInt(tokens.nextToken());
				}
			}
			
			permutation(0, new int[N]);
			for(int i = 0; i < perList.size(); i++) {
				int[] list = perList.get(i);
				board = new int[R][C];
				// 보드 복사! 
				
				for(int r = 0; r < R; r++) {
					for(int c = 0; c < C; c++) {
						board[r][c] = realBoard[r][c];
					}
				}
				
				for(int j = 0; j < list.length; j++) {
					int col = list[j];
					int row = findTop(col); // 해당 컬럼의  top row!
					if(row == -1) continue; // 만약 비어있으면? 다음 열 체크
					removeCells(row, col);
					gravity();
					
				}
				checkResult();
			}
			sb.append("#").append(t + " ").append(result).append("\n");
		}
		System.out.println(sb);

	}

	private static void checkResult() {
		int count = 0;
		
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(board[i][j] > 0) {
					count++;
				}
			}
		}
		
		if(result > count) {
			result = count;
		}
	}

	private static void gravity() {
		List<Integer> tmp = new ArrayList<>();
		for(int i = 0; i < C; i++) {
			for(int j = 0; j < R; j++) {
				if (board[j][i] > 0) {
					tmp.add(board[j][i]);
					board[j][i] = 0;
				}
			}
			int cnt = R- tmp.size();
			for(int j = 0; j < tmp.size(); j++) {
				board[cnt][i] = tmp.get(j);
				cnt++;
			}
			tmp.clear();
		}
		
	}

	private static void removeCells(int row, int col) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {row, col, board[row][col]});
		board[row][col] = 0;
		
		while(!q.isEmpty()) {
			int[] current = q.poll();
			
			for(int i = 0; i < 4; i++) {
				int rx = current[0];
				int ry = current[1];
				for(int j = 0; j < current[2]-1; j++) {
					rx += dx[i];
					ry += dy[i];
					
					if(rx < 0 || ry < 0 || rx >= R || ry >= C) break;
					if(board[rx][ry] == 0) continue;
					q.offer(new int[] {rx,ry, board[rx][ry]});
					board[rx][ry] = 0;
				}
			}
		}
	}

	private static int findTop(int col) {
		int row = -1;
		for(int i = 0; i < R; i++) {
			if(board[i][col] > 0) {
				row = i;
				break;
			}
		}
		return row;
	}

	private static void permutation(int cnt, int[] choose) {
		if(N == cnt) {
			int[] newOne = Arrays.copyOf(choose, choose.length);
			perList.add(newOne);
			return;
		}
		
		for(int i = 0; i < C; i++) {
			choose[cnt] = i;
			permutation(cnt+1, choose);
		}

	}

}
