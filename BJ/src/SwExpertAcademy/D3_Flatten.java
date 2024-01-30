/**
 * @author Minji Lee
 * @date 2024.01.30
 * @link https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AY0LFFoqrIIDFAXz&contestProbId=AV139KOaABgCFAYh&probBoxId=AY0LFFoqrIMDFAXz+&type=PROBLEM&problemBoxTitle=0129%EC%A3%BC&problemBoxCnt=++4+
 * @keyword_solution 
 * 1. 어떤 방식을 사용해서 풀건지 고민
 * 2. 반복되는 부분을 찾게 되서 재귀로 선택
 * 3. 매개변수, 기저조건 고민
 * 4. 미리 로직 설계 후, 시간 복잡도를 계산하고, 주어진 시간 메모리 조건에 적합한지 고민하기 
 * @input 
 * 1. 테스트 케이스는 10개 / 배열의 크기는 100 / 덤프횟수는 1이상 1000이하  
 * 2. 토큰을 사용하려 배열을 만드는게 가장 심플한 것 같음! 
 * @output  
 * 1. 출력 형식을 잘 확인!
 * @time_complex M(덤프횟수) * N(배열의 크기) * 퀵소트(nlogn) 
 * @perf 21732메모리 123ms
 */

package src.SwExpertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class D3_Flatten {
	// 우선 테스크 케이스는 10개로 정해져있음
	static int T = 10;
	// 버퍼리더로 입력값 받구
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	// 전역으로 결과값 저장
	static int result = 0;
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		// 테케 10개 for문 
		for(int i = 0; i < T; i++) {
			int tc = Integer.parseInt(br.readLine());
			int[] list = new int[100];
			
			// StringTokenizer 로 입력값 받아서, list에 저장. 
			StringTokenizer tokens = new StringTokenizer(br.readLine());
			for(int j = 0; j < 100; j++) {
				list[j] = Integer.parseInt(tokens.nextToken());
			}
			
			// 재귀함수 호출 
			checking(0,tc,list);
			System.out.printf("#%d %d\n", i+1, result);
		}
	}
	
	/* int cnt 는 0 부터 시작하는 값
	 * int tc 는 입력에서 받은 카운트 리미트
	 * int[] list 는 입력에서 받은 리스트
	 */
	
	static void checking(final int cnt, int tc, int[] list) {
		// 정렬 항상 해주고, 젤 작은 값에 +1, 젤 큰값에  -1 
		// count++ 
		// 
		Arrays.sort(list);
		// 기저 조건
		if(cnt == tc) {
			result = list[list.length-1] - list[0];
			return ;
			
		}
		
		list[0] += 1;
		list[list.length-1] -= 1;
		checking(cnt+1, tc, list);
	}

}
