package com.renu.jpa.one_to_one.models;

public class EmployeeAccountCombined {
private Employee employee;
private Account account;

public EmployeeAccountCombined() {}

public Employee getEmployee() {
	return employee;
}

public void setEmployee(Employee employee) {
	this.employee = employee;
}

public Account getAccount() {
	return account;
}

public void setAccount(Account account) {
	this.account = account;
}



}
