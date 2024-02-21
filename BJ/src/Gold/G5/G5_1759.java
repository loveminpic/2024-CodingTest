package src.Gold.G5;
/**
 * @author Minji Lee
 * @date 20240221
 * @link https://www.acmicpc.net/problem/1759
 * @keyword_solution 조합 + 정렬 + 서로소
 * @input (3 ≤ L ≤ C ≤ 15)
 * @output  조건을 만족하는 가능성있는 암호의 조합
 * @time_complex  
 * @perf 12196	92
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class G5_1759 {
	static int L,C; 
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static List<char[]> combiList = new ArrayList<>();
	static List<String> result = new ArrayList<>();
	static char[] src;
	static char[] mo = {'a','e','i','o','u'};
	
	
	public static void main(String[] args) throws IOException {
		
		tokens = new StringTokenizer(br.readLine());
		L = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		
		src = new char[C];
		tokens = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < C; i++) {
			src[i] = tokens.nextToken().charAt(0);
//			System.out.println(src[i]);
		}
		
		combi(0,0,new char[L]);
		
		for(char[] tmp : combiList) {
			checkingPossible(tmp);
		}
		
		Collections.sort(result);
		for(String tmp : result) {
			System.out.println(tmp);
		}
	}

	private static void checkingPossible(char[] list) {
		int first = 0;
		int second = 0; 
		Arrays.sort(list);
		for(int i = 0; i < list.length; i++) {
			if(list[i] == 'a' ||list[i] == 'e'||list[i] == 'i'||list[i] == 'o'|| list[i] == 'u' ) {
				first += 1;
			}else {
				second += 1;
			}
		}
		
		if(first >=1 && second >= 2) {
			String str = String.valueOf(list);
			result.add(str);
		}
	}

	private static void combi(int start, int cnt, char[] choosed) {
		if(cnt == L) {
			combiList.add(Arrays.copyOf(choosed, choosed.length));
			return;
		}
		
		for(int i = start; i < src.length; i++) {
				choosed[cnt] = src[i];
				combi(i+1, cnt+1, choosed);
		}
	}
}
