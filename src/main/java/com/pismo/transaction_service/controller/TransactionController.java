package com.pismo.transaction_service.controller;

import com.pismo.transaction_service.dto.TransactionRequest;
import com.pismo.transaction_service.model.Transaction;
import com.pismo.transaction_service.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionRequest transactionRequest) {
        Transaction transaction = transactionService.createTransaction(
                transactionRequest.getAccountId(),
                transactionRequest.getOperationTypeId(),
                transactionRequest.getAmount()
        );
        return ResponseEntity.ok(transaction);
    }
}
