package com.example.task.controller;

import com.example.task.model.Transaction;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransferMoneyController {

//    @Autowired
//    RestTemplate restTemplate;


    @CrossOrigin
    @PostMapping("/transaction")
    String newReportCrime(@RequestBody Transaction transaction) {
        if (transaction.getId() != null) {
            System.out.println("TEST " + transaction.getId());
        }
        return "";
    }

}
