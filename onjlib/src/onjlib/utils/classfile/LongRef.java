package onjlib.utils.classfile;

import java.io.*;

public class LongRef extends ConstantRef {

	long m_value;

	public LongRef(long value)
	{
		m_value = value;
		m_tag = LONG_TAG;
	}

	public LongRef()
	{
		m_tag = LONG_TAG;
	}
	public void read(DataInputStream dis) throws IOException
	{
		m_value = dis.readLong();
	}

	long getValue()
	{
		return(m_value);
	}


	public void write(DataOutputStream dos) throws IOException
	{
		super.write(dos);
		dos.writeLong(m_value);
	}
	public String toString()
	{
		return(new String("LongRef:" + m_value));
	}


}
