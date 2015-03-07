/**
 * 
 */
package com.gxtcookbook.code;

import java.util.List;

import com.gxtcookbook.code.server.JpaController;
import com.gxtcookbook.code.server.model.persisted.Student;

/**
 * @author I070659
 *
 */
public class TestJPA {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("creating JPA Controller..");
		
		JpaController<Long, Student> dao = new JpaController<Long, Student>() {};
		List<Student> list = dao.entities();
		System.out.println(list.toString());
		
	}

}
