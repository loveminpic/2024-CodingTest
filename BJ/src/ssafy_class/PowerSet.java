package ssafy_class;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PowerSet {
	static char[] src = {'a','b','c','d'};
	static boolean[] choosed;
	static int cnt = 0;
	public static void main(String[] args) {
		System.out.println("부분집합");
		choosed = new boolean[src.length];
		makePowerset(1,choosed);
//		 makePowerset();

	}
	
	public static void makePowerset(int tocheck, boolean[] choosed) {
		
		if(tocheck== src.length) {
//			System.out.println(++cnt + ": " +Arrays.toString(choosed));
			print(choosed);
			return;
		}
		choosed[tocheck] = true;
		makePowerset(tocheck+1, choosed);
		choosed[tocheck] = false;
		makePowerset(tocheck+1, choosed);
	}
	
	private static void print(boolean[] chooesd) {
		List<Character> list1 = new ArrayList<>();
		List<Character> list2 = new ArrayList<>();
		for(int i = 0; i < choosed.length; i++) {
			if(choosed[i]) {
				list1.add(src[i]);
			}else {
				list2.add(src[i]);
			}
		}
		System.out.println(++cnt+" :"+ list1 + " :" + list2);
	}
	
	// 비트마스킹~ 
	private static void makePowerset() {
		for(int i =0; i < (1<< src.length-1); i++ ) {
			List<Character> list1 = new ArrayList<>();
			List<Character> list2 = new ArrayList<>();
//			List<Character> list = new ArrayList<>();
			
			// 부분집합을 구성하는 각 비트를 반복 돔
			for(int j = 0; j <src.length; j++) {
				if((i & (1<<j)) > 0){
					list1.add(src[j]);
				}else {
					list2.add(src[j]);
				}
			}
			String binStr = String.format("%4s", Integer.toBinaryString(i)).replace(" ", "0");
			
			System.out.printf("%s %d %s %s%n",binStr, i, list1, list2);
		}
	}
}


/*
1: [true, true, true, true]
2: [true, true, true, false]
3: [true, true, false, true]
4: [true, true, false, false]
5: [true, false, true, true]
6: [true, false, true, false]
7: [true, false, false, true]
8: [true, false, false, false]
9: [false, true, true, true]
10: [false, true, true, false]
11: [false, true, false, true]
12: [false, true, false, false]
13: [false, false, true, true]
14: [false, false, true, false]
15: [false, false, false, true]
16: [false, false, false, false]


결과 값을 보면 0 번째 인덱스가 true or false 로 시작하는 값에 따라 다른 값들은 그저 반대가 될뿐이다. 
1과 16, 2와15 쭉쭉쭉 보면 다 반전된 값이다. 
즉 첫번째 인덱스를 고정하면, 반만 구하면 된다. 
 
 그래서 처음에 메인에서 호출할때, 시작 cnt 를 1로 준다. 


*/