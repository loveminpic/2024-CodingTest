package aps.Silver.S4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


public class S4_1158 {
	static StringTokenizer tokens;
	static int N,K ;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		
		Queue<Integer> q = new ArrayDeque<>();
		for(int i = 1; i <= N; i++) {
			q.add(i);
		}
		
		int cnt = 0;
		
		sb.append("<");
		while(!q.isEmpty()) {
			int tmp = q.poll();
			cnt ++;
			if(cnt == K) {
				sb.append(tmp+", ");
				cnt = 0;
			}else {
				q.add(tmp);
			}
		}
		
		sb.delete(sb.length()-2, sb.length());
		sb.append(">");
		System.out.println(sb);
		
	}

}
