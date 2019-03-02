package com.renu.jpa.one_to_many.controllers;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renu.jpa.one_to_many.models.AccountEntity;
import com.renu.jpa.one_to_many.models.EmployeeAccountCombinedEntity;
import com.renu.jpa.one_to_many.models.EmployeeEntity;
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
//------------------------------------------------------------------------------------------
	@RequestMapping(value = "/add")
	public ResponseEntity<?> add(@RequestBody EmployeeAccountCombinedEntity employeeAccountCombinedEntity) {
		LOGGER.info("From class OneToMany,method : add()---enter---");
		employeeAccountCombinedEntity.getEmployeeEntity().setAccounts(employeeAccountCombinedEntity.getAccountEntities());
		employeeAccountCombinedEntity.getAccountEntities().forEach(x->x.setEmployee(employeeAccountCombinedEntity.getEmployeeEntity()));
		employeeEntityRepository.save(employeeAccountCombinedEntity.getEmployeeEntity());
		return ResponseEntity.ok().body("--one-to-many-success--");
	}
	//----------------------------------------------------------------------------------------
	@RequestMapping(value = "/getAll")
	public ResponseEntity<?> getAll() {
		LOGGER.info("From class OneToMany,method : getAll()---enter---");
		List<EmployeeEntity>employeeEntities=employeeEntityRepository.findAll();
		
		return ResponseEntity.ok().body(employeeEntities);
	}
	//----------------------------------------------------------------------------------------
		@RequestMapping(value = "/get/{id}")
		public ResponseEntity<?> getById(@PathVariable("id")Long id) {
			LOGGER.info("From class OneToMany,method : getById()---enter---");
			EmployeeEntity employeeEntity=employeeEntityRepository.getById(id);
			
			return ResponseEntity.ok().body(employeeEntity);
		}
		//----------------------------------------------------------------------------------------
				@RequestMapping(value = "/delete/{id}")
				public ResponseEntity<?> deleteById(@PathVariable("id")Long id) {
					LOGGER.info("From class OneToMany,method : deleteById()---enter---");
					EmployeeEntity employeeEntity=employeeEntityRepository.getById(id);
					employeeEntityRepository.delete(employeeEntity);
					return ResponseEntity.ok().body("---DELETED ID : "+id);
				}
				
	
	

}
