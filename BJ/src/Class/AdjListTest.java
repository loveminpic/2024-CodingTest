package src.Class;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

// 무향 리스트 연결리스트 버전 
public class AdjListTest {

	static class Node{
		int to;
		Node next;
		
		public Node(int to, Node next) {
			this.to = to;
			this.next = next;
			
		}
		public Node(int to){
			this.to = to;
		}
		@Override
		public String toString() {
			return "Node [to=" + to + ", next=" + next + "]";
		}
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int V = sc.nextInt();
		int E = sc.nextInt();
		
		Node[] adjList = new Node[V];
		
		for(int i  =0 ; i < E; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			adjList[from] = new Node(to, adjList[from]);
			adjList[to] = new Node(from, adjList[to]);
			
		}
		
		bfs(adjList,0);

	}

	static void bfs(Node[] adjList, int start) {
		
		// 1. 큐와 방문관리 배열 준비 
		int V = adjList.length;
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
			for(Node tmp = adjList[current]; tmp != null ; tmp = tmp.next) {
				if(!visited[tmp.to]) {
					queue.offer(tmp.to);
					visited[tmp.to] = true;
				}
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