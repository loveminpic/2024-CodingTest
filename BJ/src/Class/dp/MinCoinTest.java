package src.Class.dp;

import java.util.Scanner;

public class MinCoinTest {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); // 목표금액
		int[] D = new int[N+1]; // 각 금액을 만드는 최소동전수(최적해)
		
		D[0] = 0; // 점화식으로 값을 구할 수 없는 대상 초기화!
		for(int i = 1; i <= N ; i++) {
			int min = D[i-1] + 1; // 1월을 사용했을 경우로 임시 최적해
			if(i >= 4) {
				min = Math.min(min, D[i-4] +1); 
			}
			if(i >= 6) {
				min = Math.min(min, D[i-6] +1);
			}
			D[i] = min;
		}
		
		System.out.println(D[N]);
	}

}
