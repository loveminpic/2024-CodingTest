package src.SwExpertAcademy.swtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 활주로건설 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	
	static int TC,N,X;
	static int result =0;
	static int[][] board;
	
	public static void main(String[] args) throws IOException {
		TC = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= TC; tc++) {
			tokens = new StringTokenizer(br.readLine());
			N = Integer.parseInt(tokens.nextToken());
			X = Integer.parseInt(tokens.nextToken());
			
			board = new int[N][N];
			int[][] newBoard = new int[N][N];
			result = 0;
			for(int i = 0; i < N; i++) {
				tokens = new StringTokenizer(br.readLine());
				for(int j = 0; j <N; j++) {
					board[i][j] = Integer.parseInt(tokens.nextToken());
					newBoard[j][i] = board[i][j];
				}
			}
			
			for(int[] tmp : board) {
				solved(tmp);
				
			}
			
			for(int[] tmp : newBoard) {
				solved(tmp);
			}
			sb.append("#"+ tc+ " " + result + "\n");
		}
		
		System.out.println(sb);
	}

	private static void solved(int[] arr) {
		int cnt = 0;
		boolean[] visited = new boolean[arr.length];
		
		for(int i = 0 ; i < arr.length-1; i++) {
			if(arr[i] == arr[i+1]) continue;
			if(Math.abs(arr[i]- arr[i+1]) > 1) return;
			
			// 내리막길 
			if(arr[i] - arr[i+1] == 1) {
				for(int j = i+1; j <= i+X; j++) {
					if(j >= arr.length || arr[j] != arr[i+1] || visited[j]) return; // 경사로 설치 불가 조건
					visited[j] = true; // 경사로 설치
				}
				i = i + X - 1; // 경사로 설치 후, 다음 확인 위치 업데이트
			// 오르막
			}else if(arr[i+1] - arr[i]  == 1) {
				for(int j = i; j > i-X; j--) {
					if(j < 0 || arr[j] != arr[i] || visited[j]) return; // 경사로 설치 불가 조건
					visited[j] = true; // 경사로 설치
				}	
			}
			
		}
		result++;
	}

}
