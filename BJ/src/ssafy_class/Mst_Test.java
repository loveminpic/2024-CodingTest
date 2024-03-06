package ssafy_class;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Mst_Test{
	
	static class Edge implements Comparable<Edge>{
		int from,to,weigth;

		public Edge(int from, int to, int weigth) {
			super();
			this.from = from;
			this.to = to;
			this.weigth = weigth;
		}
		@Override
		public int compareTo(Edge o) {
			
			return Integer.compare(this.weigth, o.weigth);
		}
		
	}
	static int V;
	static Edge[] edgeList;
	static int[] parents;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		edgeList = new Edge[E];
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to =  Integer.parseInt(st.nextToken());
			int weight =  Integer.parseInt(st.nextToken());
			edgeList[i] = new Edge(from,to,weight);
		}
		
		Arrays.sort(edgeList);
		
		make();
		
		int weight = 0;
		int cnt = 0;
		
		// 2. 정렬된 간선을 하나씩 꺼내서 신장트리 생성 
		for(Edge edge : edgeList) {
			if(!union(edge.from, edge.to)) continue;
			weight += edge.weigth;
			if(++cnt == V-1) {
				break;
			}
		}
		
		System.out.println(weight);
	}
	
	public static void make() {
		// 1. make - set
		parents = new int[V]; // 자신의 부모나 루트를 저장 ( 경로 압축으로 인해 )
		
		for(int i = 0; i < V; i++) {
			parents[i] = i; // 모든 정점을 자신을 대표로
		}
	}

	public static int find(int a) {
 
		if(a == parents[a]) {
			return a; 		// 자신이 루트라는 이야기
		}
		return parents[a] = find(parents[a]);
	}
	
	public static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) {
			return false; // a와 b가 같은 트리에 속해있다. -> 유니온 불필요
		}
		
		parents[bRoot] = aRoot;
		return true;
	}
}
