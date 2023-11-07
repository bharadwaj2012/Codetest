package code.test.employee.salary.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import code.test.employee.salary.exception.ResourceNotFoundException;
import code.test.employee.salary.model.Employee;
import code.test.employee.salary.repository.EmployeeRepository;
import code.test.employee.salary.service.EmployeeService;

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

	/*@Override
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
		
		
	}*/
	
	@Override
	public Employee getAllEmployeesCurrentFinancialYearSal(long id) {
		
		Employee employeeSalCal= new  Employee();
		Employee existingEmployee = employeeRepository.findById(id).orElseThrow( ()
		-> new ResourceNotFoundException("Employee", "Id", id));
		 
		 
			 //if (getActualFinancialYear(existingEmployee.getDoj())) {
		 employeeSalCal.setEmployeeId(existingEmployee.getEmployeeId()); 
		 employeeSalCal.setFirstName(existingEmployee.getFirstName());
		 employeeSalCal.setLastName(existingEmployee.getLastName());
		 employeeSalCal.setEmail(existingEmployee.getEmail()); // save existing employee to
		 Calendar calendar = Calendar.getInstance();
			 calendar.setTime(existingEmployee.getDoj());
			 FiscalDate fiscalDate = new FiscalDate(calendar);
		        int year = fiscalDate.getFiscalYear();
		        //double sal=fiscalDate.getFiscalMonth()*existingEmployee.getMonthlySal();
		        System.out.println("Current Date : " + calendar.getTime().toString());
		        System.out.println("Fiscal Years : " + year + "-" + (year + 1));
		        System.out.println("Fiscal Month : " + fiscalDate.getFiscalMonth());
		        System.out.println(" ");
		        employeeSalCal.setAnnualSalary((existingEmployee.getAnnualSalary())-(lossOfPay(existingEmployee.getMonthlySal(), fiscalDate.getDays())));
		 employeeSalCal.setTax(tax(employeeSalCal.getAnnualSalary()));
		 if(employeeSalCal.getAnnualSalary() > 2500000)
		 {
			 employeeSalCal.setCess(0.02*existingEmployee.getAnnualSalary());
		 }
		 
		return employeeSalCal;
		
		
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
            tax = 12500 + (0.10 * appIncome);
        } else { // income > 1000000
            appIncome = income - 1000000;
            tax = 112500 + (0.20 * appIncome);
        }
         
        return tax;
	}
	
	
	private double lossOfPay(double sal2,int doj) {
		
		double perDay = sal2/30;
		double lossOfPay= perDay*doj;
		System.out.println("loss of pay-->"+lossOfPay);
		return lossOfPay;
	}
	
	public static void main(String[] args) throws ParseException {
		
		 SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		 Date givenDate=formatter.parse("11-04-2023");
	        LocalDate localDate1 = givenDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	        int year = localDate1.getYear();
	        String fiscaldate = "31-03-" + year + " 23:59:59";
	        Date fiscalDate = formatter.parse(fiscaldate);
	        
	        if (givenDate.before(fiscalDate)) {
	            year = year - 1;
	            
	        }
		System.out.println(year);
	}
	
}
