/**
 * @author Minji Lee
 * @date 2024.01.30
 * @link https://www.acmicpc.net/problem/1244
 * @keyword_solution  
 * 1. 출력과 입력을 잘보기
 * 2. 조건들 잘 확인하기
 * 3. 입출력 주어진대로 잘 출력하기
 * @input 
 * 1. 남성과 여성 확인
 * 2. 스위치 숫자 시작점 1
 * 3. 스위치 갯수는 100개 이하의 양수
 * 4. 학생수는 100 이하인 양의 정수
 * @output 
 * 1. 한줄에 출력되는 갯수
 * 2. 출력마다 빈칸 존재
 * @time_complex  
 *  O(NM)
 * @perf 14592kb 148ms
 */
package aps.Silver.S4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class S4_1244 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static boolean[] list;
	static int N;
	static int M;
	
	static void checkSwitch(int sex, int numberOfSwitch) {
		// 남성일 경우
		if(sex == 1) {
			int temp = (int) list.length / numberOfSwitch ;
			int original = numberOfSwitch;
			
			for(int i = 0; i < temp; i++) {
				list[numberOfSwitch-1] = !list[numberOfSwitch-1];
				numberOfSwitch += original;
				if(numberOfSwitch > list.length) {
					break;
				}
			}
		}else {
			int left = numberOfSwitch - 2;
			int right = numberOfSwitch;
			list[numberOfSwitch-1] = !list[numberOfSwitch-1];
			while(left >= 0 && right < list.length && list[left] == list[right] ) {
				list[left] = !list[left];
				list[right] = !list[right];
				
				left -=1;
				right +=1;		
			}
		}
	}
		
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		list = new boolean[N];
		
		tokens = new StringTokenizer(br.readLine());
		for(int i= 0; i < N; i++) {
			if(Integer.parseInt(tokens.nextToken()) == 1 ){
				list[i] = true;
			}else {
				list[i] = false;
			}
		}
		tokens = new StringTokenizer(br.readLine());
		M = Integer.parseInt(tokens.nextToken());
		
		for(int i = 0; i < M; i++) {
			tokens = new StringTokenizer(br.readLine());
			int sex = Integer.parseInt(tokens.nextToken());
			int sw = Integer.parseInt(tokens.nextToken());
			checkSwitch(sex, sw);
		}
		
		int cnt = 0;
		for(boolean i : list) {
			if(cnt >= 20) {
				cnt = 0;
				System.out.println();
			}
			System.out.printf("%d ", i ? 1 : 0 );
			cnt++;
		}
	}

}