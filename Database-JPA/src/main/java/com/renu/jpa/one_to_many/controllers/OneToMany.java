package com.renu.jpa.one_to_many.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renu.jpa.one_to_many.models.EmployeeAccountCombinedEntity;
import com.renu.jpa.one_to_many.repositories.AccountEntityRepository;
import com.renu.jpa.one_to_many.repositories.EmployeeEntityRepository;

@RestController
@RequestMapping(value = "/oneToMany")
@CrossOrigin("*")
public class OneToMany {
	private static final Logger LOGGER = LoggerFactory.getLogger(OneToMany.class);
	@Autowired
	EmployeeEntityRepository employeeEntityRepository;
	@Autowired
	AccountEntityRepository accountEntityRepository;

	@RequestMapping(value = "/add")
	public ResponseEntity<?> add(@RequestBody EmployeeAccountCombinedEntity employeeAccountCombinedEntity) {
		LOGGER.info("From class UsinForeignKeyAssociation,method : add()---enter---");
		employeeAccountCombinedEntity.getEmployeeEntity().setAccounts(employeeAccountCombinedEntity.getAccountEntities());
		employeeAccountCombinedEntity.getAccountEntities().forEach(x->x.setEmployee(employeeAccountCombinedEntity.getEmployeeEntity()));
		employeeEntityRepository.save(employeeAccountCombinedEntity.getEmployeeEntity());
		return ResponseEntity.ok().body("--one-to-many-success--");
	}
	
	
	

}
