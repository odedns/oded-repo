/**
 * File: TestDao.java
 * Date: Aug 5, 2014
 * Author: I070659
 */
package test;

import org.eclipse.persistence.jpa.jpql.Assert.AssertException;
import org.junit.Test;
import org.oded.jpa.StudentDao;

import course.client.Student;

/**
 * @author I070659
 *
 */
public class TestDao {

	@Test
	public void testDao() {
		
		StudentDao dao = new StudentDao();
		Student st = new Student();
		st.setId(1002);
		st.setName("Joe Cool");
		dao.persist(st);
		Student st2 = dao.findById(1002);
		System.out.println("st2 = " + st2.getName());
		assert(1002 == st2.getId());
		dao.close();
		assert(null != dao);
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
