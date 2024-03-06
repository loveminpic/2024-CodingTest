package ssafy_class;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Prim {
	static int V;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(br.readLine());
		V = Integer.parseInt(tokens.nextToken());
		int[][] adjMatrix = new int[V][V]; // 인접행렬 준비
		boolean[] visited = new boolean[V]; // 트리정점 여부
		int[] minEdge = new int[V]; // 비트리 정점 기준으로 트리 정점들과 연결했을때 최소 간선 비용
		
		for(int i = 0; i < V; i++) {
			tokens = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j <V; j++) {
				adjMatrix[i][j]= Integer.parseInt(tokens.nextToken());
			}
		}
		
		Arrays.fill(minEdge, Integer.MAX_VALUE); 
		minEdge[0] = 0; // 시작점 처리를 위해 
		int result = 0; // 최소신장 츠리 비용
		int c ;
		for(c = 0; c < V; c++) {
			// 1. 비트리정점중 최소 간선 비용의 정점을 찾기 
			int min = Integer.MAX_VALUE;
			int minVertex = -1;
			
			for(int i = 0; i < V; i++) {
				if(!visited[i] && minEdge[i] < min) {
					min = minEdge[i];
					minVertex = i;
				}
			}
			
			if(minVertex == -1) break;
			result += min; // 간선비용 누적
			visited[minVertex] = true;// 트리 정점에 포함
			
			// 2. 새롭게 트리 정점에 확장된 정점 기준으로 비트리 정점들과의 간선비용 고려해서 최적 업데이트
			for(int i = 0; i < V ; i++) {
				if(!visited[i] && adjMatrix[minVertex][i] != 0 && adjMatrix[minVertex][i] < minEdge[i]) {
					minEdge[i] = adjMatrix[minVertex][i];
					
				}
			}
			 
		}
		System.out.println(c==V? result : -1);
		
	}

}
