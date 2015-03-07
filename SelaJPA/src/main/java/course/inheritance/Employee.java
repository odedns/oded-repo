package course.inheritance;

import javax.persistence.*;

@Entity
public class Employee extends CustomerI {
   private int employeeId;

   public int getEmployeeId() { return employeeId; }
   public void setEmployeeId(int id) { employeeId = id; }
}

