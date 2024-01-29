package src.Bronze.B5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B5_1000 {
	static int a, b;
	static StringTokenizer token;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		token = new StringTokenizer(br.readLine());
		a = Integer.parseInt(token.nextToken());
		b = Integer.parseInt(token.nextToken());
		System.out.println(a+b);
		
	}
}