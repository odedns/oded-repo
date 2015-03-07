


public class JExt {
	
	public native void print(String s);

	static
    {
      System.loadLibrary("jext");
    } 



	public static void main(String argv[])
	{

		System.out.println("in JExt.main()");

		JExt jext = new JExt();
		jext.print("Some fucking string");
	}
	
}
