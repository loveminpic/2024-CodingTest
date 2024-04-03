package SwExpertAcademy.swtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 부분집합_3 {
   static int N, M; //원소의 개수 N, 뽑을 개수 M
   static int number[];//원소들을 담을 배열
   static int ans[];//만들어진 순열/조합/부분집합 결과를 담을 배열
   static boolean check[];
   
   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      //원소의 개수 
      N= Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      number=new int[N];
      
      st = new StringTokenizer(br.readLine());
      for (int i = 0; i < N; i++) {
         number[i] = Integer.parseInt(st.nextToken());
      }
      //---------INPUT END--------------------
      
      check = new boolean[N]; // 뽑았던 다시 뽑기 방지용 
      ans= new int[M];
      //순열 만들기
//      permu(0);
      // 주어진 n개 중에 m개 뽑기, 순서가 영향을 미침
      
      //조합 만들기
//      combi(0,0,M);
      // 순서가 영향 안미침
      
      //조합 활용해서 부분집합 만들기(공집합 제외 // 경우의 수를 만들기 
//      for (int m = 1; m <= N; m++) {
//         ans = new int[m]; //배열이 불편하면 list로 만들어줘도 됨
//         combi(0,0,m);
//      }
      
//      ans = new int[N]; //선택 여부 기록 용
//      subset(0, 0);

   }
   

   private static void permu(int cnt) {
	  
	   if(cnt == M) {
		   System.out.println(Arrays.toString(ans));
		  return;
	  }
	  
	   for(int i = 0; i < N; i++) {
		   if(!check[i]) {
			   check[i] = true;
			   ans[cnt] = number[i];
			   permu(cnt+1);
			   check[i] = false;
		   }
	   }
   }
   

   private static void combi(int cnt, int idx, int m) {
	   if(cnt == m) {
		   System.out.println(Arrays.toString(ans));
		   return;
	   }
	   	for(int i = idx; i < N ; i++) {
	   		ans[cnt] = number[i];
	   		combi(cnt+1, i+1, m);
	   	}
   }
   
   private static void subset(int cnt, int idx) {
	   if(idx==N) {
		  for (int i = 0; i < N; i++) {
			if(ans[i]!=0) {
				System.out.print(number[i]+" ");
				
			}
		}
		  System.out.println();
		  return;
	   }
	   
	   //안뽑는 경우 
	   ans[idx]=0;
	   subset(cnt, idx+1);
	   //뽑은 경우
	   ans[idx] = 1;
	   subset(cnt+1, idx+1);
   }

}
