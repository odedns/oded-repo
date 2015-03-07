package work;
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
import java.awt.Color;

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

}
