package src.CombiEveryDay;
import java.util.Arrays;

public class Combi20240220 {
	// nCr �� ���� ������� ������ �Ǹ��
	static int[] src = { 1,2,3};
	static int N = 2;
	public static void main(String[] args) {
		
		combi(0,0, new int[N]);
	}
	private static void combi(int start, int cnt, int[] choosed) {
		
		if(cnt == N) {
			System.out.println(Arrays.toString(choosed));
			return;
		}
		for(int i = start; i < src.length; i++) {
			choosed[cnt] = src[i];
			combi(i+1, cnt+1, choosed);
		}
	}

}
