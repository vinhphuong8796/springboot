package Main.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import Main.Entity.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	Iterable<Employee> findAll();
	
//	@Query("select e., s.name from Employee e")
//	List<Object> getSchoolIdAndName();
	List<Employee> findByEmpNo(String empNo);
	List<Employee> findByFullName(String fullName);
	
	@Query("select e from Employee e where e.empNo = ?1 and e.fullName= ?2")
	Page<Employee> findCustom(String id,String username,Pageable Pageable);
	
    @Query("SELECT e FROM Employee e")
    Page<Employee> GetAll(Pageable Pageable);
    
    
}
