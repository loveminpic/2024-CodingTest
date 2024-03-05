package ssafy_class;

import java.util.Arrays;
import java.util.Scanner;

// 주사위 던지기 (던지는 횟수는 6번)
public class DiceTest {
	static int N, numbers[];
	static boolean[] isSelected;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		numbers = new int[N];
		int mode = sc.nextInt(); 
		
		switch (mode) {
		case 1: // 중복순열
			dice1(0);
			break;
		case 2 : // 순열
			isSelected = new boolean[7];
			dice2(0);
			break;
		case 3: // 중복조합
			dice3(0,1);
			break;	
		case 4: // 조합
			dice4(0,1);
			break;
		default:
			break;
		}
		
	}
	
	public static void dice1(int cnt) {
		if(cnt == N) {
			System.out.println(Arrays.toString(numbers));
			return;
		}
		for(int i = 1; i <= 6; i++) {
			numbers[cnt] = i;
			dice1(cnt +1);
		}
	}
		
	public static void dice2(int cnt) {
		if(cnt == N) {
			System.out.println(Arrays.toString(numbers));
			return;
		}
		for(int i = 1; i <= 6; i++) {
			if(!isSelected[i]) {
				numbers[cnt] = i;
				isSelected[i] = true;
				dice2(cnt +1);
				isSelected[i] = false;
			}
		}
	}
	
	// 중복조합! 
		public static void dice3(int cnt, int start) {
			if (cnt == N) {
				System.out.println(Arrays.toString(numbers));
				return;
			}
			
			for(int i = start; i <=6; i++ ) {
					numbers[cnt] = i;
					dice4(cnt+1, i);
				}
			
		}
		
	// 조합! 
	public static void dice4(int cnt, int start) {
		if (cnt == N) {
			System.out.println(Arrays.toString(numbers));
			return;
		}
		
		for(int i = start; i <=6; i++ ) {
				numbers[cnt] = i;
				dice4(cnt+1, i+1);
			}
		
	}
}
