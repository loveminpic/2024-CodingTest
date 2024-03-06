package aps.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 나무재테크 {
	static int N,treeCount, year;
	static int[][] board;
	static int[][] curBoard;
	static Deque<Tree> treeList = new LinkedList<Tree>();
	static List<Tree> dieTreeList;
	static int[] dx = {-1,-1,-1,0,0,1,1,1};
	static int[] dy = {-1,0,1,-1,1,-1,0,1};

	static class Tree implements Comparable<Tree>{
		int r;
		int c;
		int age;
		int weight;
		
		public Tree(int r, int c, int age) {
			super();
			this.r = r;
			this.c = c;
			this.age = age;
		}
		
		public Tree(int r, int c, int age, int weight) {
			super();
			this.weight = weight;
		}


		@Override
		public String toString() {
			return "Tree [r=" + r + ", c=" + c + ", age=" + age + ", weight=" + weight + "]";
		}

		@Override
		public int compareTo(Tree o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.age, o.age);
			
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(tokens.nextToken());
		treeCount = Integer.parseInt(tokens.nextToken());
		year = Integer.parseInt(tokens.nextToken());
		
		board = new int[N+1][N+1];
		curBoard = new int[N+1][N+1];
		
		
		for(int i = 1; i <= N; i++) {
			tokens = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N ; j++) {
				board[i][j] = Integer.parseInt(tokens.nextToken());
				curBoard[i][j] = 5;
			}
		}
		
		for(int t =0; t < treeCount; t++) {
			tokens = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(tokens.nextToken());
			int c = Integer.parseInt(tokens.nextToken());
			int age = Integer.parseInt(tokens.nextToken());
			Tree tmp = new Tree(r,c,age);
			treeList.add(tmp);
		}
		
		while(year-- > 0) {
			dieTreeList = new ArrayList<Tree>(); // 매년 초기화 해줘야함. 
			
			//spring();
			for(int s = 0; s < treeList.size();) {
				Tree tmp = treeList.poll();
				if(curBoard[tmp.r][tmp.c] >= tmp.age) {
					tmp.weight += tmp.age;
					curBoard[tmp.r][tmp.c] -= tmp.age;
					tmp.age += 1;
					treeList.add(tmp);
					s++;
				}else {
					dieTreeList.add(tmp);
				}
			}
			
			//summer()
			for(int s = 0; s< dieTreeList.size(); s++) {
				Tree tmp = dieTreeList.get(s);
				curBoard[tmp.r][tmp.c] += tmp.age / 2;

			}
			
			// fall();
			Queue<Tree> q = new ArrayDeque<Tree>();
			
			for(Tree m : treeList) {
				if(m.age % 5 == 0) {
					q.add(m);
				}
			}
			
			while(!q.isEmpty()) {
				Tree tmp = q.poll();
			
				for(int d = 0; d < 8; d++) {
					if(tmp.r + dx[d] > 0 && tmp.r + dx[d] <= N && tmp.c + dy[d] > 0 && tmp.c + dy[d] <= N ) {
						Tree newTree = new Tree(tmp.r+dx[d], tmp.c+dy[d],1);
						treeList.addFirst(newTree);
					}
				}
			}
			// winter();			
			for(int s = 1; s <= N; s++) {
				for(int h = 1; h <= N; h++) {
					curBoard[s][h] += board[s][h];
				}
			}
		}
		
		System.out.println(treeList.size());
	}

}
