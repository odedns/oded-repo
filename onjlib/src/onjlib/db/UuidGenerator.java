/*
 * Created on 15/01/2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package onjlib.db;

import java.net.InetAddress;
import java.util.Random;

import onjlib.utils.Convert;

/**
 * @author odedn
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UuidGenerator  implements IdGenerator {

	private static final int UUID_LEN = 16;
	private static final char SEP = '-';
	private static int m_ip = -1;
	private int m_clock;
	private short m_filler;
	
	/**
	 * Constructor initialize the Uuid. 
	 */
	public UuidGenerator() {		
		super();
		// TODO Auto-generated constructor stub
		long millis = System.currentTimeMillis();
		Random rnd  = new Random(millis);
		m_clock = rnd.nextInt();
		m_filler = (short)System.identityHashCode(this);
		
		if(m_ip == -1) {
			try {
				InetAddress addr = InetAddress.getLocalHost();
				byte b[] = addr.getAddress();
				m_ip = Convert.bytesToInt(b);						
			} catch(Exception e) {
				e.printStackTrace();
				m_ip = rnd.nextInt(); 
			}
		} // if
		
	}
	
	
	/**
	 * get the ip address.
	 * @return int the ipaddress as an int.
	 */
	public int getIp()
	{
		return(m_ip);
	}
	
	
	/**
	 * get the next UUID value as aString
	 * @return String the uuid value.
	 */
	public String nextStringVal()
	{
		StringBuffer sb = new StringBuffer(UUID_LEN * 2);
		long millis = System.currentTimeMillis();
		short hiTimeVersion = (short) ((millis >>> 48) & 0x0FFF);
		hiTimeVersion |= (1 << 12);
		int loTime = (int) millis;
		short midTime = (short) (millis >> 32 & 0xFFFF);
		m_clock++;
		//short rem = (short)(millis >>> 8);
		
		sb.append(Convert.intToHex(loTime));
		sb.append(SEP);
		sb.append(Convert.shortToHex(midTime));
		sb.append(Convert.shortToHex(hiTimeVersion));
		sb.append(SEP);
		sb.append(Convert.shortToHex((short)m_clock));
		sb.append(SEP);
		sb.append(Convert.intToHex(m_ip));
		sb.append(SEP);
		sb.append(Convert.shortToHex(m_filler));
		
		return(sb.toString());
		
	}
	
	/* (non-Javadoc)
	 * @see onjlib.db.IdGenerator#nextVal()
	 */
	public long nextVal() {
		// TODO Auto-generated method stub
		String s = nextStringVal();
		long l = Convert.bytesToLong(s.getBytes());
		return(l);
	}
	
	/*
	 * test driver
	 */
	public static void main(String[] args) {
		
		UuidGenerator uid = new UuidGenerator();
		
		for(int i=0;  i < 10; ++ i) {
			String s = uid.nextStringVal();
			System.out.println(s);
			long l = uid.nextVal();
			System.out.println(l);
			try {
				Thread.sleep(1000);
			} catch(Exception e){
				e.printStackTrace();
			}
		}
		
		
		
		
	}


	
}
