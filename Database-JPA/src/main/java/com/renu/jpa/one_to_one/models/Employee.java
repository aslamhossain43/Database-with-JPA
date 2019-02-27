package com.renu.jpa.one_to_one.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
@Entity
public class Employee extends TimeEntity<Long> {
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private Long id;
private String email;
private String firstName;
private String lastName;
@OneToOne(cascade=CascadeType.ALL)
private Account account;

public Employee() {}
@Override
public Long getId() {
	return id;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public void setId(Long id) {
	this.id = id;
}
public Account getAccount() {
	return account;
}
public void setAccount(Account account) {
	this.account = account;
}

}
