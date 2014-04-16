package onjlib.utils.classfile;

import onjlib.utils.Debug;
import java.io.*;
import java.util.*;

public class ConstantPool {
	short m_counter;
	ArrayList m_refs;

	public ConstantPool()
	{
		m_refs = new ArrayList();
	}
	public void write(DataOutputStream dos) throws IOException
	{
		Debug.println("ContstantPool.write() counter:" + m_counter);
		dos.writeShort(m_counter);
		ConstantRef cr;
		Iterator iter =  m_refs.iterator();
		while(iter.hasNext()) {
			cr = (ConstantRef) iter.next();
			cr.write(dos);
		}
		dos.flush();


	}

	public void read(DataInputStream dis) throws IOException
	{
		m_counter = dis.readShort();
		for(int i=0; i<m_counter-1; ++i) {
			m_refs.add(getConstantRef(dis));
		}
	}

	private ConstantRef getConstantRef(DataInputStream dis) throws IOException
	{
		byte tag;

		ConstantRef cr;
		tag = dis.readByte();
		switch(tag) {
			case ConstantRef.UTF8_TAG:
				cr = new UTF8Ref();
				cr.read(dis);
				break;
			case ConstantRef.LONG_TAG:
				cr = new LongRef();
				cr.read(dis);
				break;
			case ConstantRef.INTEGER_TAG:
				cr = new IntegerRef();
				cr.read(dis);
				break;
			case ConstantRef.FLOAT_TAG:
				cr = new FloatRef();
				cr.read(dis);
				break;
			case ConstantRef.DOUBLE_TAG:
				cr = new DoubleRef();
				cr.read(dis);
				break;
			case ConstantRef.STRING_TAG:
				cr = new StringRef();
				cr.read(dis);
				break;
			case ConstantRef.CLASS_TAG:
				cr = new StringRef();
				cr.setTag(ConstantRef.CLASS_TAG);
				cr.read(dis);
				break;
			case ConstantRef.FIELDREF_TAG:
				cr = new FieldRef();
				cr.read(dis);
				break;
			case ConstantRef.IFMETHODREF_TAG:
				cr = new FieldRef();
				cr.setTag(ConstantRef.IFMETHODREF_TAG);
				cr.read(dis);
				break;
			case ConstantRef.METHODREF_TAG:
				cr = new FieldRef();
				cr.setTag(ConstantRef.METHODREF_TAG);
				cr.read(dis);
				break;
			case ConstantRef.NAMEANDTYPE_TAG:
				cr = new NameAndTypeRef();
				cr.read(dis);
				break;
			default:
				System.out.println("unknown tag: " + tag);
				cr = null;
				break;
		}


		return(cr);

	}

	public String toString()
	{
		StringBuffer sb = new StringBuffer();

		sb.append("ConstantPool count: " + m_counter + '\n');
		for(int i=0; i<m_refs.size(); ++i) {
			sb.append(i+1 + ":" + m_refs.get(i).toString() + '\n');
		}
		return(sb.toString());
	}

	public ConstantRef getRef(int index)
	{
		ConstantRef cr = null;
		if(index < m_refs.size()) {
			cr = (ConstantRef)m_refs.get(index);
		}
		return(cr);
	}



}
