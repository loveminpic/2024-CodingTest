package src.SSAFY.Lv3;

import java.util.ArrayList;

public class 가위바위보_2006 {

	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 1; i <= 15 ; i ++) {
			list.add(i);
		}
		
		int cnt = 1;
		for(int temp : list) {
			System.out.printf("%2d ",temp);
			if(temp == 5 |temp == 9 | temp == 12 |temp == 14) {
				System.out.printf("\n");
				for(int i = 0; i < cnt; i++) {
					System.out.printf("%2s ", "");
				}
				cnt++;
				
			}
		}
		
		
	}

}
