/**
 * Date: 07/06/2007
 * File: SingletonBean.java
 * Package: test.spring
 */
package test.spring;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author Oded
 *
 */
public class SingletonBean {
	private static final Logger log = Logger.getLogger(SingletonBean.class);
	@Autowired
	private TestBean bean;
	@Autowired
	private DataSource datasource;
	@Autowired 
	private TestDao dao;
	
	
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

	public DataSource getDatasource() {
		return datasource;
	}

	public void setDatasource(DataSource datasource) {
		this.datasource = datasource;
	}

	public TestDao getDao() {
		return dao;
	}

	public void setDao(TestDao dao) {
		this.dao = dao;
	}

}
