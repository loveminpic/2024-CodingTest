package src.Silver.S2;
/**
 * @author Miji Lee
 * @date 20230207
 * @link
 * @keyword_solution 인접리스트 활용?
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
import java.util.StringTokenizer;

public class S2_18352 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int cityNum,loadNum,K,startCity;
	static ArrayList<Integer> result = new ArrayList<Integer>();
	static ArrayList<Integer>[] list;
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		cityNum = Integer.parseInt(tokens.nextToken());
		loadNum = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		startCity = Integer.parseInt(tokens.nextToken());
		list = new ArrayList[cityNum+1];
		
		// 인접리스트 만들
		for(int i =1 ; i <= loadNum; i++) {
			tokens = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(tokens.nextToken());
			int b = Integer.parseInt(tokens.nextToken());
			if(list[a] == null) {
				list[a] = new ArrayList<Integer>();
			}
			list[a].add(b);
		}
		
		checkingCity(startCity, 0);
		
	}
	
	public static void checkingCity(int startCity, int cnt) {
		if(cnt == K) {
			result.add(startCity);
			return;
		}
		for(int i = 0; i < list[startCity].size(); i++) {
			int nextCity = list[startCity].get(i);
			if(cnt == K) {
				result.add(nextCity);
			}
			if(!list[nextCity].isEmpty()) {
				checkingCity(nextCity, cnt+1); 
			}
			cnt++;
		}
	}

}
