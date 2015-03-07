package onjlib.utils.classfile;

import java.io.*;

public class StringRef extends ConstantRef {

	short m_index;
	String m_text;


	public StringRef()
	{
		m_tag = STRING_TAG;
	}
	public void read(DataInputStream dis) throws IOException
	{
		m_index = dis.readShort();
	}

	public short getIndex()
	{
		return(m_index);
	}

	public String getText()
	{
		return( UTF8Dict.getString(m_index));
	}


	public void write(DataOutputStream dos) throws IOException
	{
		super.write(dos);
		dos.writeShort(m_index);
	}
	public String toString()
	{
		return(new String("StringRef:" + m_index));
	}



}
