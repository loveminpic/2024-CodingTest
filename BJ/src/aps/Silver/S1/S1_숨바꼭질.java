package aps.Silver.S1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1_숨바꼭질 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int result = Integer.MAX_VALUE;
	static int start, end;
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		start = Integer.parseInt(tokens.nextToken());
		end = Integer.parseInt(tokens.nextToken());
		
		// 현재위치, count
		bfs(start, 0, new boolean[end*3]);
		
		System.out.println(result);
	}

	private static void bfs(int s, int cnt, boolean[] visited) {

	}
}


