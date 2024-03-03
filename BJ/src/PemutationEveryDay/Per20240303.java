package src.PemutationEveryDay;

import java.util.Arrays;

public class Per20240303 {
	static int[] src = {1,2,3};
	
	public static void main(String[] args) {
	
		
		//permutaion(0, new boolean[src.length], new int[3]);
		//combi(0, 0, new int[3]);
		powerSet(new boolean[src.length],0);
	}


	private static void powerSet(boolean[] visited, int cnt) {
		if(cnt == src.length) {
			System.out.println(Arrays.toString(visited));
			return;
		}
		visited[cnt] = true;
		powerSet(visited, cnt+1);
		visited[cnt] = false;
		powerSet(visited, cnt+1);
		
	}


	private static void combi(int start, int cnt, int[] choosed) {
		if(cnt == 3) {
			System.out.println(Arrays.toString(choosed));
			return;
		}
		
		for(int i = start ; i < src.length; i++) {
			choosed[cnt] = src[i];
			combi(i+1, cnt+1, choosed);
		}
	}


	static public void permutaion(int cnt, boolean[] visited,int[] choosed) {
		if(cnt == 3) {
			System.out.println(Arrays.toString(choosed));
			return;
		}
		
		for(int i = 0; i < src.length; i++) {
			if(!visited[i]) {
				visited[i] = true;
				choosed[cnt] = src[i];
				permutaion(cnt+1, visited, choosed);
				visited[i] = false;
			}
		}
	}
	
}

