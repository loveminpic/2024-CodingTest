package aps.Gold.G3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G3_16202 {
	
	static class Edge {
		
		int v;
		int to;
		int w;
		
		
		public Edge(int v, int to) {
			super();
			this.v = v;
			this.to = to;
			this.w = ++cnt;
		}

		@Override
		public String toString() {
			return "Edge [v=" + v + ", to=" + to + ", w=" + w + "]";
		}
		
	}
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int N,M,K;
	static Edge[] list;
	static int[] p;
	static int cnt = 0;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		list = new Edge[M];
		
		for(int i = 0 ; i < M; i++) {
			tokens = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(tokens.nextToken());
			int to = Integer.parseInt(tokens.nextToken());
			list[i] = new Edge(from, to);
		}
		
		
		int start = 0;
		while(K-- > 0) {
			int result = mstSolved(start);
			sb.append(result+" ");
			start++;
			if(result == 0) {
				break;
			}
		}
		
		while(K-->0) {
			sb.append(0+" ");
		}
		sb.trimToSize();
		System.out.println(sb);
	}
	private static int mstSolved(int start) {
		int weight = 0;
		p = new int[N+1];
		for(int j = 1; j <=N; j++) {
			p[j] = j;
		}
		for(int i = start; i < list.length; i++) {
			
			weight += union(list[i].v ,list[i].to, list[i].w);	
		}
		
		int root = findSet(1);
		for(int i = 2; i <= N; i++) {
			if(root != findSet(i)){
				return 0;
			}
		}
		return weight;
	}
	
	private static int union(int v, int to, int w) {
		int rootA = findSet(v);
		int rootB = findSet(to);
		
		if(rootA == rootB) {
			return 0;
		}
		
		p[rootB] = rootA; 
		
		return w;
	}
	private static int findSet(int v) {
		if(p[v] == v) return v;
		else {
			return p[v] = findSet(p[v]); 
		}
	}

}
