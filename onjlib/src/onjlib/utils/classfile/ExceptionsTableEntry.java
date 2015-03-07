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

public class ExceptionsTableEntry {

	int m_start;
	int m_end;
	int m_handler;
	int m_catchType;
	public ExceptionsTableEntry() {
	}
	public void read(DataInputStream dis) throws IOException
	{
		m_start = dis.readUnsignedShort();
		m_end = dis.readUnsignedShort();
		m_handler = dis.readUnsignedShort();
		m_catchType = dis.readUnsignedShort();
	}

	public void write(DataOutputStream dos) throws IOException
	{
		dos.writeShort(m_start);
		dos.writeShort(m_end);
		dos.writeShort(m_handler);
		dos.writeShort(m_catchType);

	}


}