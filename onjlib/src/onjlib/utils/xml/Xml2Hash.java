package onjlib.utils.xml;

import onjlib.utils.GStack;
import onjlib.utils.MultiProperties;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import javax.xml.parsers.*;
import java.io.*;

/**
 * Convert an Xml file into a MultiProprties Object.
 * <p>Copyright (c) 2001
 * @author Oded Nissan
 * @version 1.0 30/3/2002
 */

public class Xml2Hash extends DefaultHandler {

	/* Buffer for collecting data from // the "characters" SAX event. */
	private CharArrayWriter contents = new CharArrayWriter();
	GStack m_stk;
	MultiProperties m_props;
	String lastName = null;


	public Xml2Hash()
	{
		m_stk = new GStack();
		m_props = new MultiProperties();
	}

	public MultiProperties getProperties()
	{
		return(m_props);
	}

   /**
	*  Override methods of the DefaultHandler class
	*  to gain notification of SAX Events.
	*/
   public void startElement( String namespaceURI,
			  String localName,
			  String qName,
			  Attributes attr ) throws SAXException {


	  String ename = (String ) m_stk.peek();
	  if(null == ename) {
		ename = localName;
	  } else {
		ename = ename + "." + localName;
	  }

	  m_stk.push(ename);
	  /**
	   * now loop over all Attributes.
	   */
	   if(attr != null) {
		for(int i=0; i < attr.getLength(); ++i) {
			String name = attr.getLocalName(i);
			String value = attr.getValue(i);
			ename = (String ) m_stk.peek();
			if(null != ename) {
				name = ename + "." + name;
			}
			m_props.setProperty(name,value);
		} // for
	   } // if


   }

   public void endElement( String namespaceURI,
			  String localName,
			  String qName ) throws SAXException
	{

		String value = contents.toString().trim();
		contents.reset();
		String ename = (String ) m_stk.pop();
		if(null != ename && value.length() > 0) {
			m_props.setProperty(ename,value);
		}
   }



   public void characters( char[] ch, int start, int length )
				  throws SAXException {

	  contents.write( ch, start, length );

   }

	void dump()
	{
		System.out.println("m_propes = " + m_props.toString());
	}

   public static void main(String argv[])
   {
		System.out.println( "Xml2Hash" );
	  try {


		 // Set the ContentHandler...
		 Xml2Hash handler = new Xml2Hash();
		 SAXParserFactory fact = SAXParserFactory.newInstance();
		 SAXParser sp = fact.newSAXParser();
		 sp.parse(new FileInputStream("e:/dev/src/java/xml/xml_proj.jpx"),handler);

		handler.dump();
	  }catch ( Exception e )  {
		 e.printStackTrace();
	  }
   }


}
