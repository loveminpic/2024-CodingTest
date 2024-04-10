package aps.Silver.S1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class S1_16926 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens; 
	static StringBuilder sb = new StringBuilder();
	static int N,M,R;
	static int[][] board;
	
	
	public static void main(String[] args) throws IOException {
		
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		R = Integer.parseInt(tokens.nextToken());
		
		board = new int[N][M];
		for(int i = 0; i < N; i++) {
			tokens = new StringTokenizer(br.readLine());
			for(int j = 0; j < M ; j++) {
				board[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		rotate(board, N, M, R);
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M ; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
		
	}
	  public static void rotate(int[][] arr, int N, int M, int R) {
	        int cnt = Math.min(N, M) / 2; // 회전할 사각형(레이어)의 수

	        for (int idx = 0; idx < cnt; idx++) {
	            int maxN = N - idx - 1;
	            int maxM = M - idx - 1;
//	            int[] temp = new int[(maxN - idx + maxM - idx) * 2]; // 임시 배열
	            Queue<Integer> temp = new ArrayDeque<>();
	            int k = 0;

	            // 상단
	            for (int i = idx; i < maxM; i++) {
	            	temp.add(arr[idx][i]);
	            }
	            // 오른쪽
	            for (int i = idx; i < maxN; i++) temp.add(arr[i][maxM]);
	            // 하단
	            for (int i = maxM; i > idx; i--) temp.add(arr[maxN][i]);
	            // 왼쪽
	            for (int i = maxN; i > idx; i--) temp.add(arr[i][idx]);
	            
	   
	            for(int i = 0; i < R; i++) {
	            	int tmp = temp.poll();
	            	temp.add(tmp);
	            }

	            k = 0;
	            for (int i = idx; i < maxM; i++) arr[idx][i] = temp.poll();
	            for (int i = idx; i < maxN; i++) arr[i][maxM] = temp.poll();
	            for (int i = maxM; i > idx; i--) arr[maxN][i] = temp.poll();
	            for (int i = maxN; i > idx; i--) arr[i][idx] = temp.poll();
	        }
	    }
}
