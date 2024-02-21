package src.PowerSetEveryDay;
import java.util.ArrayList;
import java.util.List;

public class Power20240220 {
	static int[] src = {1,2,3};
	static List<ArrayList<Integer>> list = new ArrayList<>();
	
	public static void main(String[] args) {
		
		powerSet(0, new boolean[src.length]);
		for(int i = 0; i < list.size();i++) {
			for(int j =0; j < list.get(i).size(); j++) {
				System.out.print(list.get(i).get(j));
			}
			System.out.println();
		}
	}

	private static void powerSet(int cnt, boolean[] choosed) {
		if(cnt == src.length) {
			ArrayList<Integer> tmp = new ArrayList<>();
			for(int i = 0; i < choosed.length; i++) {
				if(choosed[i] == true) {
					tmp.add(src[i]);
				}
			}
			
			list.add(tmp);
			return;
		}
		
		choosed[cnt] = true;
		powerSet(cnt+1, choosed);
		choosed[cnt] = false;
		powerSet(cnt+1, choosed);
	}

}
