package aps.samsung;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 코드트리빵  {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;

	static int N, M;
	static int[][] board;
	static List<Store> storeList = new ArrayList<Store>();
	static List<Person> personList = new ArrayList<Person>();
	static List<int[]> baseCampList = new ArrayList<int[]>();

	static boolean[] check;
	static int time = 0; // 결과 값
    
	// 상, 좌, 우, 하
	static int[] dx = { -1, 0, 0, 1 };
	static int[] dy = { 0, -1, 1, 0 };
    
	static int arrived; // while문 탈출 조건

	static public class Store { // 편의점 class
		int r, c;
		int num;
		boolean status;

		public Store(int r, int c, int num) {
			super();
			this.r = r;
			this.c = c;
			this.num = num;
		}

	}

	static public class Person { // 사람 class
		int r, c;
		int num;
		boolean status;

		public Person(int r, int c, int num) {
			super();
			this.r = r;
			this.c = c;
			this.num = num;
		}

	}

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken()); // 행 열
		M = Integer.parseInt(tokens.nextToken()); // 사람 수
		arrived = M; // while 탈출 조건 초기화
		check = new boolean[M + 1];

		board = new int[N][N];
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(tokens.nextToken());
				if (board[i][j] == 1) {
					baseCampList.add(new int[] { i, j });
				}
			}
		}

		storeList.add(new Store(0, 0, 0)); // 디폴트 값.

		for (int i = 0; i < M; i++) {
			tokens = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(tokens.nextToken());
			int c = Integer.parseInt(tokens.nextToken());

			storeList.add(new Store(r - 1, c - 1, i + 1)); // 편의점은 1번 부터! 배열은 0,0 부
		}
        
		// ---- input end----


		while (arrived > 0) { // 편의점에 도착한 사람들은 arrived을 하나씩 줄여주기
			time++;
			int pSize = personList.size(); // 중간에 추가되는 사람은 로직에 영향을 받지 않도록
            // 1.2번 처리
			if (!personList.isEmpty()) { // 만약 격자 안에 사람이 있다면
				for (int p = 0; p < pSize; p++) {
					if (personList.get(p).status) // 이미 도착한 사람은 건너뛰기
						continue;
					move2(personList.get(p).num, p); // 도착하지 못한 사람

				}
			}
            
			// 2번에서 처리된 편의점들 다 체크해주기
            // 격자에 있는 사람들이 모두 이동한 뒤에 해당 칸을 지나갈 수 없어짐에 유의합니다. 
            // 라는 문구로 인해 여기서 처리 
            for (int i = 1; i < storeList.size(); i++) {
				if (storeList.get(i).status) {
					int tmpR = storeList.get(i).r;
					int tmpC = storeList.get(i).c;
					board[tmpR][tmpC] = -1;
				}
			}
            
			// 3번 처리 
			if ((time <= M)) {
				if (check[time])
					continue;
				findStore(time);
				check[time] = true;
		
			}

		}

		System.out.println(time);
	}

	private static void findStore(int t) {
		int sr = storeList.get(t).r;
		int sc = storeList.get(t).c;

		int br = 0;
		int bc = 0;
		int len = 100;

		for (int i = 0; i < baseCampList.size(); i++) {
			// bfs 돌면서 가장 가까운 베이스 캠프 고르기, 고를때 해당 배열이 1인지확인 -1 이면 안됨
			int[] curr = baseCampList.get(i);
			if (board[curr[0]][curr[1]] == -1)
				continue;

			int[][] visited = new int[N][N]; // 최소 거리 찾는 배열
			Queue<int[]> q = new ArrayDeque<int[]>();
			q.add(curr);
			visited[curr[0]][curr[1]] = 1;

			out: while (!q.isEmpty()) {
				int[] inQcurr = q.poll();
				if (visited[curr[0]][curr[1]] >= len)
					break;

				for (int d = 0; d < 4; d++) {
					int rx = inQcurr[0] + dx[d];
					int ry = inQcurr[1] + dy[d];

					if (rx < 0 || ry < 0 || rx >= N || ry >= N)
						continue;
					if (visited[rx][ry] > 0 || board[rx][ry] == -1)
						continue;
					if (rx == sr && ry == sc) {
						int tmp = visited[inQcurr[0]][inQcurr[1]] + 1;
						if (tmp < len) {
							br = curr[0];
							bc = curr[1];
							len = tmp;
						} 
						break out;
					}
					q.add(new int[] { rx, ry });
					visited[rx][ry] = visited[inQcurr[0]][inQcurr[1]] + 1;
				}

			}

		}
		// 정해진 베이스 캠프는 -1 로 체크 해주기
		board[br][bc] = -1;
		personList.add(new Person(br, bc, t));
	}

	private static void move2(int num, int p) {
		// 사람의 좌표 기준으로 상왼오하를 돌지 않고, 편의점을 기준으로 가까운 상왼오하를 찾음

		Person np = personList.get(p);
		int sr = storeList.get(num).r; // 해당 사람의 편의점 위치 
		int sc = storeList.get(num).c;

		int[][] visited = new int[N][N]; // 최소 거리 찾는 배열
        
		Queue<int[]> q = new ArrayDeque<int[]>();
		q.add(new int[] { sr, sc }); // 편의점으로 시작
		visited[sr][sc] = 1;

		while (!q.isEmpty()) {
			int[] inQcurr = q.poll();

			for (int d = 0; d < 4; d++) {
				int rx = inQcurr[0] + dx[d];
				int ry = inQcurr[1] + dy[d];

				if (rx < 0 || ry < 0 || rx >= N || ry >= N)
					continue;
				if (visited[rx][ry] > 0 || board[rx][ry] == -1)
					continue;
				q.add(new int[] { rx, ry });
				visited[rx][ry] = visited[inQcurr[0]][inQcurr[1]] + 1;
			}

		}

		int len = 100;
		int ar = 0;
		int ac = 0;
	
    	// 어디로 이동하는게 편의점에 가까워지는지 찾는 로직 
		for (int i = 0; i < 4; i++) { 
			int rx = np.r + dx[i];
			int ry = np.c + dy[i];

			if (rx < 0 || ry < 0 || rx >= N || ry >= N)
				continue;
			if (visited[rx][ry] == 0 || board[rx][ry] == -1)
				continue;

			if (visited[rx][ry] < len) {
				ar = rx;
				ac = ry;
				len = visited[rx][ry];
			}
		}

		// 찾은 좌표는 업데이트 해줌, 만약 편의점에 도착했으면, 그것도 표시 
		personList.get(p).r = ar;
		personList.get(p).c = ac;
		if (ar == sr && ac == sc) {
			storeList.get(num).status = true;
			arrived--;
			personList.get(p).status = true;
		}

	}
}