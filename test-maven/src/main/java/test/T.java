/**
 * 
 */
package test;

class A {
	String data;
	 A()
	{
		 System.out.println("A ctor");
		 foo();
	}
	void foo() {
		System.out.println("foo in a ");
	}
}


class B extends A {
	
	B(){
		super();
		data = "my data";
		System.out.println("B Ctor");
	}
	@Override
	void foo() {
		System.out.println("foo in b data =" + data);
	}
	
}

/**
 * @author family
 *
 */
public class T {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		B b = new B();

	}

}
