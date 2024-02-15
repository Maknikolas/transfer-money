package com.example.task.service;

import com.example.task.model.Account;
import com.example.task.model.Transaction;
import com.example.task.model.TransactionRequest;
import com.example.task.repository.AccountRepository;
import com.example.task.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class TransactionService {

    @Autowired
    public AccountRepository accountRepository;
    @Autowired
    public TransactionRepository transactionRepository;

    @Transactional
    public Transaction processTransaction(TransactionRequest request) {
        Account sourceAccount = accountRepository.findById(request.getSourceAccountId()).orElseThrow(() -> new RuntimeException("Source account not found"));
        Account targetAccount = accountRepository.findById(request.getTargetAccountId()).orElseThrow(() -> new RuntimeException("Target account not found"));

        if (sourceAccount.equals(targetAccount)) {
            throw new RuntimeException("Cannot transfer to the same account");
        }
        try {
            // Check if the currency is the same
            String currency = request.getCurrency();
            if (!sourceAccount.getCurrency().equals(currency)) {
                throw new RuntimeException("Currency of source account is " + sourceAccount.getCurrency() + ", while trying to transfer in " + currency);
            }
            if (!targetAccount.getCurrency().equals(currency)) {
                throw new RuntimeException("Currency of target account is " + targetAccount.getCurrency() + ", while trying to transfer in " + currency);
            }

            // Check if source account has sufficient balance
            BigDecimal transactionAmount = request.getAmount();
            if (sourceAccount.getBalance().compareTo(transactionAmount) < 0) {
                throw new RuntimeException("Insufficient balance in source account");
            }

            // Perform the transaction
            BigDecimal newSourceBalance = sourceAccount.getBalance().subtract(transactionAmount);
            BigDecimal newTargetBalance = targetAccount.getBalance().add(transactionAmount);

            // Update account balances
            AccountService accountService = new AccountService();
            accountService.updateAccountBalance(sourceAccount, newSourceBalance, accountRepository);
            accountService.updateAccountBalance(targetAccount, newTargetBalance, accountRepository);
            Transaction transaction = new Transaction(sourceAccount,targetAccount, transactionAmount, currency);
            transactionRepository.save(transaction);
            return transaction;
        } catch (Error e) {
            throw new Error(e);
        }
    }
}
