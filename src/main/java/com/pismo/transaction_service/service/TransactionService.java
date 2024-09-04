package com.pismo.transaction_service.service;

import com.pismo.transaction_service.model.Transaction;

public interface TransactionService {

    Transaction createTransaction(Long accountId, Long operationTypeId, Double amount);
}
