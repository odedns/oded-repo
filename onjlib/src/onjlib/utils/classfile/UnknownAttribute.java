package onjlib.utils.classfile;

import java.io.*;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Oded Nissan
 * @version 1.0
 */

public class UnknownAttribute extends Attribute {

	byte m_val[];
	public UnknownAttribute(int nameIndex, int len)
	{
		super(nameIndex,len);
		m_val = new byte[len];
	}

	public void write(DataOutputStream dos) throws IOException
	{
		super.write(dos);
		dos.write(m_val);

	}
	public void read(DataInputStream dis) throws java.io.IOException
	{
		dis.read(m_val);
	}

	public String toString()
	{
		return(new String("UnknownAttribute :" + m_length));
	}
}
