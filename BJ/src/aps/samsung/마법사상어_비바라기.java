package aps.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 마법사상어_비바라기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int N, M;
	static int[][] board;
	static int[][] Q;
	static Queue<int[]> groomQ = new ArrayDeque<int[]>();
	static boolean[][] visited;
	static int[] dx = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };
	static int[] dy = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };

	public static void main(String[] args) throws IOException {

		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());

		board = new int[N][N];

		Q = new int[M][2];

		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}

		for (int i = 0; i < M; i++) {
			tokens = new StringTokenizer(br.readLine());
			Q[i][0] = Integer.parseInt(tokens.nextToken());
			Q[i][1] = Integer.parseInt(tokens.nextToken());
		}

		groomQ.add(new int[] { N - 1, 0 }); // 처음 비구
		groomQ.add(new int[] { N - 1, 1 });
		groomQ.add(new int[] { N - 2, 0 });
		groomQ.add(new int[] { N - 2, 1 });
		// --------- input end

		for (int i = 0; i < M; i++) {

			solved(Q[i][0], Q[i][1]);
		}

		int result = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				result += board[i][j];
			}
		}

		System.out.println(result);
	}

	private static void solved(int dir, int s) {
		visited = new boolean[N][N];

		// 1. 모든 그룸이 dir 방향으로 s 만큼 이동! 물의 양 1증가

		movGrooms(dir, s);

		magic();
		// 5. 구름 리스트에 2 이상 인 애들다 넣어줌. 그대신 물의 양이2가 줄어줘야 함. 마이너스 물은 다시 0으로 변경
		makeNewGroom();
		
	}

	private static void makeNewGroom() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(board[i][j] >= 2 && !visited[i][j]) {
					board[i][j] -= 2;
					groomQ.add(new int[] {i,j});
				}
			}
		}
	}

	private static void movGrooms(int dir, int s) {
		int size = groomQ.size();
		
		while(size --> 0) {
			int[] curr = groomQ.poll();
			int rx = (curr[0] + dx[dir] * s % N + N) % N;
			int ry = (curr[1] + dy[dir] * s % N + N) % N;

			board[rx][ry] += 1;
			groomQ.add(new int[] {rx,ry});
			visited[rx][ry] = true;

		}
		
		//움직인 구름 찍어
//		for(int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(visited[i]));
//		}
		// 2. 각 름에 비가 내려 칸의 바구니에 저장된 물의 양이 1 증가 .  
		// visited 배열에 구름 위치 찍어줌~ 
	}

	private static void magic() {
		// 4. 4번 실행
		while(!groomQ.isEmpty()) {
			int[] curr = groomQ.poll();
			int count = 0;
			
			for(int i = 2; i < 9; i = i+2) {
				int rx = curr[0] + dx[i];
				int ry = curr[1] + dy[i];
				
				if(rx < 0 || ry < 0 || rx >= N || ry >= N) continue;
				if(board[rx][ry] == 0 ) continue;
				count++;
			}
			board[curr[0]][curr[1]] += count;
		}

	}

}
