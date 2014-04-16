package onjlib.utils.classfile;

import java.io.*;

/**
 * Title:
 * Description:
 * <p> Copyright (c) 2001
 * @author Oded Nissan
 * @version 1.0
 */

public class LineNumberTableAttribute extends Attribute {


	int m_arrLen;
	int m_startPc[];
	int m_lineNum[];

	public LineNumberTableAttribute(int nameIndex, int len)
	{
		super(nameIndex,len);
	}

	public void write(DataOutputStream dos) throws IOException
	{
		super.write(dos);
		dos.writeShort(m_arrLen);
		for(int i=0; i< m_arrLen; ++i) {
			dos.writeShort(m_startPc[i]);
			dos.writeShort(m_lineNum[i]);
		}
	}

	public void read(DataInputStream dis) throws IOException
	{
		m_arrLen = dis.readUnsignedShort();
		m_startPc = new int[m_arrLen];
		m_lineNum = new int[m_arrLen];
		for(int i=0; i < m_arrLen; ++i) {
			m_startPc[i] = dis.readUnsignedShort();
			m_lineNum[i] = dis.readUnsignedShort();
		}



	}

	public String toString()
	{
		return(new String("LineNumberAttribute:" + m_arrLen));
	}
}
