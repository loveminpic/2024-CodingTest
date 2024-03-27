package aps.Gold.G4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class G4_2239 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[][] board;

	static boolean[] row = new boolean[10];
	static boolean[] col = new boolean[10];
	
	public static void main(String[] args) throws IOException {
		board = new int[9][9];
		for(int i = 0; i < 9 ; i++) {
			String tmp = br.readLine();
			for(int j = 0; j < 9; j++) {
				board[i][j] = tmp.charAt(j) - '0';
			}
		}
	
		solved(0,0);
	}

	private static void solved(int row, int col) {
		if(col == 9) {
			solved(row+1, 0);
			return;
		}
		
		if(row == 9) {
			for(int i = 0; i < 9; i++){
                for(int j = 0; j < 9; j++)
                    System.out.print(board[i][j]);
                System.out.println();
            }

            System.exit(0);
		}
		
		if(board[row][col] == 0) {
			for(int i = 1; i <= 9; i++) {
				if(check(row, col, i)) {
					board[row][col] = i;
					solved(row,col+1);
				}
			}
			board[row][col] = 0;
			return;
		}else {
			solved(row, col+1);
		}
	}


	
	static boolean check(int row, int col, int val){

        for(int i = 0; i< 9; i++){
            if(board[row][i] == val)
                return false;
        }
        

        for(int i = 0; i<9; i++){
            if(board[i][col] == val)
                return false;
        }
        
        int rowStart = (row/3)*3;
        int colStart = (col/3)*3;

        for(int i = rowStart; i<rowStart+3; i++){
            for(int j = colStart; j<colStart+3; j++)
                if(board[i][j] == val)
                    return false;
        }

        return true;
    }

}
