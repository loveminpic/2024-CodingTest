package src.Silver.S2;
/**
 * @author Minji Lee
 * @date 20230207
 * @link
 * @keyword_solution bfs 레벨 이용해서 풀이
 * @input  
 * 도시의 개수 N, 도로의 개수 M, 거리 정보 K, 출발 도시의 번호 X
 * (2 ≤ N ≤ 300,000, 1 ≤ M ≤ 1,000,000, 1 ≤ K ≤ 300,000, 1 ≤ X ≤ N)
 * @output X도시부터 시작하여 K거리만큼 갈 수 있는 도시 구하
 * @time_complex  
 * @perf 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class S2_18352 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int cityNum, loadNum, K, startCity;
	static StringBuilder sb = new StringBuilder();
	
	static int[] distance;
	static ArrayList<Integer>[] list;
	static Queue<Integer> q = new LinkedList<>();
	static ArrayList<Integer> result = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		cityNum = Integer.parseInt(tokens.nextToken());
		loadNum = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		startCity = Integer.parseInt(tokens.nextToken());
		
		distance = new int[cityNum+1];
		Arrays.fill(distance, -1);
		
		list = new ArrayList[cityNum+1];
		
		
		for(int i = 0; i < loadNum; i++) {
			tokens = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(tokens.nextToken());
			int b = Integer.parseInt(tokens.nextToken());
			if(list[a] == null) {
				list[a] = new ArrayList<>();
			}
			list[a].add(b);
		}
		
		bfs(startCity);
		
		for(int i =1; i < distance.length; i++) {
			if(distance[i] == K) {
				sb.append(i).append("\n");
			}
		}
		if(sb.length() == 0) {
			sb.append(-1);
		}
		System.out.println(sb);
	}
	
	public static void bfs(int city) {
		
		q.offer(city);
		distance[city] = 0;
		
		while(!q.isEmpty()) {
			int tmp = q.poll();
			if(list[tmp] != null) {
				for(int m : list[tmp]) {
					if(distance[m] == -1) {
						q.offer(m);
						distance[m] = distance[tmp] + 1;
					}
					
				}
			}
		}
		
		
	}
	
	
}
	