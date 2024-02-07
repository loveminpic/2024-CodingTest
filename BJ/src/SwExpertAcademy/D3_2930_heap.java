package src.SwExpertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D3_2930_heap {
	static int T ;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens ;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		T = Integer.parseInt(br.readLine());
		for( int i = 1; i <= T; i++ ) {
			sb.append("#").append(i).append(" ");
			int N = Integer.parseInt(br.readLine());
			for(int j = 1; j <= N; j++) {
				tokens = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(tokens.nextToken());
				
				if (a == 1) {
					int b = Integer.parseInt(tokens.nextToken());
				}
			}
		}
	}

}
