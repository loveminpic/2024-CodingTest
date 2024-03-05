package aps.Silver.S2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;import java.io.StringReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class DfsAndBfs {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder(); 
	static int N,M,V;
	static List[] graph;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new StringReader(instr));
		tokens = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(tokens.nextToken()) + 1; // Vertex start 1 
		M = Integer.parseInt(tokens.nextToken());
		V = Integer.parseInt(tokens.nextToken());
		
		// making graph;
		graph = new List[N];
		for(int n = 1; n < N; n++) {
			graph[n] = new ArrayList();
		}
		
		for(int m = 0; m < M; m++) {
			tokens = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(tokens.nextToken());
			int b = Integer.parseInt(tokens.nextToken());
			
			graph[a].add(b);
			graph[b].add(a);
		}
		
		for(int n = 1; n < N; n++) {
			Collections.sort(graph[n]);
		}
		//boolean [] visited = new boolean[N];
		
		dfs(V, new boolean[N]);
		sb.append("\n");
		bfs();
//		// 입력확인
//		for(List l : graph) {
//			System.out.println(l);
//		}
		
		System.out.println(sb);
	}
	
	private static void dfs(int s, boolean visited[]) {
		visited[s] = true;
		sb.append(s).append(" ");
	
		List<Integer> childs = graph[s];
		for(int i = 0; i < childs.size(); i++) {
			Integer child = childs.get(i);
			if(!visited[child]) {
				dfs(child,visited);
			}
		}
	}

	private static void bfs() {
		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] visited  = new boolean[N];
		
		queue.offer(V);
		visited[V] = true;
		
		while(!queue.isEmpty()) {
			Integer current = queue.poll();
			sb.append(current).append(" ");
			
			List<Integer> childs = graph[current];
			for(int i = 0; i < childs.size(); i++) {
				Integer child = childs.get(i);
				if(!visited[child]) {
					queue.offer(child);
					visited[child] = true;
				}
			}
		}
	}
	
	 static void bfs2() {
	        // 1. 준비물
	        Queue<Integer> q = new ArrayDeque<>();
	        boolean[] visited = new boolean[N];

	        // 2. 초기화
	        q.offer(V);          // 방문할꺼야
	        visited[V] = true;

	        // 3. 방문
	        int depth = 0;
	        while (!q.isEmpty()) {
	            int size = q.size(); // 현재 depth의 size
	            System.out.printf("depth: %d, 내용: %s%n", depth, q);
	            while (size-- > 0) {
	                // 3-1. 대장 데려오기 - 이게 진짜 방문
	                Integer head = q.poll();
	                // 3-2. 대장 사용하기
	                sb.append(head).append(" ");
	                // 3-3. 미방문한 자식 탐색하기 - graph활용
	                List<Integer> childs = graph[head];
	                for (int i = 0; i < childs.size(); i++) {
	                    Integer child = childs.get(i);
	                    if (!visited[child]) {
	                        q.offer(child);            // 방문할꺼야..
	                        visited[child] = true;
	                    }
	                }
	            }
	            depth++;
	        }
	    }

	    // 5번 노드를 방문하게 되면 출발점에서 목적지까지의 경로를 출력하세요.
	    static void bfs3() {
	        // 1. 준비물
	        Queue<MyNode> q = new ArrayDeque<>();
	        boolean[] visited = new boolean[N];

	        // 2. 초기화
	        q.offer(new MyNode(V));          // 방문할꺼야
	        visited[V] = true;

	        // 3. 방문
	        int depth = 0;
	        while (!q.isEmpty()) {
	            int size = q.size(); // 현재 depth의 size
	            System.out.printf("depth: %d, 내용: %s%n", depth, q);
	            while (size-- > 0) {
	                // 3-1. 대장 데려오기 - 이게 진짜 방문
	                MyNode head = q.poll();
	                // 3-2. 대장 사용하기
	                sb.append(head.no).append(" ");
	                if(head.no==5) {
	                    StringBuilder path = new StringBuilder(" "+head.no);
	                    for(MyNode parent = head.parent; parent!=null; parent = parent.parent) {
	                        path.insert(0, " "+parent.no);
	                    }
	                    System.out.println("5에 도달하는 경로: "+ path);
	                }
	                // 3-3. 미방문한 자식 탐색하기 - graph활용
	                List<Integer> childs = graph[head.no];
	                for (int i = 0; i < childs.size(); i++) {
	                    Integer child = childs.get(i);
	                    if (!visited[child]) {
	                        q.offer(new MyNode(child, head));            // 방문할꺼야..
	                        visited[child] = true;
	                    }
	                }
	            }
	            depth++;
	        }
	    }

	    static class MyNode {
	        Integer no;
	        MyNode parent;

	        public MyNode(Integer no) {
	            this.no = no;
	        }

	        public MyNode(Integer no, MyNode parent) {
	            this(no);
	            this.parent = parent;
	        }

	        @Override
	        public String toString() {
	            return "[no=" + no + ", parent=" + parent + "]";
	        }
	    }

	 
	private static String instr = 
			"5 5 3\r\n" + 
			"5 4\r\n" + 
			"5 2\r\n" + 
			"1 2\r\n" + 
			"3 4\r\n" + 
			"3 1";
	
	
}
