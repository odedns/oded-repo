package course.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class CreditCard implements java.io.Serializable
{
   @Id @GeneratedValue
   private int id;
   @Temporal(TemporalType.DATE)
   private Date expirationDate;
   private String number;
   private String nameOnCard;
   private String creditOrganization;
   private Customer customer;

 
   public int getId() { return id; }
   public void setId(int id) { this.id = id; }

   public Date getExpirationDate() { return expirationDate; }
   public void setExpirationDate(Date date) { expirationDate = date; }

   public String getNumber() { return number; }
   public void setNumber(String number) { this.number = number; }

   public String getNameOnCard() { return nameOnCard; }
   public void setNameOnCard(String name) { nameOnCard = name; }

   public String getCreditOrganization() { return creditOrganization; }
   public void setCreditOrganization(String org) { creditOrganization = org; }

   @OneToOne(mappedBy="creditCard")
   public Customer getCustomer() { return customer; }
   public void setCustomer(Customer customer) { this.customer = customer; }
}
