/**
 * 
 */
package course.client;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;



import course.domain.*;

/**
 * @author Oded Nissan
 *
 */
public class TestCust {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
			// TODO Auto-generated method stub
		
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("CourseJPA");
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			Customer c = new Customer();
			c.setFirstName("Bob");
			c.setLastName("Lee");
			c.setHasGoodCredit(true);
			
			Phone phone = new Phone();
			phone.setNumber("9999999");
			phone.setType((byte)1);
			LinkedList<Phone> phones = new LinkedList<Phone>();
			phones.add(phone);
			c.setPhoneNumbers(phones);
			
			em.persist(c);
			em.persist(phone);
			
			em.getTransaction().commit();
			em.close();
			emf.close();
			System.out.println("done....");
			
			
			

	}

}
