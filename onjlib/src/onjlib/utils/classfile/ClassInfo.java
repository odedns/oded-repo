package onjlib.utils.classfile;

import java.io.*;

public class ClassInfo {
	int m_accessFlags;
	int m_thisRef;
	int m_superRef;
	int m_ifs;

	public ClassInfo(int accessFlags, int thisRef, int superRef,
			 int ifRef)
	{
		m_accessFlags = accessFlags;
		m_thisRef = thisRef;
		m_superRef = superRef;
		m_ifs = ifRef;
	}

	public ClassInfo()
	{
	}
	public void read(DataInputStream dis) throws IOException
	{
		m_accessFlags = dis.readUnsignedShort();
		m_thisRef = dis.readUnsignedShort();
		m_superRef = dis.readUnsignedShort();
		m_ifs = dis.readUnsignedShort();
	}

	public void write(DataOutputStream dos) throws IOException
	{
		dos.writeShort(m_accessFlags);
		dos.writeShort(m_thisRef);
		dos.writeShort(m_superRef);
		dos.writeShort(m_ifs);

	}

	public String toString()
	{
		return(new String("ClassInfo: " + m_accessFlags + ":" + UTF8Dict.getString(m_thisRef) + ":" +
			UTF8Dict.getString(m_superRef) + ":" + m_ifs));

	}
}
