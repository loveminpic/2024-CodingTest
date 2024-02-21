package src.Class;
import java.net.*;
import java.util.Arrays;
import java.io.*;

public class TEST {
	static int[] dice1 = { 1, 2, 3, 4, 5, 6 };
	static int[] dice2 = { 1, 2, 3, 4 };
	static int[] dice3 = { 1, 2 };

	public static void main(String[] args) {

		dfs(1, new int[3], 0);

	}

	private static void dfs(int type, int[] choosed, int cnt) {
		if (cnt == 3) {
			System.out.println(Arrays.toString(choosed));
			return;
		}
		int[] range = null;
		if (type == 1) {
			range = dice1;
		} else if (type == 2) {
			range = dice2;
		} else {
			range = dice3;
		}

		for (int i = 0; i < range.length; i++) {
			choosed[cnt] = range[i];
			dfs(type+1, choosed, cnt + 1);

		}

	}
}
