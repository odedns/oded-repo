package course.domain;

import javax.persistence.*;

@Entity
public class Phone implements java.io.Serializable
{
   private int id;
   private String number;
   private byte type;

   @Id @GeneratedValue
   public int getId() { return id; }
   public void setId(int id) { this.id = id; }

   public String getNumber() { return number; }
   public void setNumber(String number) { this.number = number; }

   public byte getType() { return type; }
   public void setType(byte type) { this.type = type; }
}
