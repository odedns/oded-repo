package onjlib.utils.classfile;

import java.io.*;

public class FieldRef extends ConstantRef {

	short m_index;
	short m_nameAndType;


	public FieldRef()
	{
		m_tag = FIELDREF_TAG;
	}
	public FieldRef(short index, short nameAndType)
	{
		m_index = index;
		m_nameAndType = nameAndType;
		m_tag = FIELDREF_TAG;
	}

	public void read(DataInputStream dis) throws IOException
	{
		m_index = dis.readShort();
		m_nameAndType = dis.readShort();
	}

	short getNameAndType()
	{
		return(m_nameAndType);
	}

	short getIndex()
	{
		return(m_index);
	}

	public void write(DataOutputStream dos) throws IOException
	{
		super.write(dos);
		dos.writeShort(m_index);
		dos.writeShort(m_nameAndType);
	}

	public String toString()
	{
		return(new String("FIELDREF:" + m_index + ":" + m_nameAndType));
	}

}
