package SwExpertAcademy.swtest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Test_5653_teacherversion {
	static int N, M, K;
	static int[][] map; // 해당 위치에 세포 생명주기(x)값 넣기
	static boolean[][] visited;
	static Queue<Cell> q;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	// 세포 정보 관리용 클래스
	static class Cell {
		int r, c; // 위치 정보
		int status; // 상태 정보 0: 비활성, 1:활성, 2:죽은 세포
		int life; // 생명력 수치
		int time;// 상태 변경 용 시간 흐름 확인

		Cell(int r, int c, int life) {
			this.r = r;
			this.c = c;
			this.life = life;
			this.time = life; // 생명력 수치로 셋팅
		}

		// 시간마다 상태 정보 변경하는 메서드
		void step() {
			switch (status) {
			case 0: // 비활성화 상태
				if (--time == 0) // 생명력이 0이 되면? 활성화 상태로 바꿔주기
					this.status = 1;
				break;
			case 1: // 활성화 상태
				if (++time == life) // 생명력과 같아지면? 죽은 세포로 바꿔주기
					this.status = 2;
				break;
			}

		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 세포배양 정보
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken()); // 세포를 배양하는 시간

			// 배양 용기 만들기!
			map = new int[N + K + 1][M + K + 1];
			visited = new boolean[N + K + 1][M + K + 1]; // 번식할때 방문 여부 확인할 배열

			// 관리할 세포들을 담을 큐 생성
			q = new LinkedList<>();

			// 세포 배양 정보 받기, 세포 배양 위치를 중앙으로 잡아주기
			for (int r = K / 2 + 1; r < N + K / 2 + 1; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = K / 2 + 1; c < M + K / 2 + 1; c++) {
					// 이 위치에 있는 세포의 생명력
					int tmp = Integer.parseInt(st.nextToken());
					if (tmp != 0) {
						map[r][c] = tmp;
						visited[r][c] = true;
						q.add(new Cell(r, c, tmp)); // 초기 세포 정보 큐에 넣어주기
					}
				}
			}

			// --------INPUT END------------------
			solve();
			System.out.println("#"+t+" "+q.size());
		}

	}

	// 매시간 세포 배양해주기 : BFS 베이스
	private static void solve() {

		while (K-- > 0) {
			//배양 전저리
			//현재 가진 정보로 번식을 했을 때, 각 map에 가장 큰 생명력 저장
			for(Cell test : q) {//현재 큐의 모든 세포를 뽑아서 번식
				if(test.status ==1) {
					for (int d = 0; d < 4; d++) {
						int nr = test.r + dr[d];
						int nc = test.c + dc[d];
						//방문 처리 => 이전 시간에 번식 된곳
						if(visited[nr][nc])
							continue;
						
						//배양 용기의 세포 생명력만 갱신 : 수치가 가장 높은 값으로
						if(map[nr][nc]<test.life)
							map[nr][nc] = test.life;
					}
				}
				
			}
			
			// 이번 시간에 확인할 세포 수
			int size = q.size();
			// 현재 시간에 확인할 세포 만큼 배양
			for (int s = 0; s < size; s++) {
				Cell c = q.poll();
				// 세포가 활성화 상태일때만 번식 시키기
				if (c.status == 1) {
					for (int d = 0; d < 4; d++) {
						int nr = c.r + dr[d];
						int nc = c.c + dc[d];
						if (visited[nr][nc]) //이미 배양 된 곳이면 다음으로
							continue;
						//배양이 안된 곳에 번식
						q.add(new Cell(nr, nc, map[nr][nc]));
						visited[nr][nc] = true;
					}
				}
				// 세포 상태 변화 시키기
				c.step();
				if (c.status == 2) // 죽은세포면?
					continue;// 큐에 넣지 않고 다음으로
				// 활성화, 비활성화 상태의 세포는 다음 시간에 배양할 수 있게
				// 큐에 넣어 주기
				q.add(c);
			}
		}
	}

}
