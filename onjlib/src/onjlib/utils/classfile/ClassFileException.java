package onjlib.utils.classfile;

import onjlib.utils.GWrappingException;
/**
 * Title:       ClassFileException
 * Description: An exception for class file package
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author      Oded Nissan
 * @version 1.0
 */

public class ClassFileException extends GWrappingException {

	public ClassFileException(String msg) {
		super(msg);
	}

	public ClassFileException(String msg, Throwable th)
	{
		super(msg,th);
	}
}