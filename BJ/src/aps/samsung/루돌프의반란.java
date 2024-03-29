package aps.samsung;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 루돌프의반란 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static StringBuilder sb = new StringBuilder();
    static List<Santa> santaList = new ArrayList<>();
    static int N; // 보드 사이즈
    static int M; // 돌아야하는 게임 턴
    static int P; // 산타의 수
    static int C; // 루돌프가 움직여서 충돌이 일어날때 얻는 점수
    static int D; // 산타가 움직여서 충돌이 일어날때 얻는 점수
    static int[] deer = new int[2]; // 루돌프 위치
    static int[][] board;
    static int[] dx = {-1,0,1,0,-1,-1,1,1}; // 상우하좌 먼저! 
    static int[] dy = {0,1,0,-1,-1,1,1,-1};
    		
    static public class Santa {
        int number;
        int x;
        int y;
        boolean die = false; // true = 죽음, false = 아직 안죽음
        int wakeUpTime = 0;
        int score = 0;
        
        public Santa(int number, int x, int y) {
            super();
            this.number = number;
            this.x = x;
            this.y = y;
        }
        
        public void liveBonues() {
            this.score++;
        }
        public void timeToWake() {
        	this.wakeUpTime--;
        }
        
    }
    
    public static void main(String[] args) throws IOException {
        
        // 입력 받기 
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());
        P = Integer.parseInt(tokens.nextToken());
        C = Integer.parseInt(tokens.nextToken());
        D = Integer.parseInt(tokens.nextToken());
        
        // 보드 입력 
        board = new int[N+1][N+1];
        
        // 루돌프 위치 입력 받기
        tokens = new StringTokenizer(br.readLine());
        deer[0] =  Integer.parseInt(tokens.nextToken());
        deer[1] =  Integer.parseInt(tokens.nextToken());
        // 루돌프 보드에 찍기 
        board[deer[0]][deer[1]] = -1;
        
        santaList.add(new Santa(0,0,0));
        // 산타 리스트~ 
        for(int i = 1; i <= P; i++) {
            tokens = new StringTokenizer(br.readLine());
            int number = Integer.parseInt(tokens.nextToken());
            int x = Integer.parseInt(tokens.nextToken());
            int y = Integer.parseInt(tokens.nextToken());
            santaList.add(new Santa(number, x, y));
            board[x][y] = number; // 보드에 산타 찍기 (넘버)
        }
        
        //--------------------입력 끝 --------------------
        int count_M = 0; 
        while(++count_M <= M) {
            
        	deerMove();
            santaMove();
            
            
            // 산타가 다 탈락할 경우 종료
            boolean check = true;
            for(int i = 1; i <= santaList.size(); i++) {
                if(santaList.get(i).die == false) {
                    check = false;
                    break;
                }
            }
            if(check) break;
            
            // 탈락하지 않은 산타들 점수 1점씩 
            for(int i = 1; i <= santaList.size(); i++) {
                if(santaList.get(i).die == false) {
                	santaList.get(i).liveBonues();
                }
            }
        }
        
        
        
        
        
        
        // -------------- 출력 ---------------------------
        for(int i = 1; i <= santaList.size(); i++) {
            sb.append(santaList.get(i).score).append(" ");
        }
        System.out.println(sb);
    }

    private static void deerMove() {
    	int santaNum = 0;
    	int dist = Integer.MAX_VALUE;
    	
    	// 가장 가까운 산타 찾기!
    	for(int i = 1; i < santaList.size(); i++) {
    		if(santaList.get(i).die) continue; // 탈락하지 않은 산타 중에 고르기 
    		int tmp = distCheck(deer[0], deer[1], santaList.get(i).x,  santaList.get(i).y);
    		if(tmp < dist) {
    			santaNum = i;
    			dist = tmp;
    		}else if(tmp == dist) {
    			if(santaList.get(i).x > santaList.get(santaNum).x) {
    				santaNum = i;
    				dist = tmp;
    			}else if(santaList.get(i).x == santaList.get(santaNum).x) {
    				if(santaList.get(i).y > santaList.get(santaNum).y) {
        				santaNum = i;
        				dist = tmp;
    				}
    			}
    		}
    	}
    	
    	// 해당 산타 방향으로 1 전진 
    	deerOneMoveToSanta(deer[0], deer[1], santaList.get(santaNum).x, santaList.get(santaNum).y);
    	
    	
    	
    	
	}

    // r1,c1 = 루돌프 , r2,c2 = 산타 
	private static void deerOneMoveToSanta(int r1, int c1, int r2, int c2) {
		int distance = Integer.MAX_VALUE;
		int[] newPosFordeer = new int[2];
		int direction = -1;
		
		for(int i = 0; i < 8; i++) {
			int rx = r1 + dx[i];
			int ry = c1 + dy[i];
			int tmpdist;
			if(rx <= 0 || ry <= 0 || rx > N || ry > N) continue;
			tmpdist = distCheck(r2,c2,rx,ry);
			
			if(tmpdist < distance) {
				distance = tmpdist;
				newPosFordeer[0] = rx;
				newPosFordeer[1] = ry;
				direction = i;
			}
		}
		
		board[deer[0]][deer[1]] = 0; // 루돌프 자리 빼주
		deer[0] = newPosFordeer[0];
		deer[1] = newPosFordeer[1];
		
		// 산타가 있다면 충돌 발
		if(board[deer[0]][deer[1]] > 0) {
			int santaNum = board[deer[0]][deer[1]];
			crush(santaNum, direction , C);
			santaList.get(santaNum).wakeUpTime = 2;
		}
		// 루돌프 자리 세팅
		board[deer[0]][deer[1]] = -1;
		
		
		
	}

	private static void crush(int santaNum, int d, int score) {
		santaList.get(santaNum).score += score;
		
		// 산타 자리 지워주고~ 
		board[santaList.get(santaNum).x][santaList.get(santaNum).y] = 0;
		
		int rx = santaList.get(santaNum).x + (dx[d] * score);
		int ry = santaList.get(santaNum).y + (dy[d] * score);
		
		if(rx <= 0 || ry <= 0 || rx > N || ry > N) {
			santaList.get(santaNum).die = true;
			return;
		}
		// 산타 위치 재설정 해주고~ 
		santaList.get(santaNum).x = rx;
		santaList.get(santaNum).y = ry;
		
		if(board[rx][ry] > 0) {
			// 상호작용 
		}
		
		// 보드에도 다시 입력해주고! 
		board[rx][ry] = santaNum;
		
	}

	private static void santaMove() {
		for(int i = 1; i < santaList.size(); i++) {
			Santa tmpSanta = santaList.get(i);
			if(tmpSanta.die || tmpSanta.wakeUpTime != 0) continue;
			santaMoveToDeer(deer[0],deer[1],i);
			
		}
	}
	
	// r1,c1 = 루돌프 , r2,c2 = 산타 
	private static void santaMoveToDeer(int r1, int c1, int santaNum) {
		int r2 = santaList.get(santaNum).x;
		int c2 = santaList.get(santaNum).y;
		
		int distance = distCheck(r1,c1,r2,c2); // 지금보다 멀어지면 안돼~
		int[] newPosForSanta = new int[2];
		int direction = -1;
		
		for(int i = 0; i < 4; i++) {
			int rx = r2 + dx[i];
			int ry = c2 + dy[i];
			int tmpdist;
			if(rx <= 0 || ry <= 0 || rx > N || ry > N) continue; // 밖으로는 이동 안
			if(board[rx][ry] > 0) continue; // 다른 산타가 있는 자리도 안돼! 
			
			tmpdist = distCheck(r1,c1,rx,ry);
			
			if(tmpdist < distance) {
				distance = tmpdist;
				newPosForSanta[0] = rx;
				newPosForSanta[1] = ry;
				direction = i;
			}
		}
		if(direction == -1) return;

		board[r2][c2] = 0;
		santaList.get(santaNum).x = newPosForSanta[0];
		santaList.get(santaNum).y = newPosForSanta[1];
		
		// 루돌프가 있다면! 충돌 발생!
		if(board[newPosForSanta[0]][newPosForSanta[1]] == -1) {
			
			if(direction == 0) {
				direction = 2;
			}else if(direction == 1) {
				direction = 3;
			}else if(direction == 2) {
				direction = 0;
			}else if (direction == 3) {
				direction = 1;
			}
			crush(santaNum, direction , D);
			santaList.get(santaNum).wakeUpTime = 2;
		}
		
		
		
	}

	private static int distCheck(int r1, int c1, int r2, int c2) {
		return (int) (Math.pow(r1-r2, 2) +  Math.pow(c1-c2, 2));
	}

	

	
}
