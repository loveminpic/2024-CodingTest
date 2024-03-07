package aps.Gold.G4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class G4_17250 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int N,M;
	static int[] p;
	static int[] values;
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		p = new int[N+1];
		values = new int[N+1];
		
		makeSet();
		for(int i = 1; i <= N; i++) {
			values[i] = Integer.parseInt(br.readLine());
		}
		
		for(int i =1;  i <=M; i++) {
			tokens = new StringTokenizer(br.readLine());
			int first = Integer.parseInt(tokens.nextToken());
			int second = Integer.parseInt(tokens.nextToken());
			
			union(first,second);
		}
		
	}
	
	private static void makeSet() {
		
		for(int i = 1; i <= N; i++) {
			p[i] = i;
		}
	}

	private static void union(int first, int second) {
		int rootA = findSet(first);
		int rootB = findSet(second);
		
		// 만약에 두개 연결 하려고 하면, 루트 값 변경해주고, first value root 값과 second 둘다 더해준당!  
		if(rootA == rootB) {
			System.out.println(values[rootA]);
			return;
		}
		
		p[rootB] = rootA;
		values[rootA] += values[rootB];
				
		System.out.println(values[rootA]);
	}

	private static int findSet(int first) {
		
		if(first == p[first]) {
			return first;
		}
		else {
			return p[first] = findSet(p[first]);
		}
	}
	
	
}
