package onjlib.utils.classfile;

import java.io.*;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public class SourceFileAttribute extends Attribute  {

	int m_valIndex;

	public SourceFileAttribute(int nameIndex, int len)
	{
		super(nameIndex,len);
	}

	public void write(DataOutputStream dos) throws IOException
	{
		super.write(dos);
		dos.writeShort(m_valIndex);
	}

	public void read(DataInputStream dis) throws IOException
	{
		m_valIndex = dis.readUnsignedShort();
	}

	public String toString()
	{
		return(new String("SourceFileAttribute:" + m_valIndex));
	}

}