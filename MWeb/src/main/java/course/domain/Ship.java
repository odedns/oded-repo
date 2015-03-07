package course.domain;

import javax.persistence.*;

@Entity
public class Ship implements java.io.Serializable
{
   private int id;
   private String name;
   private double tonnage;

   public Ship() {}

   public Ship(String name, double tonnage)
   {
      this.name = name;
      this.tonnage = tonnage;
   }


   @Id @GeneratedValue
   public int getId() { return id; }
   public void setId(int id) { this.id = id; }

   public String getName() { return name; }
   public void setName(String name) { this.name = name; }

   public double getTonnage() { return tonnage; }
   public void setTonnage(double tonnage) { this.tonnage = tonnage; }
}
