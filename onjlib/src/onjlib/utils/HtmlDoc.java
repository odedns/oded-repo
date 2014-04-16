package onjlib.utils;
import java.io.*;
import javax.swing.text.html.parser.*;



/**
 * <p>Title: HtmlDoc</p>
 * <p>Description: Class for retrieving information from html documents.</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * @author Oded Nissan
 * @version 1.0 27/08/2002
 */

public class HtmlDoc {

	Reader m_doc;
	HtmlCallbacks m_callBacks;
	ParserDelegator m_parser;
	/**
	 * Read input data from an HTML file
	 * specified by Reader.
	 * @param r the Reader input reader for the HTML file to parse.
	 */
	public HtmlDoc(Reader r) throws IOException
	{
		m_doc = r;
		init();
	}

	/**
	 * Read input data from a String containing HTML data.
	 * @param s - a String containing HTML data to parse.
	 */
	public HtmlDoc(String s) throws IOException
	{
		m_doc = new StringReader(s);
		init();

	}

	private void init() throws IOException
	{
		m_callBacks = new HtmlCallbacks();
		m_parser = new ParserDelegator();
		m_parser.parse(m_doc, m_callBacks,true);
	}

	/**
	 * get the HTML document's title.
	 * @return String the document title.
	 */
	public String getTitle()
	{
		return(m_callBacks.getTitle());
	}

	/**
	 * get the HTML document text.
	 * @return String the document text.
	 */
	public String getText()
	{
		return(m_callBacks.getText());
	}


	/**
	 * main text program.
	 */
	public static void main(String argv[])
	{
		String fname = "c:/dev/docs/shelltips.html";
		try {
			HtmlDoc doc = new HtmlDoc(new FileReader(fname));
			System.out.println("title = " + doc.getTitle());
			System.out.println("text = " + doc.getText());
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}
}
