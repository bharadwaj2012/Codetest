package net.javaguides.springboot.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;
import net.javaguides.springboot.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	private EmployeeRepository employeeRepository;
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		Employee employeeSalCal= new  Employee();
		List<Employee> empList= new ArrayList<>();
		// Employee existingEmployee = employeeRepository.findById(id).orElseThrow( ()
		// -> new ResourceNotFoundException("Employee", "Id", id));
		 
		 employeeRepository.findAll().forEach((existingEmployee)-> {
		 
		 employeeSalCal.setEmployeeId(existingEmployee.getEmployeeId()); 
		 employeeSalCal.setFirstName(existingEmployee.getFirstName());
		 employeeSalCal.setLastName(existingEmployee.getLastName());
		 employeeSalCal.setEmail(existingEmployee.getEmail()); // save existing employee to
		 employeeSalCal.setTax(tax(existingEmployee.getAnnualSal()));
		 employeeSalCal.setAnnualSal(existingEmployee.getAnnualSal());
		 if(existingEmployee.getAnnualSal() > 2500000)
		 {
			 employeeSalCal.setCess(0.02*existingEmployee.getAnnualSal());
		 }
		 empList.add(employeeSalCal);
		 });
		 
		return empList;
		
		
	}
	
	private double tax(double income) {
		double tax = 0;
        double appIncome = 0;
         
        if (income <= 250000) {
            tax = 0;
        } else if (income >= 250001 && income <= 500000) {
            appIncome = income - 250000;
            tax = 0.05 * appIncome;
        } else if (income >= 500001 && income <= 1000000) {
            appIncome = income - 500000;
            tax = 12500 + (0.20 * appIncome);
        } else { // income > 1000000
            appIncome = income - 1000000;
            tax = 112500 + (0.30 * appIncome);
        }
         
        return tax;
	}
	
}
