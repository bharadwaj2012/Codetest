package code.test.employee.salary.service;

import java.util.List;

import code.test.employee.salary.model.Employee;

public interface EmployeeService {
	Employee saveEmployee(Employee employee);
	//List<Employee> getAllEmployees();
	
	Employee getAllEmployeesCurrentFinancialYearSal(long id);
}
