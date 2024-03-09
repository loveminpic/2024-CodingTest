package aps.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 불안한무빙워크 { 
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int N,K;
	static int cnt;
	static BeltNum[] list;
	static int start = 1;
	
	
	static class BeltNum {
		boolean person = false;
		int safe;
		int current; // 계속 카운트 업하면 현재 위치를 나타
		
		public BeltNum(int safe, int current) {
			super();
			this.safe = safe;
			this.current = current;
			
		}

		@Override
		public String toString() {
			return "BeltNum [person=" + person + ", safe=" + safe + ", current=" + current + "]";
		}
		
		
	}
	public static void main(String[] args) throws IOException {
		
		// 생각해야하는 포인트... 
		// 안정성의 넘버를 저장할 곳이필요하고, 
		// 레일이 돌아가는것도 있고, 사람이 있는지 없는지도 체크해야하고
		// 안정성이 0이 되는 겟수도 체크하고
		// 결국 n 번째 자리에 오는 사람은 내리기도 해야함! 
		tokens = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		tokens = new StringTokenizer(br.readLine());
		
		list = new BeltNum[N*2+1];
		for(int i = 1; i < N*2+1; i++) {
			list[i] = new BeltNum(Integer.parseInt(tokens.nextToken()), i);
		}
		
		int result = solved();
		
		System.out.println(result);
	}
	
	private static int solved() {
		
		int result = 0;
		
		while(K > 0) {
			result++;
			
			moving(start); // 6
			findN(start);
			setPerson(start); // 6에 사람 
			int currentP = start; // 6
			moving(start); // 5
			findN(start);
			for(int i = 0; i < N*2; i++) {
				int tmp = currentP;
				if(list[tmp].person) {
					checkingPerson(tmp); // 6사람 1로 움직이
				}
				tmp--;
				if(tmp == 0) {
					tmp = N*2;
				}
			}
			findN(start);
			moving(start); // 6
			findN(start);
			if(K == 0) {
				break;
			}

		}
	
		return result;
		
	}
	private static void findN(int start2) {
		// 만약에 n 번째 자리에 사람이 있으면 내쫒
		int newN = start2 + N -1;
		if(newN > N*2) {
			newN = start2 - N -1;
		}
		
		if(list[newN].person) {
			list[newN].person = false;
		}
		
	}

	private static void setPerson(int point) {
		if(!list[point].person && list[point].safe > 0) {
			list[point].person = true;
			list[point].safe -= 1;
			if(list[point].safe == 0) {
				K--;
			}
		}
		
	}

	private static void checkingPerson(int point) {
		int current = point;
		if(point+1 == N*2+1) {
			point = 1;
		}
		
		if(!list[point].person && list[point].safe > 0) {
			list[point].person = true;
			list[point].safe -= 1;
			if(list[point].safe == 0) {
				K--;
			}
			list[current].person = false;
		}
		
	
	}

	private static void moving(int num) {
		// start 포인트 변경해주
		num--;
		if(num == 0) {
			start = N*2;
		}else {
			start = num;
		}
		
	}

}
