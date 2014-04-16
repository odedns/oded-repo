package jpa.exercise;

import java.util.LinkedList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import course.domain.Customer;
import course.domain.Phone;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("CourseJPA");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Emp emp = new Emp();
		emp.setId(100);
		emp.setFirstName("aaa");
		emp.setLastName("bbb");
		Dept dept = new Dept();
		dept.setId(1);
		dept.setName("CS");
		//dept.getEmps().add(emp);
		emp.setDep(dept);
		Emp emp1 = new Emp();
		emp1.setId(101);
		emp1.setFirstName("Bon");
		emp1.setLastName("Smith");
		emp1.setDep(dept);
		em.persist(emp);
		em.persist(emp1);
		em.persist(dept);

		
	
		em.getTransaction().commit();
		em.close();
		emf.close();
		System.out.println("done....");
		
	}

}
