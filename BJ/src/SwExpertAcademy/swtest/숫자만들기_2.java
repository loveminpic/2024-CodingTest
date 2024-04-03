package SwExpertAcademy.swtest;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 숫자만들기_2 {
	static int N;
	static int[] opers; //연산자 '+', '-', '*', '/' 순서
	static int[] numbers; //숫자
	static int Min, Max;
	

	
	public static void main(String[] args) throws NumberFormatException, IOException {

		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 StringTokenizer st = null;
		 StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T;t++) {
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			//@@ TODO: 입력으로 주어진 연산자를 목적에 맞게 입력 받으세요

			//연산에 활용할 숫자 정보
			numbers = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N;i++) {
				numbers[i]=Integer.parseInt(st.nextToken());
			}
			
			//--------------INPUT END-------------------------
			//테스트 케이스 별로 값이 유지 되기 때문에 초기화 필수
			Min =Integer.MAX_VALUE;
			Max = Integer.MIN_VALUE;
			//@@ TODO : 연산자 순열을 만들어서, 수식을 완성시켜 보세요.
			
			
			sb.append(String.format("#%d %d%n",t,Max-Min));
			
		}
		System.out.println(sb);

	}
	
	/**
	 * @TODO : 연산자 순열 만들기
	 */
	static void makePer() {
		
	}
	
	/**
	 * @TODO : 연산식 계산하기
	 * @return
	 */
	static int calc() {
		return 0;
	}
}
