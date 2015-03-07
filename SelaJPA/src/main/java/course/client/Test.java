/**
 * 
 */
package course.client;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


import course.domain.*;

/**
 * @author Oded Nissan
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
			// TODO Auto-generated method stub
		
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("CourseJPA");
			EntityManager em = emf.createEntityManager();
			initializeDatabase(em);
			em.getTransaction().begin();
			Student st = new Student();
			
			st.setId(10);
			st.setName("Jim");
			em.persist(st);
			
			st = em.find(Student.class,10);
			if(st != null) {
				System.out.println("found student 10");
				System.out.println("c = " + st.getName());
			} else {
				System.out.println("not found");
			}
			em.getTransaction().commit();
			em.close();
			emf.close();
			System.out.println("done....");
			
			
			

	}
	 public static void initializeDatabase(EntityManager manager)
	   {
	      List list = manager.createQuery("FROM Reservation res").getResultList();
	      if (list.size() > 0) 
	      {
	         System.out.println( "Database already initialized with entities");
	      }
	      

	      String output = null;
	    
	      
	      Customer bill = new Customer();
	      bill.setFirstName("Bill");
	      bill.setLastName("Burke");
	      bill.setHasGoodCredit(true);

	      Customer sacha = new Customer();
	      sacha.setFirstName("Sacha");
	      sacha.setLastName("Labourey");
	      sacha.setHasGoodCredit(false); // Sacha get's bad credit ;)

	      Customer marc = new Customer();
	      marc.setFirstName("Marc");
	      marc.setLastName("Fleury");
	      marc.setHasGoodCredit(true);

	      Address addr = new Address();
	      addr.setStreet("123 Boston Road");
	      addr.setCity("Billerica");
	      addr.setState("MA");
	      addr.setZip("01821");
	      bill.setAddress(addr);

	      addr = new Address();
	      addr.setStreet("Etwa Schweitzer Strasse");
	      addr.setCity("Neuchatel");
	      addr.setState("Switzerland");
	      addr.setZip("07711");
	      sacha.setAddress(addr);

	      addr = new Address();
	      addr.setStreet("JBoss Dr.");
	      addr.setState("Atlanta");
	      addr.setCity("GA");
	      addr.setZip("06660");
	      marc.setAddress(addr);

	      CreditCard cc;
	      cc = new CreditCard();
	      cc.setExpirationDate(new Date());
	      cc.setNumber("5324 9393 1010 2929");
	      cc.setNameOnCard("Bill Burke");
	      cc.setCreditOrganization("Capital One");
	      bill.setCreditCard(cc);

	      cc = new CreditCard();
	      cc.setExpirationDate(new Date());
	      cc.setNumber("3311 5000 1011 2333");
	      cc.setNameOnCard("Sacha Labourey");
	      cc.setCreditOrganization("American Express");
	      sacha.setCreditCard(cc);

	      cc = new CreditCard();
	      cc.setNumber("4310 5131 7711 2663");
	      cc.setNameOnCard("Marc Fleury");
	      cc.setCreditOrganization("MBNA");
	      marc.setCreditCard(cc);

	      System.out.println("added Bill Burke");
	      manager.persist(bill);
	      System.out.println("added Sacha Labourey");
	      manager.persist(sacha);
	      System.out.println("added Marc Fleury");
	      manager.persist(marc);

	      Ship queenMary = new Ship("Queen Mary", 40.0);
	      manager.persist(queenMary);
	      System.out.println("added Query Mary ship");
	      Ship titanic = new Ship("Titanic", 80.0);
	      manager.persist(titanic);
	      System.out.println("added Titanic ship");
	      
	      // Create cabins
	      Cabin cabin1 = new Cabin();
	      cabin1.setDeckLevel(1);
	      cabin1.setShip(queenMary);
	      cabin1.setBedCount(1);
	      cabin1.setName("Queen Cabin 1");
	      manager.persist(cabin1);
	      System.out.println("Added Queen Cabin 1 to Queen Mary");

	      Cabin cabin2 = new Cabin();
	      cabin2.setDeckLevel(1);
	      cabin2.setShip(queenMary);
	      cabin2.setBedCount(1);
	      cabin2.setName("Queen Cabin 2");
	      manager.persist(cabin2);
	      System.out.println("Added Queen Cabin 2 to Queen Mary");
	         
	      Cabin cabin3 = new Cabin();
	      cabin3.setDeckLevel(1);
	      cabin3.setShip(titanic);
	      cabin3.setBedCount(2);
	      cabin3.setName("Titanic Cabin 1");
	      manager.persist(cabin3);
	      System.out.println("Titanic Cabin 1 to Titanic");
	         
	      Cabin cabin4 = new Cabin();
	      cabin4.setDeckLevel(1);
	      cabin4.setShip(titanic);
	      cabin4.setBedCount(2);
	      cabin4.setName("Titanic Cabin 2");
	      manager.persist(cabin4);
	      System.out.println("Titanic Cabin 2 to Titanic");
	         
	      Cabin cabin5 = new Cabin();
	      cabin5.setDeckLevel(1);
	      cabin5.setShip(titanic);
	      cabin5.setBedCount(2);
	      cabin5.setName("Titanic Cabin 3");
	      manager.persist(cabin5);
	      System.out.println("Titanic Cabin 3 to Titanic");

	      // Create cruise
	      Cruise alaskan = new Cruise("Alaskan Cruise", queenMary);
	      manager.persist(alaskan);
	      System.out.println("added Alaskan Cruise on the Queen Mary");
	      Cruise atlantic = new Cruise("Atlantic Cruise", titanic);
	      manager.persist(atlantic);
	      System.out.println("added Atlantic Cruise on the Titanic");

	      Reservation alaskanReservation = new Reservation();
	      alaskanReservation.setCruise(alaskan);
	      alaskanReservation.getCabins().add(cabin1);
	      alaskanReservation.getCabins().add(cabin2);
	      alaskanReservation.getCustomers().add(bill);
	      alaskanReservation.getCustomers().add(sacha);
	      manager.persist(alaskanReservation);
	      System.out.println("Booked Bill and Sacha on Alaskan cruise in Cabin 1 and Cabin 2");
	      
	      Reservation atlanticReservation = new Reservation();
	      atlanticReservation.setCruise(atlantic);
	      atlanticReservation.getCabins().add(cabin3);
	      atlanticReservation.getCustomers().add(marc);
	      manager.persist(atlanticReservation);
	      System.out.println("Booked Marc in Cabin 1 on the Atlantic Cruise on the Titanic.  Say hi to Leo for us!");

	     
	   }

}
