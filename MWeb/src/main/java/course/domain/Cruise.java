package course.domain;

import java.util.*;
import javax.persistence.*;

@Entity
public class Cruise implements java.io.Serializable
{
   private int id;
   private String name;
   private Ship ship;
   private Collection<Reservation> reservations = new ArrayList<Reservation>();

   public Cruise() {}

   public Cruise(String name, Ship ship)
   {
      this.name = name;
      this.ship = ship;
   }

   @Id @GeneratedValue
   public int getId() { return id; }
   public void setId(int id) { this.id = id; }

   public String getName( ) { return name; }
   public void setName(String name) { this.name = name; }

   @ManyToOne
   public Ship getShip() { return ship; }
   public void setShip(Ship ship) { this.ship = ship; }

   @OneToMany(mappedBy="cruise")
   public Collection<Reservation> getReservations() { return reservations; }
   public void setReservations(Collection<Reservation> res) { reservations = res; }
}
