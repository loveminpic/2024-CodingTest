package aps.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 정육면체 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int R,C;
	static int DR,DC; // 주사위 초기위치 
	static int K;
	static int board[][];
	static int[] d;
	static int[] dx = {0,0,0,-1,1};
	static int[] dy = {0,1,-1,0,0};
	
	static int[] box1 = {0,0,0,0};
	static int[] box2 = {0,0};
	
 	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		DR = Integer.parseInt(tokens.nextToken());
		DC = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		board = new int[R][C];
		
		for(int i = 0; i < R; i++) {
			tokens = new StringTokenizer(br.readLine());
			for(int j = 0; j < C; j++) {
				board[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		tokens= new StringTokenizer(br.readLine());
		d = new int[K];
		for(int i = 0; i < K; i++) {
			d[i] =  Integer.parseInt(tokens.nextToken());
		}

		for(int i = 0; i < K; i++) {
			move(d[i]);
			
		}
		
	}

	private static void move(int i) {
		int[] newbox1 = new int[4];
		int rx = DR + dx[i];
		int ry = DC + dy[i];
		boolean version = false; // f : 주사위 바닥면이 -> 보드
		
		if(rx < 0 || rx >= R || ry < 0 || ry >= C) return;
		if(board[rx][ry] != 0) version = true; // t : 바닥면이 주사위로 그리고 보드는  0
		
		int tmp;
		switch (i) {
		case 1: // ->
			tmp  = box1[0]; // 원래 윗
			box1[0] = box2[0];
			box2[0] = box1[2];
			box1[2] = box2[1];
			box2[1] = tmp;
			if(!version) {
				board[rx][ry] = box1[2];
			}else {
				box1[2] = board[rx][ry];
				board[rx][ry] = 0;
			}
			break;
		case 2:
			tmp  = box1[0]; // 원래 윗
			box1[0] = box2[1];
			box2[1] = box1[2];
			box1[2] = box2[0];
			box2[0] = tmp;
			if(!version) {
				board[rx][ry] = box1[2];
			}else {
				box1[2] = board[rx][ry];
				board[rx][ry] = 0;
			}
			break;
		case 3:
			for(int j = 0; j < 4; j++) {
				tmp = j + 1 ;
				if(tmp > 3) tmp = 0;
				newbox1[tmp] = box1[j];
			}
			if(!version) {
				board[rx][ry] = newbox1[2];
			}else {
				newbox1[2] = board[rx][ry];
				board[rx][ry] = 0;
			}
			box1 = Arrays.copyOf(newbox1, newbox1.length);

		break;
			
		case 4:
			for(int j = 0; j < 4; j++) {
				tmp = j - 1 ;
				if(tmp < 0) tmp = 3;
				newbox1[tmp] = box1[j];
			}
			if(!version) {
				board[rx][ry] = newbox1[2];
			}else {
				newbox1[2] = board[rx][ry];
				board[rx][ry] = 0;
			}
			box1 = Arrays.copyOf(newbox1, newbox1.length);
			break;
		}
		DR = rx;
		DC = ry;
		System.out.println(box1[0]);
	}
}
