package src.SwExpertAcademy;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class D3_1230_암호문3 {

	   private static LinkedList<String> list;

	   public static void main(String[] args) throws NumberFormatException, IOException {
	      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	      StringTokenizer st = null;
	      
	      for (int t = 1; t <= 10; t++) {
	         int N = Integer.parseInt(br.readLine());
	         st = new StringTokenizer(br.readLine());
	         list = new LinkedList<String>();
	         
	         int M = Integer.parseInt(br.readLine()); // 명령의 개수
	         st = new StringTokenizer(br.readLine());
	         for (int m = 0; m < M; m++) {
	            char order = st.nextToken().charAt(0);
	            switch(order) {
	               case 'I' : //삽입
	            	  int idx = Integer.parseInt(st.nextToken());
	            	  int num = Integer.parseInt(st.nextToken());
	            	  for(int i = idx; i < idx+num ;i++) {
	            		  list.add(i, st.nextToken());
	            	  }
	                  break;
	               case 'D' : //삭제
	            	  int idx = Integer.parseInt(st.nextToken());
		              int num = Integer.parseInt(st.nextToken());
	                  break;
	               case 'A' : //추가
	                  break;
	            }
	         }
	         
	         System.out.print("#"+t+" ");
	         for (int i = 0; i < 10; i++) { //출력 10개 숫자만 해주기
	            System.out.print(list.get(i)+" ");
	         }
	         System.out.println();

	         list.clear();
	      }
	      

	   }

	}
