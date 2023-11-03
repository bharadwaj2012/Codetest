package net.javaguides.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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

	@NotBlank
	@Column(name = "phone_number")
	private String phoneNumber;

	@NotBlank
	@Column(name = "joining_date")
	private String doj;

	@NotNull
	@Column(name = "monthly_salary")
	private double monthlySal;
	
	@Column(name = "tax_amount")
	private double tax;
	
	@Column(name = "cess_amount")
	private double cess;
	
	@Transient
	private Double annualSal;

	@PostLoad
	private void onLoad() {
	    this.annualSal = monthlySal*12;
	}
}
