package com.milosnikolik.backendChallenge.Controller;

import com.milosnikolik.backendChallenge.Model.Transaction;

import com.milosnikolik.backendChallenge.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by User on 06.6.2018.
 */
@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> addTransaction(@RequestBody Transaction transaction){
        boolean result = transactionService.addTransaction(transaction);
        if (result) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
