package aps.Silver.S2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class S2_12891 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int[] ACGT = new int[4];
	static int len, cnt;
	static char[] list;
	static int  result = 0;
	
	public static void main(String[] args) throws IOException {
		
		// 입력시작
		tokens = new StringTokenizer(br.readLine());
		len = Integer.parseInt(tokens.nextToken());
		cnt = Integer.parseInt(tokens.nextToken());
		
		String temp = br.readLine();
		list = temp.toCharArray();

		tokens = new StringTokenizer(br.readLine());
		for(int i = 0; i < 4; i++) {
			ACGT[i] = Integer.parseInt(tokens.nextToken());
		}
		// 입력끝 
		
		checkingPossible();
		System.out.println(result);
		
	}

	
	public static void checkingPossible() {
		int start = 0;
		int end = cnt -1;
		
		HashMap<Character, Integer> map= new HashMap<Character, Integer>();	

		
		map.put('A', ACGT[0]);
		map.put('C', ACGT[1]); 
		map.put('G', ACGT[2]); 
		map.put('T', ACGT[3]);
		
		// 첫 번째 윈도우 초기화
        for (int i = start; i <= end; i++) {
            char c = list[i];
            map.put(c, map.get(c) - 1);
        }

        checkAndIncrement(map);
        // 슬라이딩 윈도우
        while (end < list.length - 1) {
            
        	// 시작점 문자 제거
            char startChar = list[start];
            map.put(startChar, map.get(startChar) + 1);
            start++;

            // 끝점 문자 추가
            end++;
            char endChar = list[end];
            map.put(endChar, map.get(endChar) - 1);

            checkAndIncrement(map);
        }
	       
	 }
	

	
	private static void checkAndIncrement(HashMap<Character, Integer> map) {
		boolean check = true;
		
		for(int t : map.values()) {
			if(t > 0) {
				check = false;
				break;
			}
		}
		
		if(check) {
			result++;
		}
	}
}
