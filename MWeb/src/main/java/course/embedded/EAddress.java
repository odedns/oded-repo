package course.embedded;

import javax.persistence.*;

@Embeddable
public class EAddress implements java.io.Serializable {
   private String street;
   private String city;
   private String state;

   @Column(name="STREET")
   public String getStreet() { return street; }
   public void setStreet(String street) { this.street = street; }

   @Column(name="CITY")
   public String getCity() { return city; }
   public void setCity(String city) { this.city = city; }

   @Column(name="STATE")
   public String getState() { return state; }
   public void setState(String state) { this.state = state; }
}
