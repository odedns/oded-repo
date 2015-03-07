package test.spring.data;

import org.springframework.data.repository.*;
import org.springframework.stereotype.Repository;
@Repository
public interface DepartmentRepository extends CrudRepository<Departments, String>{

}
