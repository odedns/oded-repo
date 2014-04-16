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

public class CodeAttribute extends Attribute {

	int m_maxStack;
	int m_maxLocals;
	int m_codeLen;
	byte[] m_code;
	int m_numExceptions;
	ExceptionsTableEntry m_exceptions[];
	int m_numAttrs;
	Attribute m_attrs[];

	public CodeAttribute(int nameIndex, int len)
	{
		super(nameIndex,len);
	}

	public void write(DataOutputStream dos) throws IOException
	{
		super.write(dos);
		dos.writeShort(m_maxStack);
		dos.writeShort(m_maxLocals);
		dos.writeInt(m_codeLen);
		dos.write(m_code,0,m_codeLen);
		dos.writeShort(m_numExceptions);
		for(int j=0; j< m_numExceptions; ++j) {
			m_exceptions[j].write(dos);
		}
		dos.writeShort(m_numAttrs);
		for(int i=0; i< m_numAttrs; ++i) {
			m_attrs[i].write(dos);
		}


	}

	public void read(DataInputStream dis) throws IOException
	{
		m_maxStack = dis.readUnsignedShort();
		m_maxLocals = dis.readUnsignedShort();
		m_codeLen = dis.readInt();
		m_code = new byte[m_codeLen];
		dis.read(m_code,0,m_codeLen);
		m_numExceptions = dis.readUnsignedShort();
		m_exceptions = new ExceptionsTableEntry[m_numExceptions];
		for(int j=0; j< m_numExceptions; ++j) {
			m_exceptions[j] = new ExceptionsTableEntry();
			m_exceptions[j].read(dis);
		}
		m_numAttrs = dis.readUnsignedShort();
		m_attrs = new Attribute[m_numAttrs];
		for(int i=0; i< m_numAttrs; ++i) {
			m_attrs[i] = AttributeReader.getAttribute(dis);
			m_attrs[i].read(dis);
		}


	}

	public String toString()
	{
		return(new String("CodeAttribute:" + m_maxStack + ":" + m_maxLocals + ":" + m_codeLen));
	}
}