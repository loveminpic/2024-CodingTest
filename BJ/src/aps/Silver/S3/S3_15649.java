package aps.Silver.S3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S3_15649 {

	static int N;
	static int M;
	static int[] numbers;
	static StringBuilder sb = new StringBuilder();

	static void permutation(final int n, int[] output, boolean[] flag) {
		if(n == M) {
			for(int temp : output) {
				sb.append(temp).append(" ");
			}
			sb.append("\n");
			return;
		}
		for(int i = 0 ; i < N; i++) {
			if(!flag[i] == true) {
				flag[i] = true;
				output[n] = numbers[i];
				permutation(n+1, output, flag);
				flag[i] = false;
			}
		}		
		return;
	}
	
	public static void main(String[] args) throws IOException {

		int[] output;
		boolean[] flag;
				
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		numbers = new int[N];
		for(int i = 0; i < N; i++) {
			numbers[i] = i+1;
		}
		
		flag = new boolean[N];
		output = new int[M];
		
		permutation(0, output, flag);
		System.out.println(sb);
	}
	
}
