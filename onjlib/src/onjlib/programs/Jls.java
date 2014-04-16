package onjlib.programs;

import onjlib.utils.Queue;
import onjlib.utils.StringUtils;

import java.io.File;
import java.util.Date;

public class Jls {
	static boolean m_longListing = false;
	static boolean m_recurse = false;

	Queue m_queue = null;
	public Jls(boolean longListing, boolean recurse)
	{
		m_longListing = longListing;
		m_recurse = recurse;
		m_queue = new Queue();
	}

	public String getBaseName(String path)
	{
		int index = path.lastIndexOf('/');
		return(path.substring(index+1,path.length()));
	}
	public void printFileInfo(File f)
	{
		if(m_longListing) {
			printLongListing(f);
		} else {
			String name = getBaseName(f.getName());
			System.out.print(StringUtils.padString(name,
						20));
		}

	}

	public void printLongListing(File f)
	{
		String s = null;
		String name = null;

		try {
			name = getBaseName(f.getName());
		}catch(Exception e) {
			e.printStackTrace();
		}
		StringBuffer sb = new StringBuffer(25);

		if(f.canRead()) {
			sb.append("r");
		} else {
			sb.append("-");
		}

		if(f.canWrite()) {
			sb.append("w");
		} else {
			sb.append("-");
		}
		if(f.isDirectory()) {
			sb.append("d");
		} else {
			sb.append("-");
		}

		sb.append("\t" + f.length());

		/*
		 * print timestamp
		 */
		long l = f.lastModified();
		Date d = new Date(l);
		sb.append("\t" + d.toString());


		if(f.isDirectory()) {
			s = name.concat("/");
		} else {
			s = name;
		}
		sb.append("\t" + s);

		System.out.println(sb);
	}

	public void listFiles(File f)
	{
		String list[] = null;

		if(f.isDirectory()) {
			System.out.println(f.getName() + ":");
			list = f.list();
			for(int i=0; i<list.length; ++i) {
				File f1 = new File(f.getName()+ '/' +list[i]);
				if(m_recurse && f1.isDirectory()) {
					m_queue.push(f1.getName());
					continue;
				}
				printFileInfo(f1);
			}
		} else {
			printFileInfo(f);
		}
		while(!m_queue.empty()) {
			String name = (String)m_queue.pop();
			try {
				File f2 = new File(name);
				listFiles(f2);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}

	}

	static String convertPathName(String path)
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



	public static void main(String argv[])
	{
		File dir =null;

		String s = System.getProperty("jls.longListing","false");
		Boolean b = new Boolean(s);
		String s1 = System.getProperty("jls.recurse","false");
		Boolean b1 = new Boolean(s1);

		Jls ls = new Jls(b.booleanValue(),b1.booleanValue());
		try {
			for(int i=0; i<argv.length; ++i) {
				dir = new File(convertPathName(argv[0]));
				ls.listFiles(dir);
			}
			if(argv.length == 0) {
				dir = new File(".");
				ls.listFiles(dir);
			}


		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.print('\n');

	}

}
