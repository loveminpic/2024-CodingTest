package aps.Silver.S4;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S4_29700 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int R,C,K;
	static int result = 0;
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		
		char[][] board = new char[R][C];
		for(int i = 0; i < R; i++) {
			String tmp = br.readLine();
			board[i] = tmp.toCharArray();
			checkPossible(board[i]);
		}

		System.out.println(result);
		
	}
	private static void checkPossible(char[] row) {
		int start = 0;
		int end = K-1;
		
		while(end < row.length) {
			if(row[start] == '0') {
				boolean check = true;
				for(int i = start+1; i <= end; i++ ) {
					if(row[i] == '1') {
						check = false;
						break;
					}
				}
				if(check) {
					result++;
				}
			}
			start++;
			end++;
		}
		
	}

}