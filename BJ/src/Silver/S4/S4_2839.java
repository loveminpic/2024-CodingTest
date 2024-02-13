package src.Silver.S4;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * @author Minji Lee
 * @date 2024.02.13
 * @link https://www.acmicpc.net/problem/2839
 * @keyword_solution  
 * @input (3 ≤ N ≤ 5000)
 * @output 최소로 봉지를 사용하는 갯수
 * @time_complex  
 * @perf 메모리 14172kb, 소요시간 128ms 
 */

public class S4_2839 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		int N = Integer.parseInt(br.readLine());
	
		int result = Integer.MAX_VALUE;
		
		int three = N/3 + 1; // 0 - 6개
		int five = N/5 + 1;  // 0 - 3개
		
		for(int i = five; i >= 0; i--) {
			for(int j = 0; j < three; j++) {
				if(5*i + 3*j == N) {
					result = Math.min(result, i+j);
				}else if (5*i + 3*j > N){
					break;
				}
			}
		}
		
		if(result == Integer.MAX_VALUE) {
			result = -1;
		}
		System.out.println(result);
	}

}
