/*
 * Created on 12/01/2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package onjlib.db;

import java.util.Random;

/**
 * @author odedn
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TsIdGenerator implements IdGenerator {

	Random rnd;
	/**
	 * 
	 */
	public TsIdGenerator() {
		super();
		// TODO Auto-generated constructor stub
		long millis = System.currentTimeMillis();
		rnd = new Random(millis);
	}

	/* (non-Javadoc)
	 * @see onjlib.db.UIDGenerator#nextVal()
	 */
	public long nextVal() {
		// TODO Auto-generated method stub
		long millis = System.currentTimeMillis();
		int lowTime = (int) millis;
		int rand = this.rnd.nextInt();
		long val = (rand << 32) | lowTime;
		return(val >= 0 ? val : val * -1);
	}

}
