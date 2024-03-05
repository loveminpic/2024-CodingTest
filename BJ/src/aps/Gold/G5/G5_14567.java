package aps.Gold.G5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G5_14567 {
	static int N,M;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int[] result ;
	static ArrayList<Integer>[] subjects;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		result = new int[N+1]; // 1부터 
		Arrays.fill(result, 1);
		subjects = new ArrayList[N+1];
		
		int a,b ;
		
		for(int i = 1; i <= M; i++) { 
			tokens = new StringTokenizer(br.readLine());
			a = Integer.parseInt(tokens.nextToken());
			b = Integer.parseInt(tokens.nextToken());
			
			if(subjects[b] == null) {
				subjects[b] = new ArrayList<Integer>();
			}
			
			subjects[b].add(a);
			result[b] = -1;
		}
		
		int cnt = 2;
		
		while(true) {
			for(int i = 1; i < subjects.length; i++) {
				if(subjects[i] != null) {
					boolean check = true;
					for(int j = 0; j < subjects[i].size(); j++) {
						int temp = subjects[i].get(j);
						if(result[temp] == -1 || result[temp] == cnt) {
							check = false;
						}
					}
					if(check) {
						result[i] = cnt;
						subjects[i] = null;
					}
				}
				
			}
			boolean allNull = true;
			
			for (ArrayList<Integer> item : subjects) {
                if (item != null) {
                    allNull = false; // null이 아닌 요소를 찾으면 allNull을 false로 설정하고 반복 중단
                    break;
                }
            }
			if(allNull) {
				break;
			}
			cnt++;
		}
		for(int j = 1; j < result.length; j++) {
			sb.append(result[j]).append(" ");
		}
		System.out.println(sb);
	}

}


/*
for(int i = 1; i <= subjects.length; i++) {
int num = subjects[i].get(0);
if(result[num] != 0) {
	if(subjects[i].isEmpty()) {
		result[i] = cnt;
	}
	else {
		boolean check = true;
		for(int j = 0; j< subjects[i].size(); j++) {
			if(result[j] == 0 || result[j] == cnt) {
				check = false;
				
			}
		}
		if(check) {
			result[i] = cnt;
		}
	}
}

}
cnt++;
*/