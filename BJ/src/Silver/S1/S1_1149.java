package src.Silver.S1;
/**
 * @author Minji Lee
 * @date 2024.02.27
 * @link https://www.acmicpc.net/problem/1149
 * @keyword_solution dp
 * @input N(2 ≤ N ≤ 1,000)
 * @output 첫째 줄에 모든 집을 칠하는 비용의 최솟값을 출력한다.
 * @time_complex  
 * @perf 12084	84
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1_1149 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int[][] cost;
	static int[][] dp;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		int N = Integer.parseInt(br.readLine());
		cost = new int[N][3];
		dp = new int[N][3];
		
		for(int i = 0; i < N; i++) {
			tokens = new StringTokenizer(br.readLine());
			cost[i][0] = Integer.parseInt(tokens.nextToken());
			cost[i][1] = Integer.parseInt(tokens.nextToken());
			cost[i][2] = Integer.parseInt(tokens.nextToken());
		}
		

		for (int i = 1; i < N; i++) {
			cost[i][0] += Math.min(cost[i - 1][1], cost[i - 1][2]);
			cost[i][1] += Math.min(cost[i - 1][0], cost[i - 1][2]);
			cost[i][2] += Math.min(cost[i - 1][0], cost[i - 1][1]);
		}
		int result = Integer.MAX_VALUE;
		if(result > cost[N-1][0]) {
			result = cost[N-1][0];
		}
		if(result > cost[N-1][1]) {
			result = cost[N-1][1];
		}
		if(result > cost[N-1][2]) {
			result = cost[N-1][2];
		}
		System.out.println(result);
	}

}
