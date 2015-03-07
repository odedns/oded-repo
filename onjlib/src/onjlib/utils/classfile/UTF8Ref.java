package onjlib.utils.classfile;

import java.io.*;

public class UTF8Ref extends ConstantRef {

	short 	m_length;
	String  m_text;

	public UTF8Ref()
	{
		m_tag = UTF8_TAG;
	}
	public UTF8Ref(String text)
	{
		m_text = text;
		m_length = (short) text.length();
		m_tag = UTF8_TAG;
	}
	public void read(DataInputStream dis) throws IOException
	{
		m_length = dis.readShort();
		byte b[] = new byte[m_length];
		dis.read(b);
		m_text = new String(b);
	}

	public short getLength()
	{
		return(m_length);
	}

	public String getText()
	{
		return(m_text);
	}

	public void write(DataOutputStream dos) throws IOException
	{
		super.write(dos);
		dos.writeShort(m_length);
		dos.writeBytes(m_text);
	}

	public String toString()
	{
		return(new String("UTF8:" + m_length + ":" + m_text));
	}

}
