package src.Silver.S1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class S1_1074 {
	 static int r;
	 static int c;
	    
	public static void main(String[] args) throws IOException {
	   
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = br.readLine().split(" ");

		int N = Integer.parseInt(inputs[0]); // 2^N*2^N 을 만들기 위한 입력
		r = Integer.parseInt(inputs[1]); // r,c 입력
		c = Integer.parseInt(inputs[2]);

		int pow_n = (int) Math.pow(2, N);

		recursive(0, 0, pow_n, 0);
	}

	/**
	    * 
	    * @param si : 시작 i
	    * @param sj : 시작 j
	    * @param length : 시작점부터 해당 범위까지의 길이
	    * @param cnt : (si,sj)순서
	    */
	    public static void recursive(int si, int sj, int length, int cnt){ 
	    	// 기저조건
	    	if (length == 1) {
				if (si == r && sj == c) {
					System.out.println(cnt);
					return;
				}	
				return;
			}
	    	
	    	//1. 쪼개서 재귀를 타기 위해서 중간 값 잡아주기
	    	int half = length/2;
	    	
	    	if (r < si + half && c < sj + half) {
				recursive(si, sj, half, cnt);
			} else if (r < si + half && c < sj + length) {
				// 2번째 재귀
				recursive(si, sj + half, half, cnt + half * half);
			} else if (r < si + length && c < sj + half) {
				// 3번째 재귀
				recursive(si + half, sj, half, cnt + half * half * 2);
			} else if (r < si + length && c < sj + length) {
				// 4번째 재귀
				recursive(si + half, sj + half, half, cnt + half * half * 3);
			}
	    }
}