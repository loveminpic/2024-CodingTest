package aps.PemutationEveryDay;

import java.util.Arrays;

public class Per20240219 {
	//nPr
	// 순열 -> 순서가 중요하고, 중복되지 않고 n 중에 r만큼. 
	
	static int[] src = {1,2,3,4};
	static int N;
	public static void main(String[] args) {
		
		N = 3;
		permutation(0, new boolean[src.length], new int[N]);
		
	}

	private static void permutation(int cnt, boolean[] visited, int[] choosed) {
		if(cnt == N) {
			System.out.println(Arrays.toString(choosed));
			return;
		}
		
		for(int i =0; i < src.length; i++) {
			if(!visited[i]) {
				visited[i] = true;
				choosed[cnt] = src[i];
				permutation(cnt+1,visited,choosed);
				visited[i] = false;
				
			}
		}
	}

}
