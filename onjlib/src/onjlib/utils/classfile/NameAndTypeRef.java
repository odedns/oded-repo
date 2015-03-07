package onjlib.utils.classfile;

import java.io.*;

public class NameAndTypeRef extends ConstantRef {

	short m_index;
	short m_descIndex;


	public NameAndTypeRef()
	{
		m_tag = NAMEANDTYPE_TAG;
	}
	public void read(DataInputStream dis) throws IOException
	{
		m_index = dis.readShort();
		m_descIndex = dis.readShort();
	}

	short getDescriptor()
	{
		return(m_descIndex);
	}

	short getIndex()
	{
		return(m_index);
	}

	public void write(DataOutputStream dos) throws IOException
	{
		super.write(dos);
		dos.writeShort(m_index);
		dos.writeShort(m_descIndex);
	}

	public String toString()
	{
		return(new String("NameAndTypeRef:" + m_index + ":" + m_descIndex));
	}


}
