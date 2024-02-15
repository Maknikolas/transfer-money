package com.example.task;

import com.example.task.model.Account;
import com.example.task.repository.AccountRepository;
import com.example.task.repository.TransactionRepository;
import com.example.task.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootApplication
public class TransferMoneyApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransferMoneyApplication.class, args);
		System.out.println("Hello world!");
	}
	public TransactionService transactionService(){
		return new TransactionService();
	}
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private TransactionRepository transactionRepository;
	@Bean
	public CommandLineRunner initializeDatabase() {
		return args -> {
			// Pre-populate your initial accounts here
			accountRepository.save(new Account(new BigDecimal("100.00"), "USD", LocalDateTime.now().toString()));
			accountRepository.save(new Account(new BigDecimal("200.00"), "USD", LocalDateTime.now().toString()));
			accountRepository.save(new Account(new BigDecimal("300.00"), "USD", LocalDateTime.now().toString()));
			// Add more accounts as needed
		};
	}

}
