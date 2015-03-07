package course.domain;

import javax.persistence.*;

@Entity
public class Cabin implements java.io.Serializable
{
   private int id;
   private String name;
   private int bedCount;
   private int deckLevel;
   private Ship ship;

   @Id @GeneratedValue
   public int getId() { return id; }
   public void setId(int id) { this.id = id; }
 
   public String getName() { return name; }
   public void setName(String name) { this.name = name; }

   public int getBedCount() { return bedCount; }
   public void setBedCount(int count) { this.bedCount = count; }

   public int getDeckLevel() { return deckLevel; }
   public void setDeckLevel(int level) { this.deckLevel = level; }

   @ManyToOne
   @JoinColumn(name="SHIP_ID")
   public Ship getShip() { return ship; }
   public void setShip(Ship ship) { this.ship = ship; }
}
