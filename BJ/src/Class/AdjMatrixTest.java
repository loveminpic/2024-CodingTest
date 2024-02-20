package src.Class;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

public class AdjMatrixTest {
static int V;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		V = sc.nextInt();
		int E = sc.nextInt();
		
		int[][] adjMatrix = new int[V][V];
		for(int i  =0 ; i < E; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			adjMatrix[from][to] = adjMatrix[to][from] = 1; // 무향이므로 간선 양방향 처리!
			
		}
		
//		bfs(adjMatrix,0);
		boolean[] visited = new boolean[V];
		dfs(adjMatrix, visited, 0);
	}
	
	static void bfs(int[][] adjMatrix, int start) {
		// 1. 큐와 방문관리 배열 준비 
		int V = adjMatrix.length;
		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[V];
		
		//2. 시작 정점를 큐에 넣고 방문 체크
		queue.offer(start);
		visited[start] = true;
		
		//3. 큐로 방문체크
		while(!queue.isEmpty()) {
			int current = queue.poll(); // 4. 탐색해야하는 정점 꺼내기
			System.out.println((char) (current+65));
			
			// 5. 탐색정점 주변의 인접 정점들이 탐색될 수 있도록 처리하기
			for(int i = 0; i < V; ++i) {
				if(adjMatrix[current][i] != 0 && !visited[i]) {
					queue.offer(i);
					visited[i] = true;
					
				}
			}
		}
	}
	
	// 큐를 뺄때 방문처리하면 나오는 일. 

	static void bfs2(int[][] adjMatrix, int start) {
		// 1. 큐와 방문관리 배열 준비 
		int V = adjMatrix.length;
		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[V];
		
		//2. 시작 정점를 큐에 넣고 방문 체크
		queue.offer(start);
//		visited[start] = true;
		
		//3. 큐로 방문체크
		while(!queue.isEmpty()) {
			int current = queue.poll(); // 4. 탐색해야하는 정점 꺼내기
			visited[current] = true;
			System.out.println((char) (current+65));
			
			// 5. 탐색정점 주변의 인접 정점들이 탐색될 수 있도록 처리하기
			for(int i = 0; i < V; ++i) {
				if(adjMatrix[current][i] != 0 && !visited[i]) {
					queue.offer(i);
					
				}

			}
		}
	}
	
	static void dfs(int[][] adjMatrix, boolean[] visited, int current) {
		
		visited[current] = true;
		System.out.println((char) (current+65));
		
		for(int i = 0; i < V; ++i) {
			if(adjMatrix[current][i] != 0 && !visited[i]) {
				dfs(adjMatrix, visited, i);
			}

		}
	}
}
/*
7
8
0 1
0 2
0 5
0 6
4 3
5 3
5 4
6 4
*/
/*
7
8
0 1
0 2
1 3
1 4
2 4
3 5
4 5
5 6
*/