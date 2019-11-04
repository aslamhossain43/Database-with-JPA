package com.renu.jpa.one_to_one.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renu.jpa.one_to_one.models.Employee;
import com.renu.jpa.one_to_one.models.EmployeeAccountCombined;
import com.renu.jpa.one_to_one.repositories.AccountRepository;
import com.renu.jpa.one_to_one.repositories.EmployeeRepository;

@RestController
@RequestMapping(value="/oneToOne")
@CrossOrigin("*")
public class OneToOne {
private static final Logger LOGGER=LoggerFactory.getLogger(OneToOne.class);
//--------------------------------------------------------------------------------------------------------------------
@Autowired
EmployeeRepository employeeRepository;
//--------------------------------------------------------------------------------------------------------------------
@Autowired
AccountRepository accountRepository;

//--------------------------------------------------------------------------------------------------------------------
@RequestMapping(value="/add")
public ResponseEntity<?>add(@RequestBody EmployeeAccountCombined employeeAccountCombined){
	LOGGER.info(" From class OneToOne,method : add()---ENTER----- ");
	//MODEL ID OF YOUR CLIENT SIDE MUST BE NULL OTHERWISE ERROR AND MODEL AND ALL CLASS NAME SHOULD SAME AS CLIENT SIDE TO WORK EASILY
	employeeAccountCombined.getEmployee().setAccount(employeeAccountCombined.getAccount());
	employeeAccountCombined.getAccount().setEmployee(employeeAccountCombined.getEmployee());
	employeeRepository.save(employeeAccountCombined.getEmployee());
	return ResponseEntity.ok().body("--one-to-one-success--");
}
//--------------------------------------------------------------------------------------------------------------------
@RequestMapping(value="/get")
public ResponseEntity<?>get(){
	LOGGER.info(" From class OneToOne,method : get()---ENTER----- ");
	List<Employee>employees=employeeRepository.findAll();
	return ResponseEntity.ok().body(employees);
}
//--------------------------------------------------------------------------------------------------------------------
@RequestMapping(value="/get/{id}")
public ResponseEntity<?>getById(@PathVariable("id")Long id){
	LOGGER.info(" From class OneToOne,method : getById()---ENTER----- ");
Employee employee=employeeRepository.getById(id);
	
	return ResponseEntity.ok().body(employee);
}


//--------------------------------------------------------------------------------------------------------------------
@RequestMapping(value="/delete/{id}")
public ResponseEntity<?>get(@PathVariable("id")Long id){
	LOGGER.info(" From class OneToOne,method : deleteById()---ENTER----- ");
Employee employee=employeeRepository.getById(id);
	employeeRepository.delete(employee);
	
	
	return ResponseEntity.ok().body("DELETED--ID : "+id);
}












}
