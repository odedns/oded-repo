package onjlib.utils.classfile;

import java.io.*;

public class ClassFileHeader {
	byte[] m_magic;
	short m_minVer;
	short m_majVer;

	public ClassFileHeader(short minVer, short majVer)
	{
		m_magic = new byte[4];
		m_magic[0] = (byte) 0xCA;
		m_magic[1] = (byte) 0xFE;
		m_magic[2] = (byte) 0xBA;
		m_magic[3] = (byte) 0xBE;

		m_minVer = minVer;
		m_majVer = majVer;
	}

	public ClassFileHeader()
	{
		m_magic = new byte[4];
	}
	public void write(DataOutputStream dos) throws IOException
	{
		dos.write(m_magic,0,4);
		dos.writeShort(m_majVer);
		dos.writeShort(m_minVer);

		dos.flush();
	}

	public void read(DataInputStream dis) throws IOException
	{
		dis.read(m_magic);
		m_majVer = dis.readShort();
		m_minVer = dis.readShort();
	}

	public String toString()
	{
		return(new String(m_majVer + "-" + m_minVer));
	}


}
