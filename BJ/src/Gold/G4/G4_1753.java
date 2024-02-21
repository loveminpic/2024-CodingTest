package src.Gold.G4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class G4_1753 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens ;
	static int V, E, S ;
	static StringBuilder sb = new StringBuilder();
	static PriorityQueue<Integer> pq = new PriorityQueue<>();
	static int[] visited ;
	static ArrayList<Node> list[];
	

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		tokens = new StringTokenizer(br.readLine());
		V = Integer.parseInt(tokens.nextToken());
		E = Integer.parseInt(tokens.nextToken());
		S = Integer.parseInt(br.readLine());
		
		list = new ArrayList[V+1];
		
		for(int i = 0; i <= V; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < E; i++) {
			tokens = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(tokens.nextToken());
			int v = Integer.parseInt(tokens.nextToken());
			int w = Integer.parseInt(tokens.nextToken());
			list[u].add(new Node(v, w));
		}
		
		visited = new int[V+1];
		Arrays.fill(visited, Integer.MAX_VALUE);
		
//		dijkstra();
		
		for(int i =1 ; i < visited.length; i++) {
			if(visited[i] == Integer.MAX_VALUE) {
				sb.append("INF").append("\n");
			}else {
				sb.append(visited[i]).append("\n");
			}
			
			
		}
        System.out.println(sb);

	}
}
//	private static void dijkstra() {
//		PriorityQueue<Node> q = new PriorityQueue<>();
//		q.add(new Node(S,0));
//		visited[S] = 0;
//		
//		while(!q.isEmpty()) {
//			Node current  = q.poll();
//			int node = current.to;
//			
//			if(visited[node] < current.weight) continue;
//
//            for(Node adj : list[node]) {
//                if(visited[adj.to] > visited[node] + adj.weight) {
//                    visited[adj.to] = visited[node] + adj.weight;
//                    q.add(new Node(adj.to, visited[adj.to]));
//                }
//				
//			}
//		}
//		
//	}
//	
//}
//
//class Node implements Comparable<Node>{
//	int to;
//	int weight;
//	
//	public Node(int to, int weight) {
//		this.to = to;
//		this.weight = weight;
//	}
//	 @Override
//	    public int compareTo(Node o) {
//	        return Integer.compare(this.weight, o.weight);
//	    }
//}
