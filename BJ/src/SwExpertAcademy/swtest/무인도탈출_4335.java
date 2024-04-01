package SwExpertAcademy.swtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 무인도탈출_4335 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	static int TC, N;
	static Box[] boxs;
	static List<int[]> boxsOrder = new ArrayList<int[]>();
	static int result = 0;
	
	static public class Box {
		int r;
		int c;
		int h;
	
		public Box(int r, int c, int h) {
			super();
			this.r = r;
			this.c = c;
			this.h = h;
		}

		@Override
		public String toString() {
			return "Box [r=" + r + ", c=" + c + ", h=" + h + "]";
		}
		
	
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		TC = Integer.parseInt(br.readLine());
		for(int t = 1; t <= TC; t++) {
			N = Integer.parseInt(br.readLine());
			boxs = new Box[N];
			
			for(int i = 0; i < N; i++) {
				tokens = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(tokens.nextToken());
				int c = Integer.parseInt(tokens.nextToken());
				int h = Integer.parseInt(tokens.nextToken());
				boxs[i] = new Box(r,c,h);
			}
			
			result = 0; //매번 테케마다 초기화
			boxsOrder = new ArrayList<int[]>(); // 초기
			
			// -------- 입력 및 초기화 ----------
			
			boxesPermutation(0, new int[N], new boolean[N]);
			
			for(int i = 0; i < boxsOrder.size(); i++) {
				int[] nums = boxsOrder.get(i);
				solved(new int[3] ,0 , nums, 0);
			}
			
			sb.append("#" + t + " ").append(result).append("\n");
		}
		System.out.println(sb);
	}

	private static void solved(int[] before ,int cnt, int[] list, int tmpResult) {
		
		if(tmpResult > result) {
			result = tmpResult;
		}
		
		if(cnt == N) {
			return;
		}
		
		int currentNum = list[cnt]; // {0,1}
		int r = boxs[currentNum].r;
		int c = boxs[currentNum].c;
		int h = boxs[currentNum].h;
		
		List<int[]> perList = new ArrayList<int[]>();
		perList = perMutationHardCoding(r,c,h);
		
		for(int i = 0 ; i < perList.size(); i++) {
			boolean check;
			if(before[0] + before[1] + before[2] == 0) {
				check = true;
			}else {
				check = checkPossible(before, perList.get(i));
			}
			
			if(check) {
				solved(perList.get(i), cnt+1, list, tmpResult + perList.get(i)[2]);
			}
		}
		
	}

	private static boolean checkPossible(int[] before, int[] is) {
		if(before[0] < is[0] || before[1] < is[1] ) return false;
		return true;
	}

	private static List<int[]> perMutationHardCoding(int r, int c, int h) {
		List<int[]> list = new ArrayList<int[]>();
		list.add(new int[] {r,c,h});
		list.add(new int[] {r,h,c});
		list.add(new int[] {c,r,h});
		list.add(new int[] {c,h,r});
		list.add(new int[] {h,r,c});
		list.add(new int[] {h,c,r});
		return list;
	}

	private static void boxesPermutation(int cnt, int[] choose, boolean[] visited) {
		if(cnt == N) {
			int[] tmp = new int [N];
			for(int i = 0; i < N; i++) {
				tmp[i] = choose[i];
			}
			boxsOrder.add(tmp);
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				choose[cnt] = i;
				boxesPermutation(cnt+1, choose, visited);
				visited[i] = false;

			}
		}
	}

}
