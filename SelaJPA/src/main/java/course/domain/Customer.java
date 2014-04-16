package course.domain;

import javax.persistence.*;
import java.util.*;


@Entity
public class Customer implements java.io.Serializable
{
   private int id;
   private String firstName;
   private String lastName;
   private boolean hasGoodCredit;

   private Address address;
   private Collection<Phone> phoneNumbers = new ArrayList<Phone>();
   private CreditCard creditCard;
   private Collection<Reservation> reservations = new ArrayList<Reservation>();

   @Id @GeneratedValue
   public int getId() { return id; }
   public void setId(int id) { this.id = id; }

   public String getFirstName() { return firstName; }
   public void setFirstName(String firstName) { this.firstName = firstName; }

   public String getLastName() { return lastName; }
   public void setLastName(String lastName) { this.lastName = lastName; }

   public boolean getHasGoodCredit() { return hasGoodCredit; }
   public void setHasGoodCredit(boolean flag) { hasGoodCredit = flag; }

   @OneToOne(cascade={CascadeType.ALL})
   @JoinColumn(name="ADDRESS_ID")
   public Address getAddress() { return address; }
   public void setAddress(Address address) { this.address = address; }

   @OneToOne(cascade={CascadeType.ALL})
   public CreditCard getCreditCard() { return creditCard; }
   public void setCreditCard(CreditCard card) { creditCard = card; }

   @OneToMany(cascade={CascadeType.ALL})
   @JoinColumn(name="CUSTOMER_ID")
   public Collection<Phone> getPhoneNumbers() { return phoneNumbers; }
   public void setPhoneNumbers(Collection<Phone> phones) { this.phoneNumbers = phones; }

   @ManyToMany(mappedBy="customers")
   public Collection<Reservation> getReservations() { return reservations; }
   public void setReservations(Collection<Reservation> reservations) { this.reservations = reservations; }
}
