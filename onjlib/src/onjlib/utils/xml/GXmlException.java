
package onjlib.utils.xml;

import onjlib.utils.GWrappingException;
/**
 * An exception for wrapping different Xml exceptions.
 * <p>Copyright (c) 2002
 * @author      Oded Nissan
 * @version 1.0  12/4/2002
 */

public class GXmlException extends GWrappingException {

	public GXmlException(String msg) {
		super(msg);
	}

	public GXmlException(String msg, Throwable th)
	{
		super(msg,th);
	}
}
