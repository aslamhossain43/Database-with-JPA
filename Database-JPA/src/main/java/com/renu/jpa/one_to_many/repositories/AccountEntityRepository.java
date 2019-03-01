package com.renu.jpa.one_to_many.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.renu.jpa.one_to_many.models.AccountEntity;

public interface AccountEntityRepository extends JpaRepository<AccountEntity, Long>,JpaSpecificationExecutor<AccountEntity>{

}
