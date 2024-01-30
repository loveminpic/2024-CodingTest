package src.SwExpertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @author Minji Lee
 * @date 2024.01.29
 * @link https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AY0LFFoqrIIDFAXz&contestProbId=AV19AcoKI9sCFAZN&probBoxId=AY0LFFoqrIMDFAXz+&type=PROBLEM&problemBoxTitle=0129%EC%A3%BC&problemBoxCnt=++1+
 * @keyword_solutio 구현 / 미리 설계 / Arrays 문법 / 문제 잘 이해하기 
 * @input 
 * @output   
 * @time_complex 
 * @perf 18300kb 107ms
 * bit 변수를 만들어서, 현재 변수를 기억해서 변수랑 오리지널 값이랑 비교하면 된다. 굳이 current  뒤쪽의 모든 비트를 변경할 필요가 없음!! 
 */

public class D3_원재의메모리복구하기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int T;
	static int[] original;
	static int[] current;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		T = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < T; i++) {
			int cnt = 0;
			char[] temp = br.readLine().toCharArray();
			original = new int[temp.length];
			
			for(int j = 0; j < temp.length; j++) {
				original[j] = (int) temp[j] - 48;
			}
			current = new int[original.length];
			Arrays.fill(current,0);
			
			
			while(!Arrays.equals(original,current)) {
				for(int j = 0; j < original.length; j++) {
					if(original[j] != current[j]) {
						Arrays.fill(current,j,original.length,original[j]);
						cnt++;
						break;
					}
				}
				
			}
			System.out.printf("#%d %d",i+1,cnt);
		}
	}

}
