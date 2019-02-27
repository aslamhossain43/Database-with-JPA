package com.renu.jpa.one_to_many.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.renu.jpa.one_to_many.models.AccountEntity;

public interface AccountEntityRepository extends JpaRepository<AccountEntity, Long>,JpaSpecificationExecutor<AccountEntity>{

}
