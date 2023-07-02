package com.santander.santanderapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.santander.santanderapp.controller.dto.request.TransactionRequestData;
import com.santander.santanderapp.controller.dto.response.TransactionResponse;
import com.santander.santanderapp.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
public class TransactionController {
    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/api/transaction")
    public String executeTransaction(@RequestBody TransactionRequestData transactionRequestData) {

        return transactionService.executeTransaction(transactionRequestData);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/transactions")
    public TransactionResponse retrieveAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/transactions/{senderId}")
    public TransactionResponse retrieveAllTransactionsBySender(@PathVariable("senderId") String senderId) {
        return transactionService.getAllTransactions(senderId);
    }


}
