package src.Class.combi;

import java.util.Arrays;

public class DuplicateCombination {

	static char[] src = {'a','b','c','d'};
	static char[] choosed;
	public static void main(String[] args) {
		System.out.println("중복조합");
		choosed = new char[src.length];
		makeCombination(0,0);

	}
	
	static void makeCombination(int nthChoice, int start) {
		if(nthChoice == src.length) {
			System.out.println(Arrays.toString(choosed));
			return;
		}
		for(int i= start; i <src.length; i++) {
			choosed[nthChoice] = src[i];
			makeCombination(nthChoice +1, i);
		}
	}
}