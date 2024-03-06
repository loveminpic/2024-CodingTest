package ssafy_class;

import java.util.Arrays;
import java.util.Scanner;

public class BitmaskingPermutation {
	 static int N, R, input[], numbers[];
	 
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		R = sc.nextInt();
		input = new int[N];
		numbers = new int[R];
		
		for(int i = 0; i < N; i ++) {
			input[i] = sc.nextInt();
		}
		permutaion(0, 0);
		
	}
	
	public static void permutaion(int cnt, int flag) {
		if(cnt == R) {
			System.out.println(Arrays.toString(numbers));
			return;
		}
		for(int i = 0; i < N; i++) {
			if((flag & 1 <<i) != 0) continue; // i 인덱스에 해당하는 수가 사용중이면 패스
			numbers[cnt] = input[i]; // 수 선택
			permutaion(cnt+1, flag | 1<<i);
		}
	}

}
