package SwExpertAcademy.swtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class 보호필름_2112 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int TC, D, W, K;
	static int[][] board;
	static int result;
	static List<int[]> permutaionList = new ArrayList<int[]>();
	static StringBuilder sb = new StringBuilder();
		
	public static void main(String[] args) throws NumberFormatException, IOException {
		TC = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= TC; t++) {
			tokens = new StringTokenizer(br.readLine());
			
			D = Integer.parseInt(tokens.nextToken()); // 세로 
			W = Integer.parseInt(tokens.nextToken()); // 가로 
			K = Integer.parseInt(tokens.nextToken()); // 연속되어야 하는 수 
			board = new int[D][W];
			result = 0;
			
			for(int i = 0; i < D ; i++) {
				tokens= new StringTokenizer(br.readLine());
				for(int j = 0; j < W; j++) {
					board[i][j] = Integer.parseInt(tokens.nextToken());
				}
			}
			
			// ------ input end ---------
			
			if(checkFilms(board)) {
				continue;
			}else {
				// 부분 조합을 만들어서, 조합의 길이가 작은 순으로 약품 투입! 
				// 이때 본래의 보드는 두고, 새로운 보드를 만들어서 사용해야함
				// 이때 어느때라도 성능을 만족하면 바로 break! 
				for(int i = 1; i <= D; i++) {
					permutaionList = new ArrayList<int[]>(); 
					permutaion(new int[i], new boolean[D], 0, i);
					int[][] tmpBoard = new int[D][W];
					for(int h = 0 ; h < board.length; h++) {
						tmpBoard[h] = Arrays.copyOf(board[h], board[h].length);
					}
					for(int p = 0; p < permutaionList.size(); p++) {
						int[][] newBoard = new int[D][W];
						newBoard = changeFilm(permutaionList.get(p), tmpBoard);
						if(checkFilms(newBoard)) {
							result = i;
							break;
						}
					}
				}
				
			}
			
			sb.append("#" + t + " ").append(result).append("\n");
		}
		System.out.println(sb);
	}

	private static int[][] changeFilm(int[] list, int[][] tmpBoard) {
		
		return null;
	}

	private static void permutaion(int[] choose, boolean[] visited, int cnt, int end) {
		if(cnt == end) {
			int[] tmp = new int[end];
			for(int j = 0; j < choose.length; j++) {
				tmp[j] = choose[j];
			}
			System.out.println(Arrays.toString(tmp));
			permutaionList.add(tmp);
			return;
		}
		
		for(int i = 0; i < D ; i++) {
			if(!visited[i]) {
				visited[i] = true;
				choose[cnt] = i;
				permutaion(choose, visited, cnt+1, end);
				visited[i] = false;
				
			}
		}
	}

	private static boolean checkFilms(int[][] tmpboard) {
		// 성능 검사 로직
		int cnt = 0;

		
		for(int i = 0; i < W; i++) {
			int tmp = tmpboard[0][i];
			boolean check = false;
			
			for(int j = 1; j < D; j++) {
				if(tmpboard[j][i] == tmp) {
					cnt++;
				}else {
					cnt = 0;
					tmp = tmpboard[j][i];
				}
				if(cnt == K-1) {
					check = true;
					break;
				}
			}
			if(!check) {
				return false;
			}
		}
		return true;
	}

}
