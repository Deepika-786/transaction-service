package com.pismo.transaction_service.service;

import com.pismo.transaction_service.exception.ResourceNotFoundException;
import com.pismo.transaction_service.model.Account;
import com.pismo.transaction_service.model.OperationType;
import com.pismo.transaction_service.model.Transaction;
import com.pismo.transaction_service.repository.AccountRepository;
import com.pismo.transaction_service.repository.OperationTypeRepository;
import com.pismo.transaction_service.repository.TransactionRepository;
import com.pismo.transaction_service.service.impl.TransactionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

public class TransactionServiceTest {
    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private OperationTypeRepository operationTypeRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateTransaction_Success() {

        Account account = new Account();
        account.setAccountId(1L);
        account.setDocumentNumber("12345678900");

        OperationType operationType = new OperationType();
        operationType.setOperationTypeId(1L);
        operationType.setDescription("Normal Purchase");

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(operationTypeRepository.findById(1L)).thenReturn(Optional.of(operationType));

        Transaction transaction = new Transaction();
        transaction.setTransactionId(1L);
        transaction.setAccount(account);
        transaction.setOperationType(operationType);
        transaction.setAmount(-50.0);

        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        Transaction result = transactionService.createTransaction(1L, 1L, -50.0);

        assertNotNull(result);
        assertEquals(1L, result.getTransactionId());
        assertEquals(-50.0, result.getAmount());
        assertEquals(account, result.getAccount());
        assertEquals(operationType, result.getOperationType());
    }

    @Test
    public void testCreateTransaction_AccountNotFound() {
        when(accountRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            transactionService.createTransaction(1L, 1L, -50.0);
        });
    }

    @Test
    public void testCreateTransaction_OperationTypeNotFound() {
        Account account = new Account();
        account.setAccountId(1L);
        account.setDocumentNumber("12345678900");

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(operationTypeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            transactionService.createTransaction(1L, 1L, -50.0);
        });
    }
}
