package aps.Gold.G2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class G2_4195 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int N, F;
	static int cnt = 0 ;
	static List<String> names = new ArrayList<>();
	static int[] numbers = new int[100000];
	static int[] networkSize = new int[100000]; // 네트워크 크기를 저장하는 배열

	    public static void main(String[] args) throws NumberFormatException, IOException {
	        N = Integer.parseInt(br.readLine());
	        for (int t = 0; t < N; t++) {
	            F = Integer.parseInt(br.readLine());
	            names.clear();
	            Arrays.fill(numbers, -1);
	            cnt = 0;

	            for (int i = 0; i < F; i++) {
	                tokens = new StringTokenizer(br.readLine());
	                String a = tokens.nextToken();
	                String b = tokens.nextToken();
	                int a_num;
	                int b_num;

	                if (!names.contains(a)) {
	                    names.add(a);
	                    a_num = cnt;
	                    numbers[cnt] = cnt; // a_num이 자신을 가리키게 함
	                    networkSize[cnt] = 1; // 새로운 네트워크의 크기는 1
	                    cnt++;
	                } else {
	                    a_num = names.indexOf(a);
	                }

	                if (!names.contains(b)) {
	                    names.add(b);
	                    b_num = cnt;
	                    numbers[cnt] = cnt; // b_num이 자신을 가리키게 함
	                    networkSize[cnt] = 1; // 새로운 네트워크의 크기는 1
	                    cnt++;
	                } else {
	                    b_num = names.indexOf(b);
	                }

	                System.out.println(union(a_num, b_num));
	            }
	        }
	    }

	    private static int findSet(int a) {
	        if (numbers[a] != a) {
	            numbers[a] = findSet(numbers[a]); // 경로 압축
	        }
	        return numbers[a];
	    }

	    private static int union(int a, int b) {
	        int rootA = findSet(a);
	        int rootB = findSet(b);

	        if (rootA != rootB) {
	            numbers[rootB] = rootA; // rootB의 부모를 rootA로 설정
	            networkSize[rootA] += networkSize[rootB]; // 네트워크 크기 업데이트
	        }
	        return networkSize[rootA]; // 합쳐진 네트워크의 크기 반환
	    }
	}