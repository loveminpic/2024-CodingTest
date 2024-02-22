package src.SwExpertAcademy.d4;
/**
 * @author Minji Lee
 * @date 20240222
 * @link https://swexpertacademy.com/main/talk/solvingClub/problemView.do?contestProbId=AV15B1cKAKwCFAYD&solveclubId=AY0LFFoqrIIDFAXz&problemBoxTitle=0219%EC%A3%BC&problemBoxCnt=3&probBoxId=AY3JcG16dgMDFAXh
 * @keyword_solution bfs, 인접리스트
 * @input 
 * @output 가장 깊은 레벨의 가장 큰 값 
 * @time_complex 
 * @perf  18,352 kb 메모리 110 ms
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class D4_1238Contact {
	
	static class Node {
		int value;
		Node next;
		
		public Node(int to) {
			this.value = to;
		}
		public Node(int to,Node n) {
			this.value = to;
			this.next = n;
		}
	}

	static int N;
	static int startN;
	static Node[] list;
	static int result = 0;
	static int result_depth = 0;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens;
		
		for(int t = 1; t <= 10; t++) {
			list = new Node[101];
			
			result = 0;
			result_depth =0;
			tokens = new StringTokenizer(br.readLine());
			N = Integer.parseInt(tokens.nextToken());
			startN = Integer.parseInt(tokens.nextToken());
			tokens = new StringTokenizer(br.readLine());
			
			int from, to;
			for(int n = 1; n <= N/2; n++) {
				from = Integer.parseInt(tokens.nextToken());
				to = Integer.parseInt(tokens.nextToken());
				
				if(list[from] == null) {
					list[from] = new Node(to);
				}else {
					Node tmp = list[from];
					list[from] = new Node(to,tmp);
				}
				
			}
			
			bfs(startN);
			
			sb.append("#").append(t + " ").append(result).append("\n");
		}
		System.out.println(sb);
	}

	private static void bfs(int start) {
		
		boolean[] visited = new boolean[101];
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(start);
		visited[start] = true;
		
		int cnt = 0;
		int check = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			check = 0;
			while(size -- > 0) {
				// 대장 데려온다
				int current = queue.poll();
							
				// 할일한다. 
				check = Math.max(current, check);
				
				// 자식노드 탐색
				for(Node tmp = list[current]; tmp != null ; tmp = tmp.next) {
					if(!visited[tmp.value]) {
						queue.offer(tmp.value);
						visited[tmp.value] = true;
					}
				}
			}
			result = check;
		}
		
	}
}


