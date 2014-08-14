package course.embedded;

import javax.persistence.*;

@Entity
public class ECustomer implements java.io.Serializable {
   private long id;
   private String firstName;
   private String lastName;
   private EAddress address;

   @Id @GeneratedValue
   public long getId() { return id; }
   public void setId(long id) { this.id = id; }

   public String getFirstName() { return firstName; }
   public void setFirstName(String first) { this.firstName = first; }

   public String getLastName() { return lastName; }
   public void setLastName(String last) { this.lastName = last; }

   @Embedded
   @AttributeOverrides({
      @AttributeOverride(name="street", column=@Column(name="STREET")),
      @AttributeOverride(name="city", column=@Column(name="CITY")),
      @AttributeOverride(name="state", column=@Column(name="STATE"))
   })
   public EAddress getAddress() {
      return address;
   }
   public void setAddress(EAddress address) { this.address = address; }
}


 

    
    

    
