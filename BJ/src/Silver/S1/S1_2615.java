package src.Silver.S1;
/**
 * @author Minji Lee
 * @date 2024.01.29
 * @link https://www.acmicpc.net/submit/2615
 * @keyword_solution 그래프
 * @input 
 * @output   
 * @time_complex  
 * @perf 15984 152 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1_2615 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int N = 19;
	static int[][] map = new int[N][N];
	
	static int winner;
	static int a,b;
	
	public static boolean bfs(int x, int y, int win) {
		
		
		int[] dx = {1,1,1,0,-1,-1,-1,0};
		int[] dy = {1,0,-1,-1,-1,0,-1,1};

		for(int m = 0; m < 8; m++) {
			int cnt = 1;
			int rx = x + dx[m];
			int ry = y + dy[m];
			
			while (rx >= 0 && rx < N && ry >= 0 && ry < N && map[rx][ry] == win) {
		            cnt++;
		            rx += dx[m];
		            ry += dy[m];
		    }
			
			if(cnt == 5) {
			 if (rx >= 0 && rx < N && ry >= 0 && ry < N && map[rx][ry] == win) {
	                continue;
	            }
				int oppositeRx = x - dx[m];
		        int oppositeRy = y - dy[m];
		        if (oppositeRx >= 0 && oppositeRx < N && oppositeRy >= 0 && oppositeRy < N && map[oppositeRx][oppositeRy] == win) {
	                continue;
	            }
				winner = win;
				a = x + 1;
				
				if (dx[m] == -1 || dy[m] == -1) {
			        a = (x + 1) + (dx[m] * (cnt - 1));
			        b = (y + 1) + (dy[m] * (cnt - 1));
			    }
			    return true;
			   
			}
		}
		return false;
	}

	
	public static void main(String[] args) throws IOException {
		
		// 배열에 담아서 인풋값 준비하기
		for(int i = 0; i < N; i++ ) {
			tokens = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		
		start : for(int i = 0; i< N; i++ ) {
			for(int j = 0; j < N; j++) {
				if(map[i][j] != 0) {
					boolean temp = bfs(i,j, map[i][j]);
					if(temp == true) {
						break start;
					}	
				}
			}
		}
		if (winner == 0) {
	        System.out.println(0);
	    }else {
			System.out.println(winner);
			System.out.println(a + " " + b);
	    }

	}

	}


