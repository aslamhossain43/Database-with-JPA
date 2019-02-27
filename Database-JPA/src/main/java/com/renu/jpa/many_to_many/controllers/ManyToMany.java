package com.renu.jpa.many_to_many.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renu.jpa.many_to_many.models.TeacherStudentCombined;
import com.renu.jpa.many_to_many.repositories.StudentRepository;
import com.renu.jpa.many_to_many.repositories.TeacherRepository;

@RestController
@RequestMapping(value = "/manyToMany")
@CrossOrigin("*")
public class ManyToMany {
	private static final Logger LOGGER = LoggerFactory.getLogger(ManyToMany.class);

	@Autowired
	TeacherRepository teacherRepository;
@Autowired
StudentRepository studentRepository;
//-----------------------------------------------------------------------------------------------------------------
	
	//----------------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/add")
	public ResponseEntity<?>addManyToMany(@RequestBody TeacherStudentCombined teacherStudentCombined){
		LOGGER.info("From class ManyToManyUsingEntity,method : addManyToMany()--ENTER----");
		teacherStudentCombined.getTeachers().forEach(x->x.setStudents(teacherStudentCombined.getStudents()));
		teacherRepository.saveAll(teacherStudentCombined.getTeachers());
		
		
		
		return ResponseEntity.ok().body("----many-to-many-added----");
	}
	
	

}
