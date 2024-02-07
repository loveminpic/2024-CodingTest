package src.SwExpertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_2930_힙 {
   private static int T, N;
   public static void main(String[] args) throws NumberFormatException, IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = null;
      StringBuilder sb = new StringBuilder();
      T = Integer.parseInt(br.readLine());
      for (int t = 1; t <= T; t++) {
         sb.append("#").append(t).append(" ");
         N = Integer.parseInt(br.readLine());
         PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
         for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            switch (Integer.parseInt(st.nextToken())) {
            case 1: //힙에 값을 넣기
               int num = Integer.parseInt(st.nextToken());
               pq.add(num);
               break;
            case 2:
               int tmp=-1;
               if(!pq.isEmpty())
                  tmp = pq.poll();
               sb.append(tmp).append(" ");
               break;
            }
         }
         sb.append("\n");
      }
      System.out.println(sb.toString());

   }

}