package src.SwExpertAcademy.swtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


public class Test_5653 {
	static class Cell{
		int r;
		int c;
		int x; // 생명력
		int time;
		int status; // 0. 비활성 / 1. 활성 / 2. 죽음
		
		public Cell(int r, int c, int x) {
			super();
			this.r = r;
			this.c = c;
			this.x = x;
			this.time = x;
		}
		void step() {
			switch (status) {
			case 0: // 비활성화 상태
				if (--time == 0) // 생명력이 0이 되면? 활성화 상태로 바꿔주기
					this.status = 1;
				break;
			case 1: // 활성화 상태
				if (++time == x) // 생명력과 같아지면? 죽은 세포로 바꿔주기
					this.status = 2;
				break;
			}

		}
	}

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	
	static int TC;
	static int R,C,K;
	static int[][] board;
	static boolean[][] visited;
	
	static int[] dr = {0,0,1,-1};
	static int[] dc = {1,-1,0,0};
	static Queue<Cell> q;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		TC = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= TC; t++) {
			tokens = new StringTokenizer(br.readLine());
			R = Integer.parseInt(tokens.nextToken());
			C = Integer.parseInt(tokens.nextToken());
			K = Integer.parseInt(tokens.nextToken());
			
			board = new int[R+K+1][C+K+1];
			visited = new boolean[R+K+1][C+K+1];
			q = new ArrayDeque();
			
			for(int i = K/2+1 ; i < R+K/2+1; i++) {
				tokens = new StringTokenizer(br.readLine());
				for(int j = K/2+1; j < C+K/2+1; j++) {
					int tmp  = Integer.parseInt(tokens.nextToken());
					if(tmp != 0) {
						board[i][j] = tmp;
						visited[i][j] = true;
						q.offer(new Cell(i,j, tmp));
					}
				}
			}
			solve();
			sb.append("#").append(t + " ").append(q.size()).append("\n");
		}
		
		System.out.println(sb);
	}

	private static void solve() {
		
		while(K-- > 0) {
			
			for(Cell test : q) {//현재 큐의 모든 세포를 뽑아서 번식
				if(test.status ==1) {
					for (int d = 0; d < 4; d++) {
						int nr = test.r + dr[d];
						int nc = test.c + dc[d];
						//방문 처리 => 이전 시간에 번식 된곳
						if(visited[nr][nc])
							continue;
						
						//배양 용기의 세포 생명력만 갱신 : 수치가 가장 높은 값으로
						if(board[nr][nc]<test.x)
							board[nr][nc] = test.x;
					}
				}
				
			}
			
			
			int size = q.size();
			for(int s = 0; s < size; s++) {
				Cell current = q.poll();
				
				if(current.status == 1) {
					for(int i = 0; i < 4; i++) {
						int rx = current.r + dr[i];
						int ry = current.c + dc[i];
						
						if(visited[rx][ry]) continue;
						q.offer(new Cell(rx,ry, board[rx][ry]));

						visited[rx][ry] = true;
						
					}
					
				}
				current.step();
				if(current.status == 2) {
					continue;
				}
				
				
				q.offer(current);
			}
				
		}
			
			// 우선 비활성화 된 cell 들 중에, time 이 1인 셀들을 다 담아준다. 그리고 활성화를 시켜줌
			// 만약 1은 은 아닌데 값이 큰 애들은 time 을 하나 씩 줄여준다. 
			
			// while 
			// 큐에서 하나씩 꺼내면서 활성화된 셀 기준으로 번식을 시켜준다. 
			// 번식을 시켜줄 때, 이미 다른 번식된 곳이 있으면, 번식 하지 않고, 만약 동시에 번식 시키는 셀이 2개 이상이면 생명력이 큰애를 넣어준다. 
			// 번식된 셀을 board 에도 표시해주는 것  잊지 않귀 ㅎㅎ 
			
		
		
		// 결국 
	}

}
