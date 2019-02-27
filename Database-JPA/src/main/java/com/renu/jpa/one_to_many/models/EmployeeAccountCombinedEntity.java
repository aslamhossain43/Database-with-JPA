package com.renu.jpa.one_to_many.models;

import java.util.Set;

public class EmployeeAccountCombinedEntity {
private EmployeeEntity employeeEntity;
private Set<AccountEntity>accountEntities;
public EmployeeAccountCombinedEntity() {}
public EmployeeEntity getEmployeeEntity() {
	return employeeEntity;
}
public void setEmployeeEntity(EmployeeEntity employeeEntity) {
	this.employeeEntity = employeeEntity;
}
public Set<AccountEntity> getAccountEntities() {
	return accountEntities;
}
public void setAccountEntities(Set<AccountEntity> accountEntities) {
	this.accountEntities = accountEntities;
}




}
