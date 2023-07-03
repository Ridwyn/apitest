package com.santander.santanderapp.controller;

import com.santander.santanderapp.controller.dto.request.TransactionRequestData;
import com.santander.santanderapp.controller.dto.response.TransactionResponse;
import com.santander.santanderapp.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
public class TransactionController {
    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping(value = "/api/transaction", produces = {"application/json"}, consumes = "application/json")
    public String executeTransaction(@Valid @RequestBody TransactionRequestData transactionRequestData) {

        return transactionService.executeTransaction(transactionRequestData);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/api/transactions", produces = {"application/json"})
    public TransactionResponse retrieveAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/api/transactions/{senderId}", produces = {"application/json"})
    public TransactionResponse retrieveAllTransactionsBySender(@PathVariable("senderId") String senderId) {
        return transactionService.getAllTransactions(senderId);
    }


}
