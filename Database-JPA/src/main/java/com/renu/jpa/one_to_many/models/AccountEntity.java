package com.renu.jpa.one_to_many.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class AccountEntity extends TimeEntity<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String accountNumber;

	@ManyToOne
	@JsonIgnore
	private EmployeeEntity employee;

	public AccountEntity() {
	}

	
	

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public EmployeeEntity getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeEntity employee) {
		this.employee = employee;
	}




	public void setId(Long id) {
		this.id = id;
	}




	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return id;
	}

}
