package com.renu.jpa.one_to_one.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.renu.jpa.one_to_one.models.Account;

public interface AccountRepository extends JpaRepository<Account, Long>,JpaSpecificationExecutor<Account> {

}
