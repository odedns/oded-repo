/**
 * Copyright (c) 1994-1999 Oded Nissan.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * 3. The name of the author may not be used to endorse or promote
 * products derived from this software without specific prior written
 * permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS
 * OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED.  IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 *
 */
package onjlib.utils.classfile;

import java.io.*;

/**
 * Title:   	ClassFile
 * Description:
 * <p> Copyright (c) 2002
 * @author Oded Nissan
 * @version 1.0 14/01/2002
 */

public class ClassFile {

	/**
	 * Member variables.
	 */
	DataInputStream m_dis;
	DataOutputStream m_dos;
	ClassFileHeader m_header;
	ConstantPool m_cPool;
	ClassInfo m_cInfo;
	int m_numFields;
	int m_numMethods;
	int m_numAttrs;
	MethodEntry m_fields[];
	MethodEntry m_methods[];
	Attribute m_attrs[];

	/**
	 * empty ctor creates a ClassFile Object.
	 */
	public ClassFile()
	{
		m_header = new ClassFileHeader();
		m_cPool = new ConstantPool();
		m_cInfo = new ClassInfo();

	}

	/**
	 * creates a ClassFile Object using the filename as input.
	 * @param fname the name of the input class file.
	 * @throws ClassFileException
	 */
	public ClassFile(String fname) throws ClassFileException
	{

		try {
			m_dos = new DataOutputStream(new FileOutputStream(fname));
		}
		catch (IOException ex) {
			throw new ClassFileException("Error opening output file", ex);

		}


	}

	/**
	 * creates a ClassFile Object using the InputStream as input.
	 * @param  is the InputStream to read class file from.
	 */
	public ClassFile(InputStream is)
	{
		m_dis = (DataInputStream) is;
		m_header = new ClassFileHeader();
		m_cPool = new ConstantPool();
		m_cInfo = new ClassInfo();

	}

	/**
	 * sets the output Stream to use for writing class file
	 * data.
	 * @param dos a DataOutputStream object.
	 */
	public void setOutputStream(DataOutputStream dos)
	{
		m_dos = dos;
	}

	/**
	 * sets the input Stream to use for reading class file
	 * data.
	 * @param dis a DataInputStream object.
	 */
	public void setInputStream(DataInputStream dis)
	{
		m_dis = dis;
	}

	/**
	 * gets the classfile header info.
	 * @return ClassFileHeader object.
	 */
	public ClassFileHeader getClassFileHeader()
	{
		return(m_header);

	}

	/**
	 * sets the ClassFileHeader for this ClassFile object.
	 * @param header a ClassFileHeader object.
	 */
	public void setClassFileHeader(ClassFileHeader header)
	{
		m_header = header;
	}

	/**
	 * gets the classfile info.
	 * @return Classinfo object.
	 */
	public ClassInfo getClassInfo()
	{
		return(m_cInfo);
	}

	/**
	 * sets the ClassInfo  for this ClassFile object.
	 * @param info a ClassInfo object.
	 */
	public void setClassInfo(ClassInfo info)
	{
		m_cInfo = info;
	}

	/**
	 * gets the classfile ConstantPool object.
	 * @return  a ConstantPool object.
	 */
	public ConstantPool getConstantPool()
	{
		return(m_cPool);
	}

	/**
	 * sets the ConstantPool object for this ClassFile object.
	 * @param  cp a ConstantPool object.
	 */
	public void setConstantPool(ConstantPool cp)
	{
		m_cPool = cp;
	}

	public MethodEntry[] getMethods()
	{
		return(m_methods);
	}

	public void setMethods(MethodEntry m[])
	{
		m_numMethods = m.length;
		m_methods = m;
	}

	public MethodEntry[] getFields()
	{
		return(m_fields);
	}
	public void setFields(MethodEntry f[])
	{
		m_numFields = f.length;
		m_fields = f;
	}

	public Attribute[] getAttributes()
	{
		return(m_attrs);
	}
	public void setAttributes(Attribute attrs[])
	{
		m_numAttrs = attrs.length;
		m_attrs = attrs;
	}


