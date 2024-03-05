package SwExpertAcademy.swtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 무선충전  {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static StringTokenizer tokens1;
	static StringTokenizer tokens2;
	static int TC; // 테케 갯수
	static int T; //  총 초, 즉 a와 b의 이동 거리
	static int BC_count;  // == A,  배터리 갯수 
	static int[] a_load, b_load;
	static Battery[] list_bc;
	
	static ArrayList<Integer> board[][];
	
	static int[] dx = {0, -1, 0, 1, 0} ; // 정 상 우 하 좌 왼상 오상 외하 오하
	static int[] dy = {0, 0, 1, 0, -1};
	
	static class Battery {
		int x;
		int y;
		int range;
		int power;
		
		public Battery(int x, int y, int range, int power) {
			this.x = x;
			this.y = y;
			this.range = range;
			this.power = power;
		}
		
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		TC = Integer.parseInt(br.readLine());
		
		for(int i = 1; i <=TC ; i++) {
			tokens = new StringTokenizer(br.readLine());
			T = Integer.parseInt(tokens.nextToken());
			BC_count = Integer.parseInt(tokens.nextToken());
			
			a_load = new int[T+1];
			b_load = new int[T+1];
			
			tokens1 = new StringTokenizer(br.readLine());
			tokens2 = new StringTokenizer(br.readLine());
			a_load[0] = 0;
			b_load[0] = 0;
			for(int j = 1; j <= T; j++) {
				a_load[j] = Integer.parseInt(tokens1.nextToken());
				b_load[j] = Integer.parseInt(tokens2.nextToken());
			}
			
			list_bc = new Battery[BC_count+1];
			
			for(int j = 1; j<= BC_count; j++) {
				tokens = new StringTokenizer(br.readLine());
				int x =  Integer.parseInt(tokens.nextToken());
				int y =  Integer.parseInt(tokens.nextToken());
				int range =  Integer.parseInt(tokens.nextToken());
				int power =  Integer.parseInt(tokens.nextToken());
				Battery tmp = new Battery(y, x, range, power);
				list_bc[j] = tmp;
			}
			
			board = new ArrayList[11][11];
			
			for (int r = 0; r < board.length; r++) {
	            for (int c = 0; c < board[r].length; c++) {
	            	board[r][c] = new ArrayList<Integer>(); // 각 원소를 ArrayList로 초기화
	            }
	        }
			
			for(int j = 1; j <= BC_count; j++) {
				// board에 각 배터리 범위에 유효한지 추가 
				checkingBattery(j,list_bc[j].x, list_bc[j].y, list_bc[j].range, list_bc[j].power);
			}
			
			
			int curr_ax = 1;
			int curr_ay = 1;
			
			int curr_bx = 10;
			int curr_by = 10;
			
			int sum = 0; 
			
			for(int j = 0; j <= T; j++) {
				// 두 사람의 위치를 파악하고, 사람이 같은 곳에 있는지 없는지 확인
				int ad_index = a_load[j];
				int bd_index = b_load[j];
				
				curr_ax += dx[ad_index];
				curr_ay += dy[ad_index];
				
				curr_bx += dx[bd_index];
				curr_by += dy[bd_index];
				
				if(curr_ax == curr_bx && curr_ay == curr_by) {
					if(board[curr_ax][curr_ay].size() == 1) {
						int num =  board[curr_ax][curr_ay].get(0);
						sum += list_bc[num].power;
					}else {
						// 같은 위치인데, 배터리가 2개 이상일때, 제일큰거, 그 다음 큰거 찾기 
						int num =  board[curr_ax][curr_ay].get(0);
						int num2 =  board[curr_ax][curr_ay].get(1);
						int first, second;
						
						if(list_bc[num].power > list_bc[num2].power) {
							first =  list_bc[num].power;
							second = list_bc[num2].power;
							
						}else {
							second =  list_bc[num].power;
							first = list_bc[num2].power;
						}
						
						for(int h = 2; h < board[curr_ax][curr_ay].size(); h++) {
							if(board[curr_ax][curr_ay].get(h) > first) {
								int tmp = first;
								first = list_bc[board[curr_ax][curr_ay].get(h)].power;
								second = tmp;
							}else {
								if(board[curr_ax][curr_ay].get(h) > second) {
									second = board[curr_ax][curr_ay].get(h);
								}
							}
							
						}
						
						sum += first;
						sum += second;
					}
				}else {
					// 다른 위치에서 첫번째 큰거 두번째 큰거 찾기 
					
					int a_num1;
					int a_num2; 
					int a_first = 0;
					int a_second = 0;
					int largest_a = 0; 
					
					if (board[curr_ax][curr_ay].size()  == 0) {
					}
					else if(board[curr_ax][curr_ay].size() == 1) {
						int num =  board[curr_ax][curr_ay].get(0);
						a_first = list_bc[num].power;
						largest_a = num;
					} else {
						a_num1 = board[curr_ax][curr_ay].get(0);
						a_num2 = board[curr_ax][curr_ay].get(1);
						
						if(list_bc[a_num1].power > list_bc[a_num2].power) {
							a_first =  list_bc[a_num1].power;
							a_second = list_bc[a_num2].power;
							largest_a = a_num1;
							
						}else {
							a_second =  list_bc[a_num1].power;
							a_first = list_bc[a_num2].power;
							largest_a = a_num2;
						}
						
						
						for(int h = 2; h < board[curr_ax][curr_ay].size(); h++) {
							if(board[curr_ax][curr_ay].get(h) >= a_first) {
								int tmp = a_first;
								a_first = list_bc[board[curr_ax][curr_ay].get(h)].power;
								a_second = tmp;
								largest_a = board[curr_ax][curr_ay].get(h);
							}else {
								if( list_bc[board[curr_ax][curr_ay].get(h)].power > a_second) {
									a_second =  list_bc[board[curr_ax][curr_ay].get(h)].power;
								
								}
							}
							
						}
						
						
					}
					
					int b_num1;
					int b_num2;
					int b_first = 0 ;
					int b_second = 0;
					int largest_b = 0; 
					if ( board[curr_bx][curr_by].size() == 0) {
					
					}
					else if(board[curr_bx][curr_by].size() == 1) {
						int num =  board[curr_bx][curr_by].get(0);
						b_first = list_bc[num].power;
						largest_b = num;
					}else {
						b_num1 = board[curr_bx][curr_by].get(0);
						b_num2 = board[curr_bx][curr_by].get(1);
						
						if(list_bc[b_num1].power > list_bc[b_num2].power) {
							b_first =  list_bc[b_num1].power;
							b_second = list_bc[b_num2].power;
							largest_b = b_num1;
							
						}else {
							b_second =  list_bc[b_num1].power;
							b_first = list_bc[b_num2].power;
							largest_b = b_num2;
						}
						
						
						for(int h = 2; h < board[curr_bx][curr_by].size(); h++) {
							if(board[curr_bx][curr_by].get(h) >= b_first) {
								int tmp = b_first;
								b_first = list_bc[board[curr_bx][curr_by].get(h)].power;
								b_second = tmp;
								largest_b = board[curr_bx][curr_by].get(h);
							}else {
								if( list_bc[board[curr_bx][curr_by].get(h)].power > b_second) {
									b_second =  list_bc[board[curr_bx][curr_by].get(h)].power;
								
								}
							}
							
						}
					}
					
					if(largest_a != 0 && largest_b != 0 && largest_a == largest_b) {
						if(a_second >= b_second && a_first < b_first + a_second) {
							sum += a_second;
							sum += b_first;
						}else if(a_second < b_second && a_first < a_first + b_second ) {
							sum += b_second;
							sum += a_first;
						}else {
							sum += a_first;
						}
						
					}else {
						sum += a_first;
						sum += b_first;
					}
					
				}// end
			
			
			}

			sb.append("#").append(i+ " ").append(sum).append("\n");
		}
		System.out.println(sb);
	}


	private static void checkingBattery(int number_battery, int x, int y, int range, int power) {

		for(int i = 1; i < board.length; i++) {
			for(int j = 1; j < board.length; j++) {
				if(Math.abs(x-i) + Math.abs(y-j) <= range) {
					board[i][j].add(number_battery);
				}
			}
		}
		
		
	}

}
