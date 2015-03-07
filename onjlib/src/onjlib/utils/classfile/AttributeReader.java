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

public abstract class AttributeReader {

	public AttributeReader() {
	}
	public static Attribute getAttribute(DataInputStream dis) throws java.io.IOException
	{

		int index = dis.readUnsignedShort();
		int len = dis.readInt();
		String name = UTF8Dict.getString(index);
		if(null == name) {
			return(new UnknownAttribute(index,len));
		}

		if(name.equalsIgnoreCase("Code")) {
			return(new CodeAttribute(index, len));
		}

		if(name.equalsIgnoreCase("LineNumberTable")) {
			return(new LineNumberTableAttribute(index,len));
		}
		if(name.equalsIgnoreCase("ConstantValue")) {
			return(new ConstantValueAttribute(index,len));
		}
		if(name.equalsIgnoreCase("SourceFile")) {
			return(new SourceFileAttribute(index,len));
		}
		if(name.equalsIgnoreCase("InnerClasses")) {
			return (new InnerClassAttribute(index, len));
		}
		if(name.equalsIgnoreCase("Exceptions")) {
			return(new ExceptionsAttribute(index,len));
		}
		return(new UnknownAttribute(index,len));


	}

}