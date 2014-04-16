package onjlib.utils.classfile;

import java.io.*;

public class FloatRef extends ConstantRef {

	float m_value;


	public FloatRef()
	{
		m_tag = FLOAT_TAG;
	}
	public void read(DataInputStream dis) throws IOException
	{
		m_value = dis.readFloat();
	}

	float getValue()
	{
		return(m_value);
	}


	public void write(DataOutputStream dos) throws IOException
	{
		super.write(dos);
		dos.writeFloat(m_value);
	}

	public String toString()
	{
		return(new String("FloatRef:" + m_value));
	}

}
