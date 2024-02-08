package src.SwExpertAcademy.d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class D3_2930_heap {
	static int T ;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens ;
	static StringBuilder sb = new StringBuilder();
	static PriorityQueue<Integer> heap ;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		T = Integer.parseInt(br.readLine());
		for( int i = 1; i <= T; i++ ) {
			heap = new PriorityQueue<>(Collections.reverseOrder());
			sb.append("#").append(i).append(" ");
			int N = Integer.parseInt(br.readLine());
			for(int j = 1; j <= N; j++) {
				tokens = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(tokens.nextToken());
				
				switch(a) {
				case 1:
					int b = Integer.parseInt(tokens.nextToken());
					heap.add(b);
					break;
				case 2:
					int tmp = -1;
					if(!heap.isEmpty()) {
						tmp = heap.poll();
					}
					sb.append(tmp).append(" ");
					break;
				}
				
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
