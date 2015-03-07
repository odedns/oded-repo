package onjlib.utils.classfile;

import java.io.*;

public class IntegerRef extends ConstantRef {

	int m_value;


	public IntegerRef(int value)
	{
		m_value = value;
		m_tag = INTEGER_TAG;
	}
	public IntegerRef()
	{
		m_tag = INTEGER_TAG;
	}
	public void read(DataInputStream dis) throws IOException
	{
		m_value = dis.readInt();
	}

	int getValue()
	{
		return(m_value);
	}


	public void write(DataOutputStream dos) throws IOException
	{
		super.write(dos);
		dos.writeInt(m_value);
	}
	public String toString()
	{
		return(new String("IntegerRef:" + m_value));
	}

}
