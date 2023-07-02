package com.santander.santanderapp.service;

import com.santander.santanderapp.client.UuidClient;
import com.santander.santanderapp.controller.dto.request.TransactionRequestData;
import com.santander.santanderapp.controller.dto.response.Transaction;
import com.santander.santanderapp.controller.dto.response.TransactionResponse;
import com.santander.santanderapp.controller.dto.response.TransactionResponseData;
import com.santander.santanderapp.exception.DatabaseException;
import com.santander.santanderapp.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class TransactionService {

    private TransactionRepository transactionRepository;
    private UuidClient uuidClient;

    public TransactionService(TransactionRepository transactionRepository, UuidClient uuidClient){
        this.transactionRepository = transactionRepository;
        this.uuidClient= uuidClient;
    }
    public TransactionResponse getAllTransactions(){
       List<Transaction> transactions = transactionRepository.findAll();
       TransactionResponse transactionResponse= TransactionResponse.builder()
                .data(TransactionResponseData.builder()
                        .transactions(transactions)
                        .build())
                .build();
        return  transactionResponse;
    }
    public TransactionResponse getAllTransactions(String senderId){
        List<Transaction> transactions = transactionRepository.findBysender(senderId);
        TransactionResponse transactionResponse= TransactionResponse.builder()
                .data(TransactionResponseData.builder()
                        .transactions(transactions)
                        .build())
                .build();
        return  transactionResponse;
    }
    public String executeTransaction(TransactionRequestData transactionRequestData) {
        Transaction transaction = Transaction.builder()
                .id(uuidClient.getTransactionId())
                .amount(transactionRequestData.getAmount())
                .receiver(transactionRequestData.getReceiver())
                .sender(transactionRequestData.getSender())
                .build();
        try{
            Transaction savedTran = transactionRepository.save(transaction);
            return savedTran.getId();
        }catch (Exception e){
            log.info("Error saving to db");
            throw new DatabaseException(e);
        }

    }

}
