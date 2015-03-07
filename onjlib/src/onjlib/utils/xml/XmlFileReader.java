
/**
 * Copyright (c) 1994-2002 Oded Nissan.
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
package onjlib.utils.xml;

import java.io.*;
import org.w3c.dom.*;

/**
 * XmlUtils class provides some usful methods for reading writing
 * and creating XML files. The class tries to use the jaxp API when possible
 * and also uses weblogic's apache package for serializing XML output files.
 */
public class XmlFileReader{
	Document m_doc;

	/**
	 * constructor that reads an Xml file into a Document object.
	 * in case of any error the GXmlException is thrown, this exception
	 * wraps the original exception.
	 * @param fname the file name of the Xml file to read.
	 * @throws GXmlException a generic Xml exception.
	 */
	public XmlFileReader(String fname) throws GXmlException
	{
		try {
			m_doc = XmlUtils.readXmlDocument(fname);
		}
		catch (Exception ex) {
			m_doc = null;
			throw new GXmlException("Error reading file: " + fname, ex);

		}
	}

	/**
	 * constructor that reads an Xml file into a Document object.
	 * in case of any error the GXmlException is thrown, this exception
	 * wraps the original exception.
	 * @param is the InputStream  of the Xml file to read.
	 * @throws GXmlException a generic Xml exception.
	 */
	public XmlFileReader(InputStream is) throws GXmlException
	{
		try {
			m_doc = XmlUtils.readXmlDocument(is);
		}
		catch (Exception ex) {
			m_doc = null;
			throw new GXmlException("Error reading InputStream ", ex);

		}

	}

	/**
	 * get the value of an Element in the Xml Document.
	 * finds the first occurance of the element and retrieves its value.
	 * @param elemName the name of the element to search for.
	 * @return String the element value or null if not found.
	 */
	public String getElementValue(String elemName)
	{
		return(XmlUtils.getElementValue(m_doc.getDocumentElement(),elemName));
	}

	/**
	 * sets the value of an Element in the Xml Document.
	 * finds the first occurance of the element and sets its value.
	 * @param elemName the name of the element to search for.
	 * @param value the value of the element.
	 * @return String the element value or null if not found.
	 */
	public void setElementValue(String elemName, String value)
	{
		XmlUtils.setNodeValue(m_doc,elemName,value);
	}

	/**
	 * get the value of an Attribute in the Xml Document.
	 * finds the first occurance of the parent element and then searches its attributes
	 * for the occurance of the attribute and retrieves its value.
	 * @param elemName the name of the element to search for.
	 * @param att the name of the attribute  to search for.
	 * @return String the attribute value or null if not found.
	 */
	public String getAttributeValue(String elemName, String att)
	{
		return(XmlUtils.getElementAttribute(m_doc.getDocumentElement(),elemName,att));
	}

	public void setAttributeValue(String elemName, String att, String value)
	{
	}

	/**
	 * get the value of an Element in the Xml Document.
	 * finds the first occurance of the parent element and then searches its children
	 * for the first occurance of the element and retrieves its value.
	 * @param parent the name of the parent element to search for.
	 * @param elemName the name of the child element to search for.
	 * @return String the element value or null if not found.
	 */
	public String getSubElementValue(String parent, String elemName)
	{
		return(XmlUtils.getSubElementValue(m_doc.getDocumentElement(),parent,elemName));
	}



}
