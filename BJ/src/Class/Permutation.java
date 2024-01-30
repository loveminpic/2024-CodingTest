package src.Class;

import java.util.Arrays;

public class Permutation {
	static char[] src = {'a','b','c','d'};
	static int cnt= 0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("순열");
		makePermutaion(0, new char[3], new boolean[src.length]);
		System.out.println(cnt);
	}
	
	// 매개변수 
	// 기저조건, 유도조건
	// 플랫하게 바라보기! 
	// 유도조건을 먼저 작성하고 기저조건을 작성한다. 
	// 함수 안에서 매개변수 값이 변하면 안되니까 final 로 강제성을 준다. 그리고 재귀 호출시 ++ 로 증가하지 않도록 한다! 
	static void makePermutaion(final int nthChoice, char[] choosed, boolean [] visited) {
		// 기저조건 : base part
		if(nthChoice == choosed.length) {
			System.out.println(Arrays.toString(choosed));
			return;
		}
		
		// 유도조건 : inductive part
		for(int i = 0; i < src.length; i++) {
			if (!visited[i]) {
				visited[i] = true;
				choosed[nthChoice] = src[i];
				cnt++;
				makePermutaion(nthChoice+1, choosed, visited);
				visited[i] = false;
			}
		}
		
	}

}
