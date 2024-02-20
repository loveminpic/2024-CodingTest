import java.util.Arrays;

public class Permu20240220 {
	// 순열은 순서가 필요하고, 필요한 cnt 가 필요하고, visited도 필요함
	static int[] src = {1,2,3,4,5};

	static int N = 4;
	public static void main(String[] args) {
		
		permutation(0, new boolean[src.length],  new int[N]);
		
	}
	private static void permutation(int cnt, boolean[] visited, int[] choosed) {
		if(cnt == N) {
			System.out.println(Arrays.toString(choosed));
			return;
		}
		for(int i = 0; i < src.length; i++) {
			if(!visited[i]) {
				visited[i] = true;
				choosed[cnt] = src[i];
				permutation(cnt+1, visited, choosed);
				visited[i] = false;
			}
		}
		
	}

}
