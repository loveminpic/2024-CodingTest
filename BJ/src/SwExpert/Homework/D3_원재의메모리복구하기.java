package src.SwExpert.Homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @author 
 * @date 
 * @link
 * @keyword_solution  
 * @input 
 * @output   
 * @time_complex  
 * @perf 
 */

public class D3_원재의메모리복구하기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int T;
	static int[] original;
	static int[] current;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		T = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < T; i++) {
			int cnt = 0;
			char[] temp = br.readLine().toCharArray();
			original = new int[temp.length];
			
			for(int j = 0; j < temp.length; j++) {
				original[j] = (int) temp[j] - 48;
			}
			current = new int[original.length];
			Arrays.fill(current,0);
			
			
			while(!Arrays.equals(original,current)) {
				for(int j = 0; j < original.length; j++) {
					if(original[j] != current[j]) {
						Arrays.fill(current,j,original.length,original[j]);
						cnt++;
						break;
					}
				}
				
			}
			System.out.printf("#%d %d",i+1,cnt);
		}
	}

}
