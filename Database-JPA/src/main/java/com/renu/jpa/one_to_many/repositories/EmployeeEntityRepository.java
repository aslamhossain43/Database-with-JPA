package com.renu.jpa.one_to_many.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.renu.jpa.one_to_many.models.EmployeeEntity;

public interface EmployeeEntityRepository extends JpaRepository<EmployeeEntity, Long>,JpaSpecificationExecutor<EmployeeEntity> {
EmployeeEntity getById(Long id);

}
