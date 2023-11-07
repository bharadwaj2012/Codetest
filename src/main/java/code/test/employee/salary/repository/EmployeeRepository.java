package code.test.employee.salary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import code.test.employee.salary.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
