package ssafy_class;

import java.util.Scanner;

//N개의 원소를 입력받아 가능한 모든 부분집합 생성
// 1<= N <=10; -> 이유는 n^2 시간복잡도 때문에 많아지면 너무 느려짐

public class PowerSetTest {
	static int N, input[], target;
	static boolean isSelected[];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		target = sc.nextInt();
		input = new int[N];
		isSelected = new boolean[N];
		
		for(int i = 0;i < N; i++) {
			input[i] = sc.nextInt();
		}
		generateSubSet(0,0);

	}
	private static void generateSubSet(int cnt, int sum) {

		
		if(cnt == N) {
			if(sum == target) {
				for(int i = 0; i < N; i++) {
					if(isSelected[i]) System.out.print(input[i]+"\t");
				}
			}	
			return;
		}
		isSelected[cnt]= true; // 부분집합에 넣기
		generateSubSet(cnt+1, sum+input[cnt]);
		isSelected[cnt] = false;
		generateSubSet(cnt+1,sum);
	}

}
