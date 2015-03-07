/**
 * Copyright (c) 1998-2001 Oded Nissan.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * 3. The name of the author may not be used to endorse or promote
 * products derived from this software without specific prior written
 * permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS
 * OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED.  IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 *
 */

package onjlib.utils;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Various file utility functions.
 * @author Oded Nissan mailto:odedns@yahoo.com
 * @version 1.00 24/07/2002.
 **/
public abstract class FileUtils {

	/**
	 * finds a file in a list of paths separated by
	 * the path separator char.
	 * @param fname the base name of the file to search for.
	 * @param pathList a list of paths to search.
	 * @return String the full path of the found file or null if it
	 * wasn't found in the path.
	 */
	public static String findInPathList(String fname,String pathList)
	{
		if(pathList== null) {
			return(null);
		}

		String paths[] = StringUtils.toStringArray(pathList,
									File.pathSeparatorChar);
		return(findInPathList(fname,paths));
	}

	
	/**
	 * finds a file in a list of paths separated by
	 * the path separator char.
	 * @param fname the base name of the file to search for.
	 * @param paths  an array of string paths to search.
	 * @return String the full path of the found file or null if it
	 * wasn't found in the path.
	 */
	public static String findInPathList(String fname,String paths[])
	{
		if(paths== null) {
			return(null);
		}

		StringBuffer sb=new StringBuffer();
		File f=null;

		for(int i=0; i<paths.length; ++i) {
			sb.append(paths[i]);
			sb.append(File.separatorChar);
			sb.append(fname);
			f = new File(sb.toString());
			if(f.exists()) {
				break;
			}
			sb.delete(0,sb.length());

		}
		return(sb.length() > 0 ? sb.toString() : null);
	}

	/**
	 * finds a file in the classpath list of directories.
	 * @param fname the base name of the file to search for.
	 * @return String the full path of the found file or null if it
	 * wasn't found in the path.
	 */
	public static String findInClasspath(String fname)
	{
		String classPath= System.getProperty("java.class.path");
		return(findInPathList(fname,classPath));
	}
	/**
	 * finds a file in the OS path variable list of directories.
	 * @param fname the base name of the file to search for.
	 * @return String the full path of the found file or null if it
	 * wasn't found in the path.
	 */
	public static String findInPath(String fname)
	{
		String classPath= System.getProperty("java.library.path");
		return(findInPathList(fname,classPath));
	}

	/**
	 * read a file into a byte array.
	 * @param fname the file name
	 * @return byte[] a byte array with file contents.
	 * @throws IOException in case of error.
	 */
	public static byte[] readToByteArray(String fname) throws IOException
	{
		File f = new File(fname);
		long l = f.length();
		FileInputStream fs = new FileInputStream(fname);			
		FileChannel fc = fs.getChannel();
		ByteBuffer dst = ByteBuffer.allocate((int)l);
		fc.read(dst);		
		byte b[] = dst.array();
		fc.close();
		fs.close();
		return(b);
	}


	/**
	 * main test program.
	 */
	 public static void main(String argv[])
	 {
		String fname = "Cpp.bat";

		Debug.setDebug(true);
		System.out.println("file = " + findInPath(fname));

	 }

}
