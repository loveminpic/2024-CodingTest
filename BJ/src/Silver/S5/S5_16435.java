package src.Silver.S5;
/**
 * @author Minji Lee
 * @date 2024.02.13
 * @link https://www.acmicpc.net/problem/16435
 * @keyword_solution  
 * @input 1 ≤ N ≤ 1,000 / 1 ≤ L ≤ 10,000
 * @output 스네이크가 길어질 수 있는 최대의 길이
 * @time_complex  NlogN
 * @perf 메모리 14408, 소요시간 136ms 
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class S5_16435 {
	
	static int N, L;
	static int[] fruits; 
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens; 
	static int len_snake; 
	
	public static void main(String[] args) throws Exception {
		
		// -----------input start---------------
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		L = Integer.parseInt(tokens.nextToken());
		len_snake = L;
		tokens = new StringTokenizer(br.readLine());
		fruits = new int[N];
		
		for(int i = 0; i < N; i++) {
			fruits[i] = Integer.parseInt(tokens.nextToken());
		}
		// -----------input end---------------
		Arrays.sort(fruits);
		
		
		for(int fruit : fruits) {
			if(fruit <= len_snake) {
				len_snake++;
			}
			else {
				break;
			}
		}
		
		System.out.println(len_snake);
	}

}
