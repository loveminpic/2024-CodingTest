package SwExpertAcademy.swtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class 숫자만들기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens ;
	static StringBuilder sb = new StringBuilder();
	static int TC, N;
	static long mx = -100000000;
	static long min = 100000000;
	static List<Integer[]> perList = new ArrayList<>();
	static int [] opList ;
	static int[] num_list;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		TC = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= TC; t++) {
			N = Integer.parseInt(br.readLine());
			num_list = new int[N];
			perList = new ArrayList<>();
			opList =  new int[4];
			
			tokens = new StringTokenizer(br.readLine());
			mx = -100000000;
			min = 100000000;
			
			int cnt = 0;
			
			for(int i = 0; i < 4; i++) {
				int num = Integer.parseInt(tokens.nextToken());
				
				opList[i] = num;
				
			}
			
			tokens = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				num_list[i] = Integer.parseInt(tokens.nextToken());
			}
			
			permutation(1, num_list[0]);
			
			long result = 0;
			if(mx >min) {
				result = mx - min;
			}else {
				result = min-mx;
			}
			
			sb.append("#"+t+" ").append(result + "\n");
		}
		System.out.println(sb);
		
	}

	private static void permutation(int cnt, int result) {
		if(cnt == N) {
			mx = Math.max(mx, result);
			min = Math.min(min, result);
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			if(opList[i] > 0) {
				int new_num = result;
				switch(i) {
					case 0:
						new_num += num_list[cnt];
						break;
					case 1:
						new_num -= num_list[cnt];
						break;
					case 2:
						new_num *= num_list[cnt];
						break;
					case 3:
						new_num /= num_list[cnt];
						break;
				}
				opList[i] --;
				permutation(cnt+1, new_num);
				opList[i] ++;
			}
		}
	}

}
