package com.example.task.service;

import com.example.task.model.Account;
import com.example.task.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class AccountService {
    public void updateAccountBalance(Account account, BigDecimal newBalance, AccountRepository accountRepository) {
        account.setBalance(newBalance);
        accountRepository.save(account);
    }
}