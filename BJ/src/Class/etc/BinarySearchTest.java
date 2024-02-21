package src.Class.etc;

import java.util.Arrays;

public class BinarySearchTest {
	static char[] chars ="3401".toCharArray();
	public static void main(String[] args) {
		Arrays.sort(chars);
		char target = '1';
		System.out.println(binarySearch(target));
		System.out.println(Arrays.binarySearch(chars, target));
	}
	private static int binarySearch(char c, int start, int end) {
		if(start > end) {
			return -1;
		}
		else {
			int mid = (start+end) / 2;
			if(chars[mid] == c) {
				return mid;
			}else if (chars[mid] < c) {
				return binarySearch(c, mid+1, end);
			}else {
				return binarySearch(c, start, mid-1);
			}
		}
	}
	private static int binarySearch(char c) {
		int start= 0;
		int end = chars.length-1;
		while(start<=end) {
			int mid = (start +end)/2;
			if(chars[mid]== c) return mid;
			else if(chars[mid] < c) {
				start = mid +1;
			}else {
				end = mid -1;
			}
		}
		return -1;
	}
}
