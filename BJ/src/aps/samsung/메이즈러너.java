package aps.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 메이즈러너 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens ;
	static int N,M,K;
	static int[][] board;
	static int[][] newBoard;
	static int[] exit;
	static int cnt = 0;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		
		board = new int[N][N];
		newBoard = new int[N][N];
		exit = new int[2];
		for(int i = 0; i < N; i++) {
			tokens = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		
		for(int i = 0; i < M; i++) {
			tokens = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(tokens.nextToken());
			int c = Integer.parseInt(tokens.nextToken());
			board[r-1][c-1] += -1; 
		}
		
		tokens = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(tokens.nextToken());
		int c = Integer.parseInt(tokens.nextToken());
		board[r-1][c-1] = -11;
		exit[0] = r -1;
		exit[1] = c -1;
		
		while(K -- > 0) {
			
//			System.out.println("before ");
//			for(int i = 0; i < N; i++) {
//				System.out.println(Arrays.toString(board[i]));
//			}
			movePerson();	
//			System.out.println("after ");
//			for(int i = 0; i < N; i++) {
//				System.out.println(Arrays.toString(board[i]));
//			}
			
			if(M == 0) break;
			
			turnBox();
//			System.out.println("turn after ");
//			for(int i = 0; i < N; i++) {
//				System.out.println(Arrays.toString(board[i]));
//			}
//			
			
		}
		
		System.out.println(-cnt);
		System.out.print(exit[0]+1);
		System.out.print(" ");
		System.out.print(exit[1]+1);
		
	}

	private static void turnBox() {
		int r = 0;
		int c = 0;
		int len = 100;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(board[i][j] < 0 && board[i][j] != -11) {
					len = Math.min(len, Math.max(Math.abs(exit[0]-i), Math.abs(exit[1]-j)));
				}
			}
		}
		
//		System.out.println("len : " + len);
		
		out : for(int i = 0; i < N-len; i++) {
			for(int j = 0; j < N-len; j++) {
				if(i <= exit[0] && i+len >= exit[0] && j <= exit[1] && j+len >= exit[1]) {
					for(int ei = i; ei <= i+len; ei++) {
						for(int ej = j; ej <= j+len; ej++) {
							if(board[ei][ej] < 0 
									&& board[ei][ej] != -11) {
								r = i;
								c = j;
								break out;
							}
						}
					}
				}
			}
		}
//		System.out.println(r + " " + c );
		copyBoard();
		for(int i = 0; i <= len; i++) {
			for (int j = 0; j <= len; j++) {
				newBoard[i+r][j+c] = board[r+len-j][c+i];
				if(newBoard[i+r][j+c] > 0) {
					newBoard[i+r][j+c] -= 1;
				}else if(newBoard[i+r][j+c] == -11) {
					exit[0] = i+r;
					exit[1] = j+c;
				}
			}
		}
		
		copyNewBoard();
		
	}

	private static void movePerson() {
		newBoard = new int[N][N];
		copyBoard();
		
		for(int i = 0; i < N ; i++) {
			for(int j = 0; j < N; j++) {
				if(board[i][j] < 0 && board[i][j] != -11) {
					int currLen = Math.abs(i - exit[0]) + Math.abs(j - exit[1]);
					
					for(int d = 0; d < 4; d++) {
						int rx = i + dx[d];
						int ry = j + dy[d];
						
						if(rx < 0 || ry < 0 || rx >=N || ry >= N) continue;
						if(board[rx][ry] > 0) continue;
						if(currLen <=  Math.abs(rx - exit[0]) + Math.abs(ry - exit[1]))continue;
						newBoard[i][j] -= board[i][j];
						cnt += board[i][j];
						if(board[rx][ry] == -11) {
							M += board[i][j];
						}else {
							newBoard[rx][ry] += board[i][j];
						}
						break;
					}
					
				}
				
			}
		}
		
		copyNewBoard();
		
		
	}

	private static void copyBoard() {
		// 보드 뉴보드
		for(int i = 0; i < N; i++) {
			for(int j = 0; j< N; j++) {
				newBoard[i][j] = board[i][j];
			}
		}
	}
	
	private static void copyNewBoard() {
		// 보드 뉴보드
		for(int i = 0; i < N; i++) {
			for(int j = 0; j< N; j++) {
				board[i][j] = newBoard[i][j];
			}
		}
		
	}
}