	/**
	 * writes class file data to the output file used
	 * by this ClassFile object.
	 * @throws ClassFileException in case of error.
	 */
	public void write() throws ClassFileException
	{
	try {
		/**
		 * write class header.
		 */
		m_header.write(m_dos);

		/**
		 * write constant pool.
		 */
		m_cPool.write(m_dos);
		UTF8Dict.setConstantPool(m_cPool);

		/**
		 * write class info.
		 */
		m_cInfo.write(m_dos);

		/**
		 * write fields
		 */
		m_dos.writeShort(m_numFields);
		for(int i=0; i < m_numFields; ++i) {
			m_fields[i].write(m_dos);

		}

		/**
		 * write methods.
		 */
		m_dos.writeShort(m_numMethods);
		for(int i=0; i < m_numMethods; ++i) {
			m_methods[i].write(m_dos);

		}

		/**
		 * write class attributes.
		 */
		m_dos.writeShort(m_numAttrs);
		for(int i=0; i < m_numAttrs; ++i) {
			m_attrs[i].write(m_dos);

		}
		m_dos.close();

	}
	catch (IOException ex) {
		throw new ClassFileException("Error writing classfile", ex);
	}


	}

	/**
	 * read the class file data from the InputStream used
	 * by the ClassFile object. The data is read into
	 * the appropriate data structures.
	 * @throws ClassFileException in case of error.
	 */
	public void read() throws ClassFileException
	{
	try {

		m_header.read(m_dis);
		m_cPool.read(m_dis);
		UTF8Dict.setConstantPool(m_cPool);
		m_cInfo.read(m_dis);



		/**
		 * read fields and methods.
		 */
		 m_numFields = m_dis.readUnsignedShort();
		 m_fields = new MethodEntry[m_numFields];

		 for(int i=0; i < m_numFields; ++i) {
			m_fields[i] = new MethodEntry();
			m_fields[i].read(m_dis);

		 }
		 m_numMethods = m_dis.readUnsignedShort();

		  m_methods = new MethodEntry[m_numMethods];
		 for(int i=0; i < m_numMethods; ++i) {
			m_methods[i] = new MethodEntry();
			m_methods[i].read(m_dis);

		 }
		 /**
		  * now read class Attributes.
		  */
		 m_numAttrs = m_dis.readUnsignedShort();
		 m_attrs = new Attribute[m_numAttrs];
		 for(int i=0; i < m_numAttrs; ++i) {
			m_attrs[i] = AttributeReader.getAttribute(m_dis);
			m_attrs[i].read(m_dis);
		 }

	} catch (IOException ex) {
		throw new ClassFileException("Error reading classfile", ex);
	}


	}

	void print()
	{
		System.out.println("Class File dump\n===============");
		System.out.println("\n\nClassFile Header\n------------\n" + m_header.toString());
		System.out.println(m_cPool.toString());
		System.out.println("\n\nClassInfo\n------");
		System.out.println(m_cInfo.toString());
		System.out.println("\n\nFields\n--------");
		System.out.println("numField : " + m_numFields );
		for(int i=0; i < m_numFields; ++i) {
			System.out.println( m_fields[i].toString());
		}
		System.out.println("\n\nMethods\n--------");
		System.out.println("numMethods : " + m_numMethods );
		for(int i=0; i < m_numMethods; ++i) {
			System.out.println(m_methods[i].toString());
		 }
		 /**
		  * now read class Attributes.
		  */
		System.out.println("\n\nClass Attributes\n---------");
		System.out.println("numAttrs : " + m_numAttrs );
		for(int i=0; i < m_numAttrs; ++i) {
			System.out.println(m_attrs[i].toString());
		 }

	}

	/**
	 * main test driver.
	 */
	public static void main(String argv[])
	{
		if(argv.length > 0 ) {
			try {
				//Debug.setDebug(true);
				DataInputStream dis = new DataInputStream(new FileInputStream(argv[0]));
				ClassFile cf = new ClassFile(dis);
				cf.read();
				cf.print();
				ClassFile newcf = new ClassFile("c:/tmp/B.class" );
				newcf.setClassFileHeader(cf.getClassFileHeader());
				newcf.setConstantPool(cf.getConstantPool());
				newcf.setClassInfo(cf.getClassInfo());

				newcf.setFields(cf.getFields());
				newcf.setMethods(cf.getMethods());
				newcf.setAttributes(cf.getAttributes());
				/**
				 * write the class file.
				 */
				 newcf.write();
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}

		} else {
			System.out.println("class name missing .. ");
		}

	}
}
