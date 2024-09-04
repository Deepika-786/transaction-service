package com.pismo.transaction_service.service;

import com.pismo.transaction_service.exception.ResourceNotFoundException;
import com.pismo.transaction_service.model.Account;
import com.pismo.transaction_service.repository.AccountRepository;
import com.pismo.transaction_service.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AccountServiceTest {

    @InjectMocks
    private AccountServiceImpl accountService;

    @Mock
    private AccountRepository accountRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateAccount_Success() {
        String documentNumber = "12345678900";
        Account account = new Account();
        account.setAccountId(1L);
        account.setDocumentNumber(documentNumber);

        when(accountRepository.save(any(Account.class))).thenReturn(account);

        Account createdAccount = accountService.createAccount(documentNumber);

        assertNotNull(createdAccount);
        assertEquals(1L, createdAccount.getAccountId());
        assertEquals(documentNumber, createdAccount.getDocumentNumber());
        verify(accountRepository, times(1)).save(any(Account.class));
    }

    @Test
    public void testGetAccountById_Success() {
        Long accountId = 1L;
        Account account = new Account();
        account.setAccountId(accountId);
        account.setDocumentNumber("12345678900");

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));

        Account foundAccount = accountService.getAccountById(accountId);

        assertNotNull(foundAccount);
        assertEquals(accountId, foundAccount.getAccountId());
        verify(accountRepository, times(1)).findById(accountId);
    }

    @Test
    public void testGetAccountById_NotFound() {
        Long accountId = 1L;

        when(accountRepository.findById(accountId)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            accountService.getAccountById(accountId);
        });

        assertEquals("Account not found for id: " + accountId, exception.getMessage());
        verify(accountRepository, times(1)).findById(accountId);
    }
}

