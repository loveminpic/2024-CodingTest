package SwExpertAcademy.d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class D3_상호의배틀필드  {	
	static BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens ;
	static StringBuilder sb = new StringBuilder();
	static int TC;
	static char[][] board;
	static int r, c;
	static char[] commend;
	static int sr,sc;
	static int sd;
	static int[] dx = {0,-1,1,0,0}; 
	static int[] dy = {0,0,0,-1,1}; 

	public static void main(String[] args) throws NumberFormatException, IOException {
		TC = Integer.parseInt(br.readLine());
		for(int t = 1; t <= TC ; t++) {
			tokens = new StringTokenizer(br.readLine());
			r = Integer.parseInt(tokens.nextToken());
			c = Integer.parseInt(tokens.nextToken());
			
			board = new char[r][c];
			boolean check = false;
			for(int i = 0; i < r; i++) {
				String tmp = br.readLine();
				board[i] = tmp.toCharArray();
				if(!check) {
					for(int j = 0; j < c; j++) {
						if(board[i][j] == '^' || board[i][j] == 'v' || board[i][j] == '<' || board[i][j] == '>') {
							sr = i;
							sc = j;
							switch(board[i][j]) {
								case '^' :
									sd = 1;
									break;
								case 'v' :
									sd = 2;
									break;
								case '<' :
									sd = 3;
									break;
								case '>' :
									sd = 4;
									break;
								
							}
							check = true;
							break;
						}
					}
				}
			}
			
			int len = Integer.parseInt(br.readLine());
			String c_tmp = br.readLine();
			commend = c_tmp.toCharArray();
	
			// -----------INPUT---------------
			/*
			    U	Up : 전차가 바라보는 방향을 위쪽으로 바꾸고, 한 칸 위의 칸이 평지라면 위 그 칸으로 이동한다.
				D	Down : 전차가 바라보는 방향을 아래쪽으로 바꾸고, 한 칸 아래의 칸이 평지라면 그 칸으로 이동한다.
				L	Left : 전차가 바라보는 방향을 왼쪽으로 바꾸고, 한 칸 왼쪽의 칸이 평지라면 그 칸으로 이동한다.
				R	Right : 전차가 바라보는 방향을 오른쪽으로 바꾸고, 한 칸 오른쪽의 칸이 평지라면 그 칸으로 이동한다.
				S	Shoot : 전차가 현재 바라보고 있는 방향으로 포탄을 발사한다.
			 */
			for(int j = 0; j < commend.length; j++) {
				switch(commend[j]) {
				
					case 'U' :
						board[sr][sc] = '^';
						sd  =1;
						if(0 <= sr-1 && sr-1 < r && board[sr-1][sc] == '.') {
							board[sr][sc] = '.';
							board[sr-1][sc] = '^';
							sr = sr-1;
						
						}
						break;
					case 'D' :
						board[sr][sc] = 'v';
						sd = 2;
						if(0 <= sr+1 && sr+1 < r && board[sr+1][sc] == '.') {
							board[sr][sc] = '.';
							board[sr+1][sc] = 'v';
							sr = sr+1;
						}
						break;
					case 'L' :
						board[sr][sc] = '<';
						sd  = 3;
						if(0 <= sc-1 && sc-1 < c && board[sr][sc-1] == '.') {
							board[sr][sc] = '.';
							board[sr][sc-1] = '<';
							sc = sc-1;
						}
						break;
					case 'R' :
					
						board[sr][sc] = '>';
						sd = 4;
						if(0 <= sc+1 && sc+1 < c && board[sr][sc+1] == '.') {
							board[sr][sc] = '.';
							board[sr][sc+1] = '>';
							sc = sc+1;
						}
						break;
					case 'S' :
						shootPower(sd, sr, sc);
					
			}
					
		}
			
		sb.append("#").append(t).append(" ");
		for(int f = 0; f < r; f++) {
			for(int g = 0; g <c; g++) {
				sb.append(board[f][g]);
			}
			sb.append("\n");
		}
	}
	System.out.println(sb);
}
	private static void shootPower(int d, int r, int c) {
		int rx = r + dx[d];
		int ry = c + dy[d];
		if(d == 1) {
			while(rx >= 0) {
				if(board[rx][ry] == '*') {
					board[rx][ry] = '.';
					break;
				}else if (board[rx][ry] == '#') {
					break;
				}
				rx += dx[d];
			}
		}else if (d == 2) {
			while(rx < board.length) {
				if(board[rx][ry] == '*') {
					board[rx][ry] = '.';
					break;
				}else if (board[rx][ry] == '#') {
					break;
				}
				rx += dx[d];
			}
		}else if (d == 3) {
			while(ry >= 0) {
				if(board[rx][ry] == '*') {
					board[rx][ry] = '.';
					break;
				}else if (board[rx][ry] == '#') {
					break;
				}
				ry += dy[d];
			}
		}else if (d == 4) {
			while(ry < board[r].length) {
				if(board[rx][ry] == '*') {
					board[rx][ry] = '.';
					break;
				}else if (board[rx][ry] == '#') {
					break;
				}
				ry += dy[d];
			}
		
		}
	}
}
	