package SwExpertAcademy.swtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 디저트카페 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	
	static int TC, N;
	static int[][] board;
	static int result; 
	
	// 왼쪽 아래 대각선, 오른쪽 아래 대각선 오른쪽 위대각선, 왼쪽 위 대각선
	static int[] dx = {1, 1, -1, -1}; 
	static int[] dy = {-1, 1, 1, -1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		TC = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());
			board = new int[N][N];
			result = -1;
			
			for(int i = 0; i < N; i++) {
				tokens  = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(tokens.nextToken());
				}
			}
			
			//------Input-------
			for(int i = 0 ; i < N; i++) {
				for(int j = 0; j < N ; j++) {
					solved(i,j,i,j, new ArrayList<Integer>(), 0);
				}
			}
			
			
			sb.append("#" + tc + " " + result + "\n"); 
		}
		System.out.println(sb);
	}
	
	private static void solved(int r, int c, int cr, int cc, ArrayList<Integer> list, int d) {
	
		if(!list.isEmpty() && r == cr && c == cc) {
			if(list.size()> 3) {
				result = Math.max(result, list.size());
			}
			return;
		}
	
		int curX = cr + dx[d];
		int curY = cc + dy[d];
		
		if(curX < 0 || curX >= N || curY < 0 || curY >=N) return;
		
		if(list.contains(board[curX][curY])) return;
		else list.add(board[curX][curY]);
		
//		System.out.println("r : " + r + ", c : " +c  + ", cr : " +cr + ", cc : " + cc + ", d: " + d);
//		System.out.println(list);
	
		switch (d) {
		case 0: 
			solved(r,c,curX,curY, list, 0);
		case 1 :
			solved(r,c,curX,curY, list, 1);
		case 2 :
			solved(r,c,curX,curY, list, 2);
		case 3 :
			solved(r,c,curX,curY, list, 3);
		}	
		
		list.remove(list.size()-1);
	}

}
