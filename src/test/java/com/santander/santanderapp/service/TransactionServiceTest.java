package com.santander.santanderapp.service;

import com.santander.santanderapp.client.UuidClient;
import com.santander.santanderapp.controller.dto.request.TransactionRequestData;
import com.santander.santanderapp.controller.dto.response.Transaction;
import com.santander.santanderapp.controller.dto.response.TransactionResponse;
import com.santander.santanderapp.repository.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {
    private TransactionService transactionService;
    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private UuidClient uuidClient;

    @BeforeEach
    void setup(){
        transactionService = new TransactionService(transactionRepository, uuidClient);
    }

    @Test
    void getAllTransactions() {
        //Given
        Transaction transaction= new Transaction("123","sender@gmail.com","receiver@gmail.com",new BigDecimal(1));
        List<Transaction> expectedTransactions = Arrays.asList(transaction);

        //When
        when(transactionRepository.findAll()).thenReturn(expectedTransactions);
       TransactionResponse response = transactionService.getAllTransactions();
        List<Transaction> actualTransactions= response.getData().getTransactions();

        //Then
        Assertions.assertEquals(actualTransactions,expectedTransactions);
    }

    @Test
    void testGetAllTransactions() {
        //Given
        String senderId = "sender@gmail.com";
        Transaction transaction= new Transaction("123","sender@gmail.com","receiver@gmail.com",new BigDecimal(31));
        List<Transaction> expectedTransactions = Arrays.asList(transaction);

        //When
        when(transactionRepository.findBysender(senderId)).thenReturn(expectedTransactions);
        TransactionResponse response = transactionService.getAllTransactions(senderId);
        List<Transaction> actualTransactions= response.getData().getTransactions();

        //Then
        Assertions.assertEquals(actualTransactions,expectedTransactions);
    }

    @Test
    void executeTransaction() {
        //Given
        String expectedId= "123";
        Transaction transaction= new Transaction(expectedId,"sender@gmail.com","receiver@gmail.com",new BigDecimal(31));
        TransactionRequestData transactionRequestData = new TransactionRequestData("sender@gmail.com","receiver@gmail.com",new BigDecimal(31));

        //When
        when(uuidClient.getTransactionId()).thenReturn(expectedId);
        when(transactionRepository.save(transaction)).thenReturn(transaction);
        String actualId = transactionService.executeTransaction( transactionRequestData);

        //Then
        Assertions.assertEquals(actualId,expectedId);
    }
}