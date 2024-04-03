package aps.Silver.S1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1_14888 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[] opList = new int[4];
	static int[] numList;
	static int MX, MIN;
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		numList = new int[N];
		MX = -1000000000;
		MIN = 1000000000;
		
		tokens = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++ ) {
			numList[i] = Integer.parseInt(tokens.nextToken());
		}
		tokens = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < 4; i++ ) {
			opList[i] = Integer.parseInt(tokens.nextToken());
		}
		permutation(0, new int[N-1]);
		
		System.out.println(MX);
		System.out.println(MIN);
	}


	private static void permutation(int cnt, int[] choose) {
		if(cnt == N-1) {
			check(choose);
			return;
		}
			
		for(int i = 0; i < 4; i++) {
			if(opList[i] > 0) {
				opList[i]--;
				choose[cnt] = i;
				permutation(cnt +1, choose);
				opList[i]++;
			}
		}
	}
	
	private static void check(int[] choose) {
		int num = numList[0];
		for(int i = 0; i < choose.length; i++) {
			switch(choose[i]){
				case 0 :
					num += numList[i+1];
					break;
				case 1 :
					num -= numList[i+1];
					break;
				case 2 :
					num *= numList[i+1];
					break;
				case 3 :
					num /= numList[i+1];
					break;
				}
		}
		MX = Math.max(MX, num);
		MIN = Math.min(MIN, num);
	}

}
