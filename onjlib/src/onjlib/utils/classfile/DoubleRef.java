package onjlib.utils.classfile;

import java.io.*;

public class DoubleRef extends ConstantRef {

	double m_value;


	public DoubleRef()
	{
		m_tag= DOUBLE_TAG;
	}
	public void read(DataInputStream dis) throws IOException
	{
		m_value = dis.readDouble();
	}

	double getValue()
	{
		return(m_value);
	}


	public void write(DataOutputStream dos) throws IOException
	{
		super.write(dos);
		dos.writeDouble(m_value);

	}

	public String toString()
	{
		return(new String("DoubleRef:" + m_value));
	}

}
