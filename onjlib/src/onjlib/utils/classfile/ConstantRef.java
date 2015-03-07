package onjlib.utils.classfile;

import onjlib.utils.Debug;
import java.io.*;

public abstract class ConstantRef {

	byte m_tag;
	static final byte UTF8_TAG = 1;
	static final byte INTEGER_TAG = 3;
	static final byte FLOAT_TAG = 4;
	static final byte LONG_TAG = 5;
	static final byte DOUBLE_TAG = 6;
	static final byte CLASS_TAG = 7;
	static final byte STRING_TAG = 8;
	static final byte FIELDREF_TAG = 9;
	static final byte METHODREF_TAG = 10;
	static final byte IFMETHODREF_TAG = 11;
	static final byte NAMEANDTYPE_TAG = 12;

	public void read(DataInputStream dis) throws IOException
	{
		m_tag = dis.readByte();
	}

	public byte getTag()
	{
		return(m_tag);
	}

	public void setTag(byte tag)
	{
		m_tag = tag;
	}

	public void write(DataOutputStream dos) throws IOException
	{
		dos.write(m_tag);
		Debug.println("write() " + this.toString());
	}

	public abstract String toString();

}
