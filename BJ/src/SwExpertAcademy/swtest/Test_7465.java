package src.SwExpertAcademy.swtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Test_7465 {
	static class LinkNode {
		int data ; 
		LinkNode next;
		LinkNode parents;
		
		
		public LinkNode(int data) {
			super();
			this.data = data;
			this.parents = this;
		}
	}
	
	static int TC;
	static int N, M;
	static LinkNode[] list ;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens;
		StringBuilder sb = new StringBuilder();
		TC = Integer.parseInt(br.readLine());
		for(int t = 1; t <= TC; t++) {
			tokens = new StringTokenizer(br.readLine());
			N = Integer.parseInt(tokens.nextToken());
			M = Integer.parseInt(tokens.nextToken());
			
			list = new LinkNode[N+1];
			for(int i = 1; i <= N; i++) {
				list[i] = new LinkNode(i);
			}
			
			
			for(int m = 0; m < M; m++) {
				tokens = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(tokens.nextToken());
				int y = Integer.parseInt(tokens.nextToken());
				union(list[x],list[y]);
			}	
			Set<Integer> result = new HashSet<>();
			
			for(LinkNode tmp : list) {
				if(tmp == null) continue;
				result.add(tmp.parents.data);
			}
			sb.append("#"+ t).append(" ").append(result.size()).append("\n");
		}
		System.out.println(sb);
	}

	private static void union(LinkNode x, LinkNode y) {
		LinkNode px = findSet(x);
		LinkNode py = findSet(y);
		
		if(px == py) return;
		
		LinkNode current = py;
		while(current != null) {
			current.parents = px;
			current = current.next;
		}
		
	}
	
	private static LinkNode findSet(LinkNode x) {
		
		if(x != x.parents) {
			x.parents = findSet(x.parents);
		}
		
		return x.parents;	
	}
	
	

}
