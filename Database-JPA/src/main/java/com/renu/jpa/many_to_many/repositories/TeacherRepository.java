package com.renu.jpa.many_to_many.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.renu.jpa.many_to_many.models.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long>,JpaSpecificationExecutor<Teacher> {
Teacher getById(Long id);
}
