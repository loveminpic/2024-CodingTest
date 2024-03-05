package aps.Gold.G4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class G4_1197 {
	
	static class EdgeList{
		int from;
		int to;
		int weight;
		
		public EdgeList(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		
	
	}
	static List<EdgeList> list = new ArrayList<>(); 
	static boolean[] visited;
	static int[] parent;
	static int count = 0;
	static int result = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(br.readLine());
		int V,E;
		V = Integer.parseInt(tokens.nextToken());
		E = Integer.parseInt(tokens.nextToken());
		visited = new boolean[V];
		
		for(int i = 0; i<E; i++) {
			 tokens = new StringTokenizer(br.readLine());
			 int from = Integer.parseInt(tokens.nextToken());
			 int to = Integer.parseInt(tokens.nextToken());
			 int w = Integer.parseInt(tokens.nextToken());
			 list.add(new EdgeList(from, to, w));
		}
		
		Collections.sort(list, new Comparator<EdgeList>() {
			@Override
			public int compare(EdgeList o1, EdgeList o2) {
				// TODO Auto-generated method stub
				return o1.weight - o2.weight;
			}
			
		});
		
		parent = new int[V+1];
		for(int i = 1; i <= V; i++) {
			parent[i] = i;
		}
		
		for(int i = 0; i < list.size(); i++) {
			union(list.get(i).from, list.get(i).to, list.get(i).weight);
			if(count == V-1) {
				System.out.println(result);
				return;
			}
		}
		
	}

	private static void union(int from, int to, int w) {
		int rootFrom = findSet(from);
		int rootTo = findSet(to);
		
		if(rootFrom == rootTo) {
			return;
		}
		
		parent[rootFrom] = rootTo;
		
		count++;
		result += w;
		
	}

	private static int findSet(int from) {
		if(parent[from] == from) {
			return from;
		}
		
		return parent[from] = findSet(parent[from]);
	}

}
