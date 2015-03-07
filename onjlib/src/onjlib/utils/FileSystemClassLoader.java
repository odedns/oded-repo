/**
 * Date: 04/02/2007
 * File: FileSystemClassLoader.java
 * Package: onjlib.utils
 */
package onjlib.utils;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Oded Nissan
 *
 */
public class FileSystemClassLoader extends ClassLoader {

	private String paths[];
	
	/**
	 * constructor create a FileSystemClassLoader
	 * @param path a string of paths seperated file the
	 * path separator. 
	 */
	public FileSystemClassLoader(String path)
	{
		paths = StringUtils.toStringArray(path, File.pathSeparatorChar);
	}
	
	/**
	 * constructor create a FileSystemClassLoader
	 * @param paths an array of path strings to search for 
	 * classes.
	 */
	public FileSystemClassLoader(String paths[])
	{
		this.paths = paths;
	}
	
	@Override
	public Class<?> findClass(String name) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		Class c = null;
		String classFilename = name + ".class";
		String fname = FileUtils.findInPathList(classFilename, this.paths);
		if(null != fname) {
			try {
				byte b[] = FileUtils.readToByteArray(fname);
				c = defineClass(name, b, 0, b.length);
			} catch(IOException ie) {
				throw new ClassNotFoundException("IOException", ie);
			}
		} else {
			c = super.loadClass(name);
		}
		return(c);
	}

	@Override
	protected URL findResource(String name) {
		// TODO Auto-generated method stub
		URL url = null;
		String classFilename = name + ".class";
		String fname = FileUtils.findInPathList(classFilename, this.paths);
		if(fname == null) {
			url = super.findResource(name);
		} else {
			File f = new File(fname);
			try {
				url = f.toURL();
			} catch (MalformedURLException me) {
				me.printStackTrace();
				url = null;
			}
		}
		return(url);
	}

}
