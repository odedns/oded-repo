package onjlib.utils.compress;

import java.util.Hashtable;
import java.io.*;


class LZWTable {
	Hashtable m_stringTable;
	Hashtable m_codeTable;
	static final int ASCII_CODE = 256;
	static final int FIRST_CODE = 0;

	short m_nextCode;


	LZWTable()
	{
		m_stringTable = new Hashtable();
		m_codeTable = new Hashtable();
		m_nextCode = FIRST_CODE;
		char c;

		for(int i=0; i < ASCII_CODE; ++i) {
			c = (char) i;
			StringBuffer sb = new StringBuffer(2);
			sb.append(c);
			add(sb.toString());
		}
		System.out.println("nextCode = " + m_nextCode);
	}

	void add(String s)
	{
		Short code = new Short(m_nextCode);
		m_stringTable.put(s, code );
		m_codeTable.put(code,s);
		++m_nextCode;
	}

	short getCode(String s)
	{
		Short code = (Short)m_stringTable.get(s);
		return (code != null ? code.shortValue() : -1);
	}

	String getString(int code)
	{
		Short icode = new Short((short)code);
		return((String)m_codeTable.get(icode));
	}

}


public class LZW {
	LZWTable m_table;
	StringBuffer sb;

	LZW()
	{
		m_table = new LZWTable();
		sb = new StringBuffer();
		sb.append("");
	}

	void compress(InputStream in, OutputStream out)
	{
		int c;
		String oldString = "";
		String currString = "";
		int code;

		BufferedInputStream b_in = new BufferedInputStream(in);
		DataOutputStream b_out = new DataOutputStream(out);
		try {
			c = b_in.read();
			oldString = String.valueOf((char)c);
			while(-1 != (c=b_in.read())) {
				currString = oldString + (char)c;
				//System.out.println("currString = " + currString);
				code = m_table.getCode(currString);
				if(code == -1) {
					System.out.println("adding: " + currString);
					m_table.add(currString);
					/*
					 * output oldCode.
					 */
//				System.out.println("oldString =  " + oldString);
					code = m_table.getCode(oldString);
					System.out.println("outputting : " + oldString+":" + code);
					b_out.writeShort(code);
					oldString = String.valueOf((char)c);
				} else {
					oldString = currString;
				}

			}
			code = m_table.getCode(currString);
			System.out.println("outputting : " + code);
			b_out.writeShort(code);
			b_in.close();
			b_out.close();
		} catch(IOException e) {
			e.printStackTrace();
		}

	} /* compress */

	void decompress(InputStream in)
	{
		int c;
		String oldString = "";
		String currString = "";
		String s = "";
		int code, oldCode;
		DataInputStream b_in = new DataInputStream(in);

		try {
			oldCode = b_in.readShort();
			oldString = m_table.getString(oldCode);
			while(true) {
				code=b_in.readShort();
				System.out.println("code = " + code);
				System.out.println("oldString = " + oldString);
				sb.append(oldString);
				currString = m_table.getString(code);
				s = oldString + currString;
				System.out.println("adding: " + s);
				m_table.add(s);
				oldString = currString;
			}
		} catch (Exception ee) {
		}
		try {
			b_in.close();
		} catch(IOException e) {
			e.printStackTrace();
		}

	} /* decompress */


	public static void main(String argv[])
	{
		String s = "acbcbacbcbd";
		FileInputStream f_in = null;
		FileOutputStream f_out = null;


		LZW lzw = new LZW();
		try {
			f_in = new FileInputStream("aa");
			f_out = new FileOutputStream("out");
			lzw.compress(f_in,f_out);
			f_in = new FileInputStream("out");
		} catch(IOException e) {
			e.printStackTrace();
		}
		lzw.decompress(f_in);
		System.out.println("sb = " + lzw.sb);

	}
}

