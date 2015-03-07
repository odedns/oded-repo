/**
 * 
 */
package test.work;

/**
 * @author I070659
 *
 */




public class T {
	static int a = 0;
	
	static void printnum(int num)
	{
		int n = num;
		
		while(n % 10 != 0) {
			System.out.print(n % 10);
			n /= 10;
		}
		
		System.out.println('\n');
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		printnum(1234);

	}

}
