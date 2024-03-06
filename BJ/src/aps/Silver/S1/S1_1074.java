package aps.Silver.S1;
/**
 * @author Minji Lee
 * @date 20240214
 * @link https://www.acmicpc.net/problem/1074
 * @keyword_solution 분할정복 
 * @input 1 <= N <= 15
 * @output (r,c)를 몇번째 방문했는지? 
 * @time_complex O(logN)
 * @perf 14412kb	120ms
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class S1_1074 {
	 static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	 static StringTokenizer tokens;
	 static int r;
	 static int c;
	    
	public static void main(String[] args) throws IOException {
	   
		tokens = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(tokens.nextToken()); // N 입력 
		r = Integer.parseInt(tokens.nextToken());  // 행,열 입력 
		c = Integer.parseInt(tokens.nextToken()); 

		int pow_n = (int) Math.pow(2, N); // 2의 N승 

		recursive(0, 0, pow_n, 0);
	}
	
		/*
		si : 시작 i
	    sj : 시작 j
	    length : 시작점부터 해당 범위까지의 길이
	    cnt : 방문 순서 
	   */
	    public static void recursive(int si, int sj, int length, int cnt){ 
	    	// 기저조건
	    	if (length == 1) {
				if (si == r && sj == c) {
					System.out.println(cnt);
					return;
				}	
				return;
			}
	    	
	    	//1. 쪼개서 재귀를 타기 위해서 중간 값 잡아주기
	    	int half = length/2; 
	    	
	    	 	// 1번째 재귀 
	    	if (r < si + half && c < sj + half) {
				recursive(si, sj, half, cnt);
			} else if (r < si + half && c < sj + length) {
				// 2번째 재귀
				recursive(si, sj + half, half, cnt + half * half);
			} else if (r < si + length && c < sj + half) {
				// 3번째 재귀
				recursive(si + half, sj, half, cnt + half * half * 2);
			} else if (r < si + length && c < sj + length) {
				// 4번째 재귀
				recursive(si + half, sj + half, half, cnt + half * half * 3);
			}
	    }
}