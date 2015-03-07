package course.domain;

import javax.persistence.*;

@Entity
public class Address implements java.io.Serializable
{
   private int id;
   private String street;
   private String city;
   private String state;
   private String zip;

   @Id @GeneratedValue
   public int getId() { return id;}
   public void setId(int id) { this.id = id; }

   public String getStreet() { return street; }
   public void setStreet(String street) { this.street = street; }

   public String getCity() { return city; }
   public void setCity(String city) { this.city = city; }

   public String getState() { return state; }
   public void setState(String state) { this.state = state; }

   public String getZip() { return zip; }
   public void setZip(String zip) { this.zip = zip; }
}
