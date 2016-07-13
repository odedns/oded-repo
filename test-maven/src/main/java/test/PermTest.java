package test;

public class PermTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		permutation("abc");
	}

	public static void permutation(String str) { 
	    permutation("", str); 
	}

	private static void permutation(String prefix, String str) {
		System.out.println("permutation: prefix=" + prefix + " str=" + str);
	    int n = str.length();
	    if (n == 0) System.out.println(prefix);
	    else {
	        for (int i = 0; i < n; i++) {
	            permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n));
	        }
	    }
	}
}
