package com.example.task.controller;

import com.example.task.model.Transaction;
import com.example.task.model.TransactionRequest;
import com.example.task.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransferMoneyController {

    @Autowired
    TransactionService transactionService; // Service for business logic

    @CrossOrigin
    @PostMapping("/transactions")
    public ResponseEntity<String> transfer(@RequestBody TransactionRequest request) {
        try {
            Transaction transaction = transactionService.processTransaction(request);
            return ResponseEntity.ok("Successful transfer");
        } catch (Error e) {
            // Could change to internal server error
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            // Could change to internal server error
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }


    }

}
