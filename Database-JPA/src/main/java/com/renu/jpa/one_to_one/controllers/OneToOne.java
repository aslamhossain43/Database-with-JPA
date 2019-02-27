package com.renu.jpa.one_to_one.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	employeeAccountCombined.getEmployee().setAccount(employeeAccountCombined.getAccount());
	employeeAccountCombined.getAccount().setEmployee(employeeAccountCombined.getEmployee());
	employeeRepository.save(employeeAccountCombined.getEmployee());
	return ResponseEntity.ok().body("--one-to-one-success--");
}


}
