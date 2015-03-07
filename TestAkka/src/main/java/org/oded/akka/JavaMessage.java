package org.oded.akka;



public class JavaMessage{
	public int num;
	public final String msg;
	// Valuestyle
	
	public JavaMessage(int num,String msg) {
	 this.msg = msg;
	 this.num = num;
	}
	// If you don't do this it's going to make things a real pain
	public boolean equals(Object that) {
	if (that instanceof JavaMessage)
	return ((JavaMessage)that).msg.equals(this.msg);
	else
	return false;
	}
	// Ditto here
	public int hashCode() {
	return this.msg.hashCode();
	}
	// Makes diagnostics and the like a lot nicer
	public String toString() {
		return "JavaMessage(" + this.msg + " num=" + this.num + ")";
	}
}
