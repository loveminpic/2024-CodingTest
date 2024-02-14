
public class TEST {
	 static int f(int n){

		 

		    if (n <= 0) return 0;

		    if (n <= 3) return n;

		 

		    return f(n - 2) + f(n - 3);

		    }

		 

	public static void main(String[] args) {

	    int ans = f(8);

	    System.out.printf("%d\n", ans);


	}

}
