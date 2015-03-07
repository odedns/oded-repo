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

public class InnerClassAttribute extends Attribute {


	public InnerClassAttribute(int nameIndex, int len)
	{
		super(nameIndex,len);
	}

	public void write(DataOutputStream dos) throws IOException
	{
	}

	public void read(DataInputStream dis) throws IOException
	{
	}

	public String toString()
	{
		return(new String("InnerClassAttribute"));
	}
}