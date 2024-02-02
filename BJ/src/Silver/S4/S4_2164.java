package src.Silver.S4;
/**
 * @author Minji Lee
 * @date 20240202
 * @link https://www.acmicpc.net/problem/2164
 * @keyword_solution deque 활용 
 * @input 
 * 2초, 1 <= N <= 500,000 
 * @output 
 * 마지막 카드 출력
 * @time_complex O(N)
 * @perf  36508kb 220ms
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class S4_2164 {
	static BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(sc.readLine());
		int N = Integer.parseInt(tokens.nextToken());
		Queue<Integer> dequeue = new ArrayDeque<Integer>();
		
		for(int i = 1; i <= N; i++) {
			dequeue.offer(i);
		}
		
		findingLastCard(dequeue);

	}
	
	public static void findingLastCard(Queue<Integer> dequeue) {
		int tmp;
		int len = dequeue.size();
		while(len != 1) {
			dequeue.poll();
			if(dequeue.isEmpty()) {
				break;
			}
			tmp = dequeue.poll();
			dequeue.offer(tmp);
			len--;
		}
		System.out.println(dequeue.peek());
	}

}
