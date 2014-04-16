package onjlib.patterns;

/**
 * <p>Title: Abstract Factory.java </p>
 * <p>Description: a class implementing the Abstract Factory design pattern.</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Oded Nissan
 * @version 1.0
 */

public abstract class AbstractFactory {

  /**
   * Create a Factory using an abstract class factory.
   * @param c the Class to use for creating the Factory.
   * @return Object the Object created.
   */
  public static Factory getFactory(Class c) throws Exception
  {
    Factory o = (Factory)c.newInstance();
    return(o);
  }

  /**
   * Create a Factory using an abstract class factory.
   * @param className the name of the Class to use for creating the Factory.
   * @return Object the Object created.
   */
  public static Object getObject(String className) throws Exception
  {
    Class c = Class.forName(className);
    Factory o = (Factory) c.newInstance();
    return(o);
  }
}
