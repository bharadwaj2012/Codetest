package code.test.employee.salary.model;


import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import code.test.employee.salary.impl.FiscalDate;
import lombok.Data;

@Data
@Entity
@Table(name="employees")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long employeeId;
	
	@NotBlank
	@Column(name = "first_name")
	private String firstName;
	
	@NotBlank
	@Column(name = "last_name")
	private String lastName;
	
	@NotBlank
	@Column(name = "email")
	private String email;

	@NotNull
	@Column(name = "phone_number")
	@ElementCollection(targetClass=String.class)
	private List<String> phoneNumber;

	@NotNull
	@Column(name = "joining_date")
	private Date doj;

	@NotNull
	@Column(name = "monthly_salary")
	private double monthlySal;
	
	@Transient
	private double tax;
	
	@Transient
	private double cess;
	
	@Transient
	private double annualSalary;

	
	 @PostLoad private void onLoad() {
	 
		 this.annualSalary= this.monthlySal*12;
	  }
	
	
}
