package com.pismo.transaction_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pismo.transaction_service.dto.AccountRequest;
import com.pismo.transaction_service.model.Account;
import com.pismo.transaction_service.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateAccount_Success() throws Exception {
        AccountRequest request = new AccountRequest();
        request.setDocumentNumber("12345678900");

        Account account = new Account();
        account.setAccountId(1L);
        account.setDocumentNumber("12345678900");

        when(accountService.createAccount(anyString())).thenReturn(account);

        mockMvc.perform(post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountId").value(account.getAccountId()))
                .andExpect(jsonPath("$.documentNumber").value(account.getDocumentNumber()));
    }

    @Test
    public void testGetAccount_Success() throws Exception {
        Long accountId = 1L;
        Account account = new Account();
        account.setAccountId(accountId);
        account.setDocumentNumber("12345678900");

        when(accountService.getAccountById(accountId)).thenReturn(account);

        mockMvc.perform(get("/accounts/{accountId}", accountId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountId").value(accountId))
                .andExpect(jsonPath("$.documentNumber").value("12345678900"));
    }

    @Test
    public void testGetAccount_NotFound() throws Exception {
        Long accountId = 1L;

        when(accountService.getAccountById(accountId)).thenThrow(new com.pismo.transaction_service.exception.ResourceNotFoundException("Account not found for id: " + accountId));

        mockMvc.perform(get("/accounts/{accountId}", accountId))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Account not found for id: " + accountId));
    }
}

