package aps.Silver.S3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class S3_20300 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static long[] power ;
	static StringTokenizer tokens ;
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		
		tokens = new StringTokenizer(br.readLine());
		power = new long[N];
		
		for(int i = 0; i < N; i++) {
			power[i] = Long.parseLong(tokens.nextToken());
		}
		
		Arrays.sort(power);
		long result = 0;
		
		int start = 0;
		int end = power.length-1;
		
		if(N % 2 == 1) {
			result = power[power.length -1];
			end--;
		}
		
		while(start < end) {
			result = Math.max(result, power[start]+power[end]);
			start++;
			end--;
		}
		
		System.out.println(result);
	}

}
