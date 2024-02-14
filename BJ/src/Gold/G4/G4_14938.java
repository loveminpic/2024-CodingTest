package src.Gold.G4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class G4_14938 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int max_value = 0;
	static int N, M, R;
	static int[] items;
	static ArrayList<Node> list[];
	
	public static void main(String[] args) throws IOException {
		
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken()); // 지역의 갯수
		M = Integer.parseInt(tokens.nextToken()); // 수색 범위
		R = Integer.parseInt(tokens.nextToken()); // 길의 갯수
		
		items = new int[N+1];
		tokens = new StringTokenizer(br.readLine());
		
		for(int i = 1; i <=N; i++) {
			items[i] = Integer.parseInt(tokens.nextToken());
		}
		
		list = new ArrayList[N+1];
		
		for(int i = 0; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i = 1; i <= R; i++) {
			tokens = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(tokens.nextToken());
			int t = Integer.parseInt(tokens.nextToken());
			int w = Integer.parseInt(tokens.nextToken());
			
			list[s].add(new Node(t, w));
		}
		
		dijkstra();
		
	}

	private static void dijkstra() {
		// 모든 도시들 돌면서 M을 넘지 않는 거리 만큼의 도시를 방문하면서, 도시에 있는 아이템을 합산
		// 그리고 합산한 값이 이미 합산된 max 값이랑 비교해서 체인지.. 
		for(int i = 1; i <= N; i++) {
			int sum_item = 0;
			int visited[] = new int[N+1];
			visited[i] = 0;
			PriorityQueue<Node> q = new PriorityQueue<>();
			q.add(new Node(i,0));
			
			while(!q.isEmpty()) {
				Node curr = q.poll();
				int node = curr.to; // 2
				
				for(Node tmp : list[node]) {
					
				}
			}
				
			
		}
		
	}
	
}

class Node implements Comparable<Node>{
	int to;
	int edge;
	
	public Node(int to, int edge) {
		this.to = to;
		this.edge = edge;
	}

	@Override
	public int compareTo(Node o) {
		return Integer.compare(this.edge, o.edge);
	}
	
}
