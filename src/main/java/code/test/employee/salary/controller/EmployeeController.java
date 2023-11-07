package code.test.employee.salary.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import code.test.employee.salary.model.Employee;
import code.test.employee.salary.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	
	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}
	
	// build create employee REST API
	@PostMapping()
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
		return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
	}
	
	// build get all employees REST API
	/*@GetMapping
	public List<Employee> getAllEmployees(){
		return employeeService.getAllEmployees();
	}*/
	
	// build get all employees REST API getAllEmployeesCurrentFinancialYearSal
		@GetMapping("/{id}")
		public ResponseEntity<Employee> getAllEmployeesCurrentFinancialYearSal(@PathVariable("id") long id){
			return new ResponseEntity<Employee>(employeeService.getAllEmployeesCurrentFinancialYearSal(id), HttpStatus.OK);
		}
	
}
