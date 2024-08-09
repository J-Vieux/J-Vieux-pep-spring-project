package com.example.service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    AccountRepository accountRepository;
    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public boolean hasAccountName(String username){
        Account optionalName = accountRepository.findAccountByUsername(username);
        if(optionalName == null){
            return true;
        }
        return false;
    }

    public Account Register(Account account){
        if(account.getUsername() != "" && account.getPassword().length() > 3){
            System.out.println("xx");
            accountRepository.save(account);
            System.out.println(accountRepository.findAccountByUsername(account.getUsername()));
            return accountRepository.findAccountByUsername(account.getUsername());
            
        }
        return null;
    }

    public Account getAccount(Account account){
        Account user = accountRepository.findAccountByUsernameAndPassword(account.getUsername(), account.getPassword());
        System.out.println("x");
        System.out.println(user);
        if(user != null){
            System.out.println(user);
            return user;
        }
        return null;
    }
}
