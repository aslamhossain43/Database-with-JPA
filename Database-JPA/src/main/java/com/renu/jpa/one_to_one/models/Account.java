package com.renu.jpa.one_to_one.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Account extends TimeEntity<Long> {
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private Long id;
private String accountNumber;
@OneToOne(mappedBy="account")
private Employee employee;
public Account() {}
@Override
public Long getId() {
	return id;
}
public String getAccountNumber() {
	return accountNumber;
}
public void setAccountNumber(String accountNumber) {
	this.accountNumber = accountNumber;
}
public void setId(Long id) {
	this.id = id;
}
public Employee getEmployee() {
	return employee;
}
public void setEmployee(Employee employee) {
	this.employee = employee;
}



}
