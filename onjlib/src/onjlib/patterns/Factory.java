package onjlib.patterns;

/**
 * <p>Title: Factory.java </p>
 * <p>Description: a class implementing the Factory design pattern.</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Oded Nissan
 * @version 1.0
 */

public abstract class Factory {

  /**
   * Create an object using a class factory.
   * @param c the Class to use for creating the Object.
   * @return Object the Object created.
   */
  public static Object getObject(Class c) throws Exception
  {
    Object o = c.newInstance();
    return(o);
  }

  /**
   * Create an object using a class factory.
   * @param className the name of the Class to use for creating the Object.
   * @return Object the Object created.
   */
  public static Object getObject(String className) throws Exception
  {
    Class c = Class.forName(className);
    Object o = c.newInstance();
    return(o);
  }
}