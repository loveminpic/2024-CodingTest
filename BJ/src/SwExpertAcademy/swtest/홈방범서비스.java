package SwExpertAcademy.swtest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 홈방범서비스 { 
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	
	static int TC, N,M;
	static int houseCnt; // 최대 몇개의 집이었는지
	static int[][] board;
	static List<int[]> houseList = new ArrayList<>();
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		TC = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= TC; tc++) {
			tokens = new StringTokenizer(br.readLine());
			N = Integer.parseInt(tokens.nextToken());
			M = Integer.parseInt(tokens.nextToken());
			board = new int[N][N];
			houseCnt = 0;
			houseList = new ArrayList<>();
			
			for(int i = 0; i < N; i++) {
				tokens = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(tokens.nextToken());
					if(board[i][j] == 1) {
						houseList.add(new int[] {i,j});
					}
				}
			}
			// --- input ---
			int K = N+1;
			int k = 1;
			while(K-- > 0) {
				for(int i = 0; i < N; i++) {
					for(int j = 0; j < N; j++) {
						bfs(i,j,k);
					}
				}
				k++;
			}
			sb.append("#"+tc+ " "+ houseCnt + "\n");
		}
		System.out.println(sb);
	}



	private static void bfs(int r, int c, int k) {
		int realK = k;
		Queue<int[]> q = new ArrayDeque<int[]>();
		q.offer(new int[] {r,c});
		boolean[][] visited = new boolean[N][N];
		visited[r][c] = true;
		int house = 0;
		
		if(board[r][c] == 1) {
			house++;
		}

		k--; // 1
		
		while(!q.isEmpty()) {
			
			while(k-- > 0) {
				int size = q.size();
				while(size-- > 0) {
					int[] curr = q.poll();
					for(int i =0 ; i < 4; i++) {
						int rx = curr[0] + dx[i];
						int ry = curr[1] + dy[i];
						if(rx < 0 ||rx >=N || ry < 0 || ry>= N) continue;
						if(visited[rx][ry]) continue;
						q.add(new int[] {rx,ry});
						visited[rx][ry] = true;
						if(board[rx][ry] == 1) {
							house++;
						}
					}
				}
			}
			
			if(k == -1) {
				if(house <= houseCnt) {
					return;
				}
				boolean check = checking(realK, house);
				if(check) {
					houseCnt = Math.max(houseCnt, house);
					return ;
				}else {
					return;
				}
			}
		}

	}



	private static boolean checking(int k, int house) {
		int runningCost = k*k +(k-1)*(k-1);
		if((house * M) - runningCost >= 0) {
			return true;
		}
		return false;
	}
	
}