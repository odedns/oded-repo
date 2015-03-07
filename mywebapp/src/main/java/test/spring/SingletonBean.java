/**
 * Date: 07/06/2007
 * File: SingletonBean.java
 * Package: test.spring
 */
package test.spring;

import org.apache.log4j.Logger;

/**
 * @author a73552
 *
 */
public class SingletonBean {
	private static final Logger log = Logger.getLogger(SingletonBean.class);
	private TestBean bean;
	
	public void run()
	{
		log.debug("run()");
	}

	public TestBean getBean() {
		return bean;
	}

	public void setBean(TestBean bean) {
		this.bean = bean;
	}

}
