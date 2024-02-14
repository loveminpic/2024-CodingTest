package src.SwExpertAcademy.d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class D3_NQueen {
   static int N;
   static int[] selected;

   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int T = Integer.parseInt(br.readLine());

      for (int t = 1; t <= T; t++) {
         N = Integer.parseInt(br.readLine()); // N*N에 N개의 퀸 놓기 입력 받기
         selected = new int[N]; // 행을 뜻함.입력값은 해당행에 퀸을 놓을 컬럼
//         System.out.println(solve(0));
         System.out.println("#" + t + " " + solve(0));
      }
   }

   public static int solve(int index) {
      // N번 인덱스까지 놓으면 완성.(1가지 방법 찾음) ->기저조건
      if (index == N) {
         return 1;
      }

      int ret = 0;
      // 0~N-1 컬럼에 모두 퀸을 넣ㅇ
      for (int i = 0; i < N; i++) {
         selected[index] = i; // index행 i번 컬럼에 퀸 놓아보기

         if (possible(index)) // 가능한지 확인
            ret += solve(index + 1); // 가능하면 다음 놔보러가기
      }

      return ret; //누적된 값을 return
   }

   public static boolean possible(int index) {
        for(int i = 0; i < index; i++) {
           //이미 다른곳에서 선택된 행이면 놓을 수 없음
            if(selected[i] == selected[index])
                return  false;
            
            //대각선상에 놓여있는 경우(열의 차와 행의 차가 같은 경우는 대각선상에 놓여있는 경우이다.)
            if(Math.abs(index - i) == Math.abs(selected[index] - selected[i]))
              return false;
        }
         
        return true;
    }

}