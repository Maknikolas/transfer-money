package com.example.task.service;

import com.example.task.model.Account;
import com.example.task.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class AccountService {

//    @Autowired
//    private AccountRepository accountRepository;

    @Transactional
    public void updateAccountBalance(Account account, BigDecimal newBalance, AccountRepository accountRepository) {
        account.setBalance(newBalance);
        accountRepository.save(account);
    }
}