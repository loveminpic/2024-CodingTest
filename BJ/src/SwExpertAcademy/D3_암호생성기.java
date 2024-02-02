package src.SwExpertAcademy;

/**
 * @author Minji Lee
 * @date 20240202
 * @link https://swexpertacademy.com/main/talk/solvingClub/problemView.do?contestProbId=AV14uWl6AF0CFAYD&solveclubId=AY0LFFoqrIIDFAXz&problemBoxTitle=0129%EC%A3%BC&problemBoxCnt=8&probBoxId=AY0LFFoqrIMDFAXz+
 * @keyword_solution queue 이용하기
 * @input 
 * TestCase 10
 * 각 주어지는 수는 integer 범위를 넘지 않는다.
 * 마지막 암호 배열은 모두 한자리 수로 구성되어있다. 
 * @output   
 * 감소되는 값이 0이 되는 순간, 0인 배열은 맨뒤로 가고 종료됨.
 * @time_complex O()
 * @perf  20916kb 127ms
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class D3_암호생성기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		
		for(int i = 1; i <= 10; i++) {
			tokens = new StringTokenizer(br.readLine());
			tokens = new StringTokenizer(br.readLine());
			Queue<Integer> queue = new ArrayDeque<Integer>();
			
			for(int j = 0; j < 8; j++) {
				queue.offer(Integer.parseInt(tokens.nextToken()));
			}
			
			sb.append("#").append(i).append(" ");
			passwordCreate(queue);
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	public static void passwordCreate(Queue<Integer> queue) {
		int cnt = 0;
		
		while(true) {
			cnt++;
			int temp = queue.poll();
			temp = temp - cnt;
			if(temp < 0) {
				temp = 0 ;
			}
			queue.offer(temp);
			
			if(temp == 0) {
				break;
			}
			if(cnt == 5) {
				cnt = 0;
			}
		}
		
		for(int tmp : queue) {
			sb.append(tmp).append(" ");
		}
		
		
	}

}