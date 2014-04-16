
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

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import onjlib.utils.MultiProperties;

import java.net.URI;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


/**
 * XmlUtils class provides some usful methods for reading writing
 * and creating XML files. The class tries to use the jaxp API when possible
 * and also uses weblogic's apache package for serializing XML output files.
 */
public abstract class XmlUtils {

	/* static OutputFormat m_of = null; */

	/**
	 * read and parse an XML document and return a DOM style Document
	 * object.
	 * @param fname the XML filename.
	 * @return Document a DOM style Document object.
	 * @throws GXmlException
	 */
	public static Document readXmlDocument(String fname)
		throws GXmlException

	{
		Document doc = null;
		try {
			File f = new File(fname);
			URI uri = f.toURI();
			fname = uri.toString();
			DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
			fact.setValidating(true);
			DocumentBuilder db = fact.newDocumentBuilder();
			doc = db.parse(new InputSource(fname));
		} catch(Exception ex) {
			throw new GXmlException("Error in readXmlDocument", ex);
		}
		return(doc);

	}

	/**
	 * read and parse an XML document and return a DOM style Document
	 * object.
	 * @param is an InputStream pointing to the XML file.
	 * @return Document a DOM style Document object.
	 * @throws GXmlException
	 */
	public static Document readXmlDocument(InputStream is)
		throws GXmlException
	{
		Document doc = null;
		try {

			DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = fact.newDocumentBuilder();
			doc = db.parse(is);
		} catch (Exception ex) {
			throw new GXmlException("Error in readXmlDocument", ex);
		}

		return(doc);
	}

	/**
	 * createXmlDocumnet creates a new Document object using the JAXP API.
	 * @return Document a DOM style Document object.
	 * @throws ParserConfigurationException
	 */
	public static Document createXmlDocument()
		throws ParserConfigurationException
	{
		DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = fact.newDocumentBuilder();
		Document doc = db.newDocument();
		return(doc);
	}

	/**
	 * Write an XML document into an OutputStream, using the apache API.
	 * @param  doc the Document object containing the XML data to be written.
	 * @param os the OutputStream to which the XML data will be written.
	 * @throws GXmlException
	 */
	public static void writeXmlDocument(Document doc, OutputStream os)
		throws GXmlException
	{
		try {
			TransformerFactory trans_factory=TransformerFactory.newInstance();
			Transformer xml_out = trans_factory.newTransformer();
			Properties props = new Properties();
			props.put("method", "xml");
			props.put("indent", "yes");
			xml_out.setOutputProperties(props);
			xml_out.transform(new DOMSource(doc),new StreamResult(os));
		} catch (Exception ex) {
			throw new GXmlException("Error in writeXmlDocument", ex);
		}


	}

	/**
	 * Write an XML document into an OutputStream, using the apache API.
	 * @param  doc the Document object containing the XML data to be written.
	 * @param wr the Writer to which the XML data will be written.
	 * @throws GXmlException
	 */
	public static void writeXmlDocument(Document doc, Writer wr)
		throws GXmlException
	{

		try {
			TransformerFactory trans_factory=TransformerFactory.newInstance();
			Transformer xml_out = trans_factory.newTransformer();
			Properties props = new Properties();
			props.put("method", "xml");
			props.put("indent", "yes");
			xml_out.setOutputProperties(props);
			xml_out.transform(new DOMSource(doc),new StreamResult(wr));
		} catch (Exception ex) {
			throw new GXmlException("Error in writeXmlDocument", ex);
		}
	}

	/**
	 * Write an XML document into an OutputStream, using the apache API.
	 * @param  doc the Document object containing the XML data to be written.
	 * @param os the OutputStream to which the XML data will be written.
	 * @throws IOException
	 */
	 /*
	public static void writeXmlDocument(Document doc, OutputStream os)
		throws IOException
	{

		if(null == m_of) {
			m_of = new OutputFormat();
			m_of.setEncoding("UTF-8");
			m_of.setLineWidth(40);
			m_of.setIndent(4);
			m_of.setIndenting(true);
		}

		XMLSerializer xs = new XMLSerializer(os, m_of);
		xs.serialize(doc);
	}
*/

	/**
	 * set the OutputFormat to use for formatting XML output.
	 * @param of the OutputFormat object to use.
	 */
	 /*
	public void setOutputFormat(OutputFormat of)
	{
		m_of = of;
	}
	*/



	/**
	 * convert an XML configuration file into a MultiPropertie object.
	 * Elements and Attributes are converted to keys their values are
	 * stored as values in the MultiProperties object.
	 * Nexted elements are chained, so that:
	 * <server>
	 * 		<address>199.0.0.1</address>
	 * 		<port>8080</port>
	 * </server>
	 * is translated to:
	 * server.address=199.0.0.1
	 * server.port=8080
	 *
	 * @param fname the name of the XML configuration file to process.
	 * @return a MultiProperties object containing the configuration
	 * file data.
	 */
	public MultiProperties xml2Properties(String fname)
	{
		MultiProperties hash = null;
		try {
			// Set the ContentHandler...
			 Xml2Hash handler = new Xml2Hash();
			SAXParserFactory fact = SAXParserFactory.newInstance();
			SAXParser sp = fact.newSAXParser();
			sp.parse(new FileInputStream(fname),handler);
			hash = handler.getProperties();
		 }catch ( Exception e )  {
		 e.printStackTrace();
		}
		return(hash);
	}

	/**
	 * get the value of an Element in the Xml Document.
	 * finds the first occurance of the element and retrieves its value.
	 * @param root the root Element.
	 * @param elemName the name of the element to search for.
	 * @return String the element value or null if not found.
	 */
	public static String getElementValue(Element root, String elemName)
	{
		NodeList nl = root.getElementsByTagName(elemName);
		if(null == nl ) {
			return(null);
		}
		Node n = nl.item(0);
		return(n.getFirstChild().getNodeValue().trim());
	}

	/**
	 * sets the value of an Element in the Xml Document.
	 * finds the first occurance of the element and sets its value.
	 * @param root the root Element.
	 * @param elemName the name of the element to search for.
	 * @return String the element value or null if not found.
	 */
	public static void setNodeValue(Document doc, String elemName, String value)
	{
		Element root = doc.getDocumentElement();
		NodeList nl = root.getElementsByTagName(elemName);
		if(null != nl ) {
			Node n = nl.item(0);
			if(null != n) {
				n.getFirstChild().setNodeValue(value);
			} else {
				addNode(doc,elemName,value);
			}
		} else {
			addNode(doc,elemName,value);
		}

	}

	/**
	 * internal function for adding an element to the DOM tree.
	 */
	private static void addNode(Document doc, String elemName, String value)
	{
			Element root = doc.getDocumentElement();
			Element elem = doc.createElement(elemName);
			elem.appendChild(doc.createTextNode(value));
			root.appendChild(elem);
	}
	/**
	 * get the value of an Element in the Xml Document.
	 * finds the first occurance of the parent element and then searches its children
	 * for the first occurance of the element and retrieves its value.
	 * @param root the root Element.
	 * @param parent the name of the parent element to search for.
	 * @param elemName the name of the child element to search for.
	 * @return String the element value or null if not found.
	 */
	public static String getSubElementValue(Element root, String parent,String elemName)
	{
		NodeList nl = root.getElementsByTagName(parent);
		String value = null;
		if(null == nl ) {
			return(null);
		}
		Node node = nl.item(0);
		nl = node.getChildNodes();
		if(null == nl) {
			return(null);
		}

		boolean found = false;
		for(int i=0;  !found && i < nl.getLength(); ++i) {
			Node n = nl.item(i);
			if(elemName.equals(n.getNodeName())) {
				value = n.getFirstChild().getNodeValue().trim();
				found = true;
				break;
			} // if
		} // for
		return(value);
	}

	/**
	 * get the value of an Attribute in the Xml Document.
	 * finds the first occurance of the parent element and then searches its attributes
	 * for the occurance of the attribute and retrieves its value.
	 * @param root the root Element.
	 * @param elemName the name of the element to search for.
	 * @param att the name of the attribute  to search for.
	 * @return String the attribute value or null if not found.
	 */
	public static String getElementAttribute(Element root, String elemName, String att)
	{
		NodeList nl = root.getElementsByTagName(elemName);
		if(null == nl ) {
			return(null);
		}
		Node n = nl.item(0);
		if(null == n) {
			return(null);
		}
		NamedNodeMap attributes = n.getAttributes();
		if(null == attributes) {
			return(null);
		}
		n = attributes.getNamedItem(att);
		if(null == n) {
			return(null);
		}
		return(n.getNodeValue().trim());

	}

	/**
	 * main test driver.
	 */
	public static void main(String argv[])
	{
		try {

/*
			DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = fact.newDocumentBuilder();
			DOMImplementation di = builder.getDOMImplementation();
			DocumentType docType = di.createDocumentType("ejb-jar","'-//Sun Microsystems, Inc.//DTD Enterprise JavaBeans 2.0//EN", "http://java.sun.com/j2ee/dtds/ejb-jar_2_0.dtd");

			Document doc = di.createDocument("", "ejb-jar",docType);
			*/

			Document doc = XmlUtils.createXmlDocument();

			Element message = doc.createElement("message");
			doc.appendChild(message);
			Element text = doc.createElement("text");
			text.appendChild(doc.createTextNode("Hello world."));
			message.appendChild(text);
			Element timestamp = doc.createElement("timestamp");
			timestamp.appendChild( doc.createTextNode( "text foo"));
			message.appendChild(timestamp);
			XmlUtils.setNodeValue(doc,"oded","nissan");
			XmlUtils.setNodeValue(doc,"text","bullshit");

			XmlUtils.writeXmlDocument(doc,System.out);

//			doc = XmlUtils.readXmlDocument("e:/dev/src/java/xml/logger.xml");
//			doc = XmlUtils.readXmlDocument("e:/dev/src/java/myutils/onjlib/servlet/mvcservlet.xml");
//			XmlUtils.writeXmlDocument(doc,System.out);
		} catch(Exception e) {
			e.printStackTrace();
		}

	}


}

