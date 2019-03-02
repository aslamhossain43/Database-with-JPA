package com.renu.jpa.many_to_many.controllers;

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

import com.renu.jpa.many_to_many.models.Teacher;
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
		LOGGER.info("From class ManyToMany,method : addManyToMany()--ENTER----");
		teacherStudentCombined.getTeachers().forEach(x->x.setStudents(teacherStudentCombined.getStudents()));
		teacherRepository.saveAll(teacherStudentCombined.getTeachers());
		
		return ResponseEntity.ok().body("----many-to-many-added----");
	}
	//----------------------------------------------------------------------------------------------------------------
		@RequestMapping(value="/getAll")
		public ResponseEntity<?>getAll(){
			LOGGER.info("From class ManyToMany,method : getAll()--ENTER----");
			List<Teacher>teachers=teacherRepository.findAll();
			return ResponseEntity.ok().body(teachers);
		}
		//----------------------------------------------------------------------------------------------------------------
				@RequestMapping(value="/get/{id}")
				public ResponseEntity<?>getById(@PathVariable("id") Long id){
					LOGGER.info("From class ManyToMany,method : getById()--ENTER----");
					Teacher teachers=teacherRepository.getById(id);
					return ResponseEntity.ok().body(teachers);
				}
				//----------------------------------------------------------------------------------------------------------------
				@RequestMapping(value="/delete/{id}")
				public ResponseEntity<?>deleteById(@PathVariable("id") Long id){
					LOGGER.info("From class ManyToMany,method : deleteById()--ENTER----");
					Teacher teacher=teacherRepository.getById(id);
					teacherRepository.delete(teacher);
					return ResponseEntity.ok().body("---DELETED ID : "+id);
				}
				
	

}
