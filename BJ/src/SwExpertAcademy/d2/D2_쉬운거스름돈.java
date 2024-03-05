package SwExpertAcademy.d2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class D2_쉬운거스름돈 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int T, money;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		T = Integer.parseInt(br.readLine());
		
		int[] list = {50000,10000,5000,1000,500,100,50,10};
		
		for(int i = 1; i <= T; i++) {
			money = Integer.parseInt(br.readLine());
			sb.append("#").append(i).append("\n");
			int[] result = new int[8];
			
			for(int j= 0; j< list.length;j++) {
				result[j] = money / list[j];
				money = money % list[j];
			}
			
			for(int tmp: result) {
				sb.append(tmp).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
