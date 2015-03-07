package onjlib.utils;

/**
 * Title: GQueue.java
 * Description:
 * A Queue implementation.
 * This class extends the DQueue class and extracts items using
 * a FIFO method.
 * The class can be either synchronized or non-synchronized, a sycnhronized
 * class uses a synchronized List as the underlying data structure.
 *
 * <p>Copyright (c) 2002
 * @author  Oded Nissan
 * @version 1.0 14/3/2002
 */

public class GQueue extends GDQueue {
	/**
	 * Constructor specifies if the implementation is going to be
	 * synchronized or not.
	 * @param synch a boolean that specifies if the object is sycnhronized.
	 */
	public GQueue(boolean synch)
	{
		super(synch);
	}

	/**
	 * Default constructor.
	 * This creates a non-synchornized object.
	 */
	public GQueue()
	{
		super();
	}

	/**
	 * Pop an object from the front of the queue - FIFO.
	 * @return Object the returned object or null.
	 */
	public Object pop()
	{
		return(super.popFront());
	}

	public Object peek()
	{
		return(super.peekFront());
	}

}
