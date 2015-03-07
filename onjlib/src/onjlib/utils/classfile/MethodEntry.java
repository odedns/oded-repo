package onjlib.utils.classfile;

import java.io.*;

/**
 * Title:	MethodEntry.java
 * Description: Class for methods.
 * Copyright:    Copyright (c) 2002
 * Company:
 * @author Oded Nissan
 * @version 1.0
 */

public class MethodEntry {

	int m_accessFlags;
	int m_nameInx;
	int m_descInx;
	int m_numAttrs;
	Attribute m_attrs[];

	public MethodEntry(int accessFlags, int nameInx, int descInx, Attribute attrs[])
	{
		m_accessFlags = accessFlags;
		m_nameInx = nameInx;
		m_descInx = descInx;
		m_attrs = attrs;
		m_numAttrs = attrs.length;
	}
	public MethodEntry()
	{
		m_accessFlags = 0;
		m_attrs = null;
	}

	public Attribute[] getAttributes()
	{
		return(m_attrs);
	}

	public void setAttributes(Attribute attrs[])
	{
		m_attrs = attrs;
		m_numAttrs = attrs.length;
	}

	public void read(DataInputStream dis) throws IOException
	{
		m_accessFlags = dis.readUnsignedShort();
		m_nameInx = dis.readUnsignedShort();
		m_descInx = dis.readUnsignedShort();
		m_numAttrs = dis.readUnsignedShort();
		m_attrs = new Attribute[m_numAttrs];
		for(int i=0; i< m_numAttrs; ++i) {
			m_attrs[i] = AttributeReader.getAttribute(dis);
			m_attrs[i].read(dis);
		}
	}
	public void write(DataOutputStream dos) throws IOException
	{
		dos.writeShort(m_accessFlags);
		dos.writeShort(m_nameInx);
		dos.writeShort(m_descInx);
		dos.writeShort(m_numAttrs);
		for(int i=0; i< m_numAttrs; ++i) {
			m_attrs[i].write(dos);
		}
	}
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("MethodEntry:" + m_accessFlags + ":" + m_nameInx + "(" +
				   UTF8Dict.getString(m_nameInx) + ")" + ":" +
				  m_descInx + ":" + m_numAttrs);
		for(int i=0; i< m_numAttrs; ++i) {
			sb.append("\n" + m_attrs[i].toString());
		}
		return(sb.toString());
	}
}
