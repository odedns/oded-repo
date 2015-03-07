package onjlib.patterns;

/**
 * <p>Title: Singleton</p>
 * <p>Description:  An abstract class implementing the Singleton
 * design pattern.
 * Users should subclass this class in order to implement their own singleton
 * objects. The creatInstance method should be implemented by
 * sub classes.
 * </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public abstract class Singleton {
  static Object m_instance = null;


  

  /**
   * create a singleton instance if it does
   * not already exist.
   * If it doesn't exist create the instance by calling
   * createInstance.
   * @param param the parameter to be passed to createInstance
   * for creating the instance.
   * @return Object the created Singleton object.
   */
  public Object getInstance(Object param) throws Exception
  {  	
    if(null == m_instance) {      	
      m_instance  = createInstance(param);

    }
    return(m_instance);
  }


  /**
   * create the singleton instance.
   * This method should be implemented by subclasses.
   */
  protected  abstract Object createInstance(Object param) throws Exception;
  

}