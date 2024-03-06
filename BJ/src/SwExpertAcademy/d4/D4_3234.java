package SwExpertAcademy.d4;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class D4_3234 {
 
    static int T, N, res, arr[];

    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            res = 0;//결과값을 저장할 변수 초기화
            N = Integer.parseInt(br.readLine()); //추의 개수
            arr = new int[N]; //추 무게 기록
            
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            //------------INPUT END-----------------
            
            balances(0,0,0);

            System.out.println("#" + tc + " " + res);
        }
 
    }
    

    /**
     * 추 줄세우기(dfs의 형태)
     * @param cnt : 사용된 추의 개수
     * @param sumL : 왼쪽 저울에 올린 무게
     * @param sumR : 오른쪽 저울에 올린 무게
     */
    private static void balances(int cnt,int sumL, int sumR) {
    	//기저조건 : 끝나는 조건
    	if(cnt ==N) { //N개의 추를 다 뽑음
    		//추를 놓을 수 있는 1가지의 경우의 수 완성!
    		res++;
    		return;
    	}
    	
    	//N개 중에 N개 를 세울 경우 nPn = N!
    	//세울 수 있는 추의 경우의 수(순열)
    	for (int i = cnt; i < N; i++) {
			swap(cnt, i); 
			//이번 추로 선택 - 왼쪽에 올리기 (무조건 올림)
			balances(cnt+1, sumL+arr[cnt], sumR);
			//오른쪽은 조건이 걸림
			if(sumR+arr[cnt]<=sumL)
				balances(cnt+1, sumL, sumR+arr[cnt]);
			swap(cnt, i);
		}

    }
 
  
    
    private static void swap(int idx, int idx2) {
       int temp = arr[idx];
       arr[idx]=arr[idx2];
       arr[idx2]=temp;       
    }
}
