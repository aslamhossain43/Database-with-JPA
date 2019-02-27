package com.renu.jpa.many_to_many.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.renu.jpa.many_to_many.models.Student;

public interface StudentRepository extends JpaRepository<Student, Long>,JpaSpecificationExecutor<Student>{

}
