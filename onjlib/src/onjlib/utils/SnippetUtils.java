/**
 * Date: 01/02/2007
 * File: SnippetUtils.java
 * Package: onjlib.utils
 */
package onjlib.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import com.sun.tools.javac.Main;



/**
 * @author Oded Nissan
 * This class generates compiles and runs
 * Java code snippets.
 *
 */
public class SnippetUtils {

	private static String dir = System.getProperty("user.home"); 
	private static final String LOG_FILE_NAME = "javac.log";
	/**
	 * compile a code snippet.
	 * @param name the class name.
	 * @param codeSnippet a string containing the code snippet.
	 * @return Class the class for the generated code.
	 * @throws Exception in case of error.
	 */
	public static Class compileSnippet(String name,String codeSnippet) throws Exception
	{
		String fname = dir + File.separatorChar + name + ".java";
		/*
		 * create a java source file.
		 */
		PrintWriter out = new PrintWriter(new FileOutputStream(fname));
		out.println("public class " + name + " {");
		out.println("public void execute() {");
		out.println(codeSnippet);
		out.println("}}");
	    out.flush();
	    out.close();
	    /*
	     * compile the java source file.
	     */
		PrintWriter dos = new PrintWriter(new File(dir + File.separatorChar + LOG_FILE_NAME));
		String argv[] = new String[] { fname };
		int stat = Main.compile(argv,dos);
		if(stat != 0) {
			throw new Exception("Compile Error");
		}

		/*
		 * now load the class.
		 * and invoke it main method.
		 */
		
		FileSystemClassLoader fcl = new FileSystemClassLoader(dir);
		Class c = fcl.findClass(name);
		return(c);
	}
	
	
	/**
	 * runs the code snippet.
	 * @param name the class name.
	 * @param codeSnippet a string containing the code snippet.
	 * @return Class the class for the generated code.
	 * @throws Exception in case of error.
	 */
	public static void runSnippet(String name, String codeSnippet) throws Exception
	{
		Class c = compileSnippet(name, codeSnippet);
		Object o = c.newInstance();
		Method m = c.getMethod("execute", null);
		m.invoke(o, null);
	}
	
}
