package src.Class;

import java.util.Arrays;
import java.util.Scanner;

public class AdjMatrixTest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int V = sc.nextInt();
		int E = sc.nextInt();
		
		int[][] adjMatrix = new int[V][V];
		for(int i  =0 ; i < E; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			adjMatrix[from][to] = adjMatrix[to][from] = 1; // 무향이므로 간선 양방향 처리!
			
		}
		
		for(int[] row : adjMatrix) {
			System.out.println(Arrays.toString(row));
		}

	}

}
/*
7
8
0 1
0 2
0 5
0 6
4 3
5 3
5 4
6 4
*/