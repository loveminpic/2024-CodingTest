package aps.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

//2시간 소요 
public class G2_스타트택시 {
	static class Passenger implements Cloneable {
		int sr,sc,er,ec;
		int distance;
		
		public Passenger(int sr, int sc, int er, int ec) {
			this.sr = sr;
			this.sc = sc;
			this.er = er;
			this.ec = ec;
		}
		
		
		
		public Passenger(int sr, int sc, int er, int ec, int distance) {
			super();
			this.sr = sr;
			this.sc = sc;
			this.er = er;
			this.ec = ec;
			this.distance = distance;
		}
	}

	public static Comparator<Passenger> passengerComparator = new Comparator<Passenger>() {
		
		@Override
		public int compare(Passenger o1, Passenger o2) {
			int distance = Integer.compare(o1.distance, o2.distance);
			if(distance != 0) {
				return distance;
			}
			int srCompare = Integer.compare(o1.sr,o2.sr);
			if(srCompare != 0) {
				return srCompare;
			}
			int scCompare = Integer.compare(o1.sc, o2.sc);
			if(scCompare != 0) {
				return scCompare;
			}
			return Integer.compare(o1.sc, o2.sc);
		}
	};
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int[][] board;
	static int[][] visited;
	static int N,M,gas;
	static PriorityQueue<Passenger> pq = new PriorityQueue<Passenger>(passengerComparator);
	static PriorityQueue<Passenger> newPq = new PriorityQueue<Passenger>(passengerComparator);
	static int taxiR, taxiC;
	
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		gas = Integer.parseInt(tokens.nextToken());
		
		board = new int[N+1][N+1];
		visited = new int[N+1][N+1];
		
		for(int i = 1; i <= N; i++) {
			tokens = new StringTokenizer(br.readLine());
			for(int j = 1; j <=N ; j++) {
				board[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		
		tokens = new StringTokenizer(br.readLine());
		taxiR = Integer.parseInt(tokens.nextToken());
		taxiC = Integer.parseInt(tokens.nextToken());
		
		for(int i = 0; i < M ;i++) {
			tokens = new StringTokenizer(br.readLine());
			int sr = Integer.parseInt(tokens.nextToken());
			int sc = Integer.parseInt(tokens.nextToken());
			int er = Integer.parseInt(tokens.nextToken());
			int ec = Integer.parseInt(tokens.nextToken());
			pq.add(new Passenger(sr, sc, er, ec));
		}
		//-----------입력 끝------------
		
		// 택시와의 거리 먼저 구하기
		int size = pq.size();
		for(int i = 0; i < size; i++) {
			Passenger tmp = pq.poll();
			int distance = distaceChecktoTaxi(tmp.sr, tmp.sc);
			tmp.distance = distance;
			newPq.add(tmp);
			
			for(int v = 0 ; v <= N; v++) {
				Arrays.fill(visited[v], 0);
			}
		}
		
		pq = newPq;
		newPq = new PriorityQueue<Passenger>(passengerComparator);
		// 택시 움직이기
		while(!pq.isEmpty()) {
			Passenger tmp = pq.poll();
			boolean check = moveTaxi(tmp);
			if(!check) {
				System.out.println(-1);
				return ;
			}
			
			size = pq.size();
			for(int j = 0; j < size; j++) {
				Passenger tmp2 = pq.poll();
				int distance = distaceChecktoTaxi(tmp2.sr, tmp2.sc);
				tmp2.distance = distance;
				newPq.add(tmp2);
				
				for(int v = 0 ; v <= N; v++) {
					Arrays.fill(visited[v], 0);
				}
			}
			pq = newPq;
			newPq = new PriorityQueue<Passenger>(passengerComparator);
		}
		System.out.println(gas);
			
	}

	private static boolean moveTaxi(Passenger tmp) {
		gas -= tmp.distance;
		if(gas <= 0) return false;
		
		taxiR = tmp.er;
		taxiC = tmp.ec;
		
		int d = distaceChecktoTaxi(tmp.sr, tmp.sc);
		if(gas - d < 0) return false;
		
		gas += d;
		
		for(int v = 0 ; v <= N; v++) {
			Arrays.fill(visited[v], 0);
		}
		
		return true;
	}

	private static int distaceChecktoTaxi(int pR, int pC) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {pR,pC});
		visited[pR][pC] = 1;
	
		while(!q.isEmpty()) {
			int[] curr = q.poll();
		
			for(int i = 0; i < 4; i++) {
				int rx = curr[0] + dx[i];
				int ry = curr[1] + dy[i];
				
				if(rx <= 0 || ry <= 0 || rx > N || ry >N) continue;
				if(visited[rx][ry] > 0 || board[rx][ry] == 1 ) continue;
				visited[rx][ry] += visited[curr[0]][curr[1]] + 1;
				q.offer(new int[] {rx,ry});
				if(rx== taxiR && ry == taxiC) {
					return visited[taxiR][taxiC] -1; 
				}
			}

		}
		if(visited[taxiR][taxiC] == 0) {
			return Integer.MAX_VALUE;
		}
		return visited[taxiR][taxiC]-1;
	}

}



