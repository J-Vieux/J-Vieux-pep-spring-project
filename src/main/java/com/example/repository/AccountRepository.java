package com.example.repository;

import com.example.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


public interface AccountRepository extends JpaRepository<Account, Long>{

    @Query("FROM Account WHERE username = :user")
    Account findAccountByUsername(@Param("user") String username);
    
    @Query("FROM Account WHERE username = :user AND password = :pass")
    Account findAccountByUsernameAndPassword(@Param("user") String username, @Param("pass") String password);

    @Query("FROM Account WHERE accountId = :accountid")
    Account findAccount(@Param("accountid") int accountId);
}
