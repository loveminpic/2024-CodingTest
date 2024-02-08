package src.Class.nextPermutation;

import java.sql.Array;
import java.util.Arrays;
import java.util.Scanner;

public class NextPermutation {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] input = new int[N];
		
		for(int i = 0; i <N; i++) {
			input[i] = sc.nextInt();
					
		}
		//step0 : 오름차순 정렬
		Arrays.sort(input);
		
		do {
			System.out.println(Arrays.toString(input));
		}while(np(input));
		
	}
	
	// 순열의 뒷쪽부터 작은 변화를 준다.!!
	public static boolean np(int[] p) { //p : 현 순열 / 현순열의 사전순 다음순열생성 
		// step1 : 교환위치 찾기( 꼭대기를 찾으면 꼭대기 이전이 교환위치가 됨), 뒤쪽부터 찾음
		
		int N = p.length;
		int i = N -1;
		
		while(i > 0 && p[i-1] >= p[i]) --i;
		
		if(i == 0) return false; // 현 순열의 상태가 가장 큰 순열이므로 np가 없다. 
		
		//step2. 교환위치인 i-1에 넣을 값을 뒤쪽부터 찾기(큰 값중 최소값)
		int j = N-1;
		while(p[i-1] >= p[j]) --j;
		
		//step3, 교환위치(i-1) 값과 찾은 위치 j 값 교환
		swap(p, i-1, j);
		
		// step4 꼭대기(i) 위치부터 맨뒤까지 오름차순 정렬
		int k = N-1;
		while(i < k) {
			swap(p, i++, k--);
		}
		
		return true;
	}
	public static void swap (int[] arr, int i , int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
		
	}
}
