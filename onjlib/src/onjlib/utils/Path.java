/**
 * Copyright (c) 1994-1999 Oded Nissan.
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
import java.net.URL;


/**
 * the Path class splits a full path name into
 * its components.
 * The path can be split into directory name and file name.
 * @author Oded Nissan
 */
public class Path {
	String m_path;

	/**
	 * constructor creates a Path Object.
	 * @param path String which contains the fullpath to the
	 * resource.
	 */
	public Path(String path)
	{
		m_path = convertPath(path);
	}

	/**
	 * get the basic resource name in the path.
	 * @return String the basename.
	 */
	public String getBaseName()
	{
		return(getBaseName(m_path));
	}

	/**
	 * splits the directory name from the path.
	 * @return String the directory name.
	 */
	public String getDirName()
	{
		return(getDirName(m_path));
	}

	/**
	 * convert the path string from using \\ to /.
	 * @return String the converted path name.
	 */
	public String convertPath()
	{
		return(convertPath(m_path));
	}

	/**
	 * gets the full path name from the Path object.
	 * @return String the full path.
	 */
	public String getPath()
	{
		return(m_path);
	}
	/**
	 * Returns a file object for the specific path.
	 * @return File
	 */
	public File getFile()
	{
		return(new File(m_path));
	}

	/**
	 * get the basic resource name in the path.
	 * static method.
	 * @return String the basename.
	 */
	public static String getBaseName(String path)
	{
		int index = path.lastIndexOf('/');
		return(path.substring(index+1,path.length()));
	}

	/**
	 * splits the directory name from the path.
	 * static method.
	 * @return String the directory name.
	 */
	public static String getDirName(String path)
	{
		int index = path.lastIndexOf('/');
		return(path.substring(0,index+1));
	}

	/**
	 * convert the path string from using \\ to /.
	 * static method.
	 * @return String the converted path name.
	 */
	public static String convertPath(String path)
	{
		char ch;
		StringBuffer sb = new StringBuffer();

		for(int i=0; i<path.length(); ++i) {
			ch = path.charAt(i);
			if(ch == '\\') {
				sb.append('/');
			} else {
				sb.append(ch);
			}
		}
		return(sb.toString());
	}


	/**
	 * finds the specific file in the system classpath.
	 * useful for finding configuration files.
	 * @param name the file name.
	 * @return InputStream an InputStream to the located file.
	 */
	public static InputStream findFile(String name)
	{
		InputStream is = name.getClass().getResourceAsStream("/" + name);
		if(is == null) {
			ClassLoader cl = Thread.currentThread().getContextClassLoader();
			is = cl.getResourceAsStream("/" + name);			
		}
		return(is);
	}

	/**
	 * finds the specific file in the system classpath.
	 * useful for finding configuration files.
	 * @param name the file name.
	 * @return String the full pathname of the located file.
	 */
	public static String findFileInClassPath(String name)
	{
		String fname = null;
		URL url = name.getClass().getResource("/" + name);
		if(url == null) {
			ClassLoader cl = Thread.currentThread().getContextClassLoader();
			url = cl.getResource("/" + name);			
		}
		if(null != url) {
			fname = url.getPath();
		}
		return(fname);
	}

	public static void main(String argv[])
	{
		System.out.println("Path test ...");
		String path = "\\usr\\local\\bin\\gvim";

		Path p = new Path(path);

		System.out.println("basename = " + p.getBaseName());
		System.out.println("dirname = " + p.getDirName());
		System.out.println("File  = " + p.getFile().toString());
		try {
			File f = new File("c:/dev/src/java/A1.java");
			System.out.println("getAbsolutePath() = " + f.getAbsolutePath());
			System.out.println("getCanonicalPath() = " + f.getCanonicalPath());
			System.out.println("getName() = " + f.getName());
			System.out.println("getParent() = " + f.getParent());
			System.out.println("getPath() = " + f.getPath());
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}

}
