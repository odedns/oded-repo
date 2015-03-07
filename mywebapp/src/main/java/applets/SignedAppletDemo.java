package applets;
/*
 * File: @(#)SignedAppletDemo.java	1.1
 * Comment:	Signed Applet Demo
 *
 * @(#)author:  Satya Dodda
 * @(#)version: 1.1
 * @(#)date:    98/09/11
 */


import java.applet.Applet;
import java.awt.Graphics;
import java.io.*;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.awt.Color;



class Mythread extends Thread {
	String s;
	Mythread(String s)
	{
		this.s = s;
		
	}
	public void run()
	{
		System.out.println("running ");
		boolean existFolder = (Boolean) AccessController.doPrivileged(new PrivilegedAction() {
			public Object run()
			{
				final File f = new File(s);
				boolean b = (f.exists() && (f.isDirectory()));
				System.out.println("End FolderExists =" + b);
				return(b);
				
			}
		});
		
		
	}
}



/**
 *
 * A simple Signed Applet Demo
 * 
 */

public class SignedAppletDemo extends Applet {

    public String test() {

        setBackground(Color.white);

	String fileName = System.getProperty("user.home") +
				  System.getProperty("file.separator") +
				  "demo.ini";
	String msg  = "This message was written by a signed applet!!!\n";
	String s ;
				  
	try {

	    FileWriter fos = new FileWriter(fileName);
	    fos.write(msg, 0, msg.length());
	    fos.close();
	    s = new String("Successfully created file :" + fileName);

	} catch (Exception e) {
	    System.out.println("Exception e = " + e);
	    e.printStackTrace();
	    s = new String("Unable to create file :  " + fileName);
	}
	return s;

    }	

    public void paint(Graphics g) {

        g.setColor(Color.blue);
        g.drawString("Signed Applet Demo", 120, 50);
        g.setColor(Color.magenta);
        g.drawString(test(), 50, 100);

    }
	/**
	 * verify if a file exist
	 * 
	 * @param fileName
	 * @return
	 */
public synchronized boolean FileExists(final String fileName) {
		
		
		boolean existFile = (Boolean) AccessController.doPrivileged(new PrivilegedAction() {

			public Object run()
			{
				// TODO Auto-generated method stub
				final File f = new File(fileName);
				boolean b = (f.exists() && (f.isFile()));
				System.out.println("End FileExists =" + b);
				return (b);
			}
			
			
		});
		return existFile;
	}
	

	/**
	 * verify if a folder exist
	 * 
	 * @param folderName
	 * @return
	 */
	public synchronized boolean FolderExists(final String folderName) {
		
		boolean existFolder = (Boolean) AccessController.doPrivileged(new PrivilegedAction() {

			public Object run()
			{
				final File f = new File(folderName);
				boolean b = (f.exists() && (f.isDirectory()));
				System.out.println("End FolderExists =" + b);
				return(b);
				
			}
		});
		Mythread th = new Mythread(folderName);
		th.start();
		return existFolder;
	}

}
