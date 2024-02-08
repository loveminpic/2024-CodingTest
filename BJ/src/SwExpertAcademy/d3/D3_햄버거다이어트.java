package src.SwExpertAcademy.d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;


public class D3_햄버거다이어트 {
	static int T, N, L;
	static Map<Integer, Integer> map = new HashMap<Integer, Integer>();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens; 
	static StringBuilder sb = new StringBuilder();
	static int resultKal = 0;
	static int resultScore = 0;
	static Integer[] keys;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		T = Integer.parseInt(br.readLine());
		for(int t = 1; t <=T ; t++) {
			resultKal = 0;
			tokens = new StringTokenizer(br.readLine());
			N = Integer.parseInt(tokens.nextToken());
			L = Integer.parseInt(tokens.nextToken());
			
			keys = new Integer[N];
			
			for(int i = 1; i <=N; i++) {
				tokens = new StringTokenizer(br.readLine());
				int key = Integer.parseInt(tokens.nextToken());
				int value = Integer.parseInt(tokens.nextToken());
				map.put(key, value);
				keys[i-1] = key;
			}
			
			boolean[] choose = new boolean[N];
			
			Arrays.sort(keys, Collections.reverseOrder());
			
			powerSet(0, choose);
			
			sb.append("#").append(t).append(" ");
			sb.append(resultScore).append("\n");
		}
		System.out.println(sb);
	}

	private static void powerSet(int cnt, boolean[] choose) {
		if(cnt == N) {
			findingMax(choose);
			return;
		}
		choose[cnt] = true;
		powerSet(cnt+1, choose);
		choose[cnt] = false;
		powerSet(cnt+1, choose);
	
	}

	private static void findingMax(boolean[] choose) {
		int sumKal = 0;
		int score = 0;
		int key; 
		for(int i = 0; i < N ; i++) {
			if(choose[i] == true) {
				key = keys[i];
				sumKal += map.get(key);
				score += key;
				if(sumKal > L) {
					sumKal = -1;
					break;
				}
			}
			
		}
		if(score > resultScore && sumKal != -1) {
			resultScore = Math.max(resultScore, score);
		}
		
	}
	
		

}
