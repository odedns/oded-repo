package onjlib.utils;

import java.io.*;

public class GWrappingException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2638581919493453774L;
	private Throwable m_th;

	public GWrappingException(String s)
	{
		super(s);
		m_th = this;

	}

	public GWrappingException(String s, Throwable th)
	{
		super(s);
		m_th = th;
	}

	public void printStackTrace()
	{
		m_th.printStackTrace();
	}

	public void printStackTrace(PrintStream ps)
	{
		m_th.printStackTrace(ps);
	}
	public void printStackTrace(PrintWriter pw)
	{
		m_th.printStackTrace(pw);
	}


}
