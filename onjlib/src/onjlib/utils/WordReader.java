package onjlib.utils;

import java.io.StringReader;
import java.io.IOException;
/**
 * The WordReader class reads words from a StringReader stream.
 * Words are seperated by whitespace charcters (' ',\t \n).
 * The WordReader is initialized with a string all subsequent reads
 * will try to read words from this string.
 *
 * <p>Copyright (c) 2002
 *
 * @author Oded Nissan
 * @version 1.0  23/4/2002
 */

public class WordReader extends StringReader {
	StringBuffer m_sb;
	/**
	 * Initialize the WordReader to read words
	 * from the specified string.
	 * Calls the StringReader constructor.
	 * @param s the String to read data from.
	 */
	public WordReader(String s)
	{
		super(s);
		m_sb = new StringBuffer();
	}

	/**
	 * Read a word from the WordReader.
	 * Words are seperated by whitespace charcters (' ',\t \n).
	 * @return String the word that was read or null if there
	 * are no more words in the input stream.
	 */
	public String readWord() throws IOException
	{
		String word = null;
		int c;
		// reset the StringBuffer.
		m_sb.delete(0, m_sb.length());
		int wordChars = 0;
		boolean done = false;

		while(!done && -1 != (c = read())) {
			if(Character.isWhitespace((char)c)) {
				if(wordChars > 0) {
					done = true;
				}
				continue;
			} else {
				m_sb.append((char)c);
				++wordChars;
			}

		} // while
		if(wordChars > 0) {
			word = m_sb.toString();
		}
		return(word);
	}

	/**
	 * main test program.
	 */
	 public static void main(String argv[])
	 {
		String s = Console.readString("Enter s:");
		WordReader wr = new WordReader(s);
		String word;
		try {
			while(null != (word = wr.readWord())) {
				System.out.println("word = " + word);
			}
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}


	}

}