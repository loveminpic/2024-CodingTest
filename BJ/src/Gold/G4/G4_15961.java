package src.Gold.G4;
/**
 * @author MinjiLee
 * @date 20240223
 * @link https://www.acmicpc.net/problem/15961
 * @keyword_solution 슬라이딩 윈도
 * @input  2 ≤ N ≤ 3,000,000, 2 ≤ d ≤ 3,000, 2 ≤ k ≤ 3,000 (k ≤ N), 1 ≤ c ≤ d
 * @output 먹을 수 있는 최대 스시의 종
 * @time_complex  
 * @perf 168528kb	492ms
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G4_15961 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int max_sushi = 0;
	static int plate_num, sushi_menu, k, coupon_num;
	static int[] belt;
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		plate_num = Integer.parseInt(tokens.nextToken());
		sushi_menu = Integer.parseInt(tokens.nextToken());
		k = Integer.parseInt(tokens.nextToken());
		coupon_num = Integer.parseInt(tokens.nextToken());
		belt  = new int[plate_num];
		
		for(int i = 0; i < plate_num; i++) {
			belt[i] = Integer.parseInt(br.readLine());
		}
		
		int[] countSushi = new int[sushi_menu+1];
		int count = 0;
		
		 for (int i = 0; i < k; i++) {
	            if (countSushi[belt[i]] == 0) {
	                count++;
	            }
	            countSushi[belt[i]]++;
	        }
		 max_sushi = count;
		 
		 if (countSushi[coupon_num] == 0) {
	            max_sushi += 1; 
	     }
		
		for(int i = 1; i < plate_num; i++) {
			
			int end = (i + k - 1) % plate_num;
            int start = i - 1;
            
            if (countSushi[belt[start]] == 1) {
                count--;
            }
            countSushi[belt[start]]--;
			
            if (countSushi[belt[end]] == 0) {
                count++;
            }
            countSushi[belt[end]]++;
            
            int currentMax = count;
            if (countSushi[coupon_num] == 0) { // 쿠폰 초밥을 먹지 않았다면 추가
                currentMax += 1;
            }			
			max_sushi = Math.max(max_sushi, currentMax);
		}
		System.out.println(max_sushi);
	}

}
