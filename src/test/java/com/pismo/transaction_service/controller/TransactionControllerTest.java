package com.pismo.transaction_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pismo.transaction_service.dto.TransactionRequest;
import com.pismo.transaction_service.exception.ResourceNotFoundException;
import com.pismo.transaction_service.model.Transaction;
import com.pismo.transaction_service.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateTransaction_Success() throws Exception {
        TransactionRequest request = new TransactionRequest();
        request.setAccountId(1L);
        request.setOperationTypeId(1L);
        request.setAmount(-50.0);

        Transaction transaction = new Transaction();
        transaction.setTransactionId(1L);
        transaction.setAmount(-50.0);

        when(transactionService.createTransaction(anyLong(), anyLong(), anyDouble())).thenReturn(transaction);

        mockMvc.perform(post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionId").value(1L))
                .andExpect(jsonPath("$.amount").value(-50.0));
    }

    @Test
    public void testCreateTransaction_AccountNotFound() throws Exception {
        TransactionRequest request = new TransactionRequest();
        request.setAccountId(1L);
        request.setOperationTypeId(1L);
        request.setAmount(-50.0);

        when(transactionService.createTransaction(anyLong(), anyLong(), anyDouble()))
                .thenThrow(new ResourceNotFoundException("Account not found"));

        mockMvc.perform(post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateTransaction_OperationTypeNotFound() throws Exception {
        TransactionRequest request = new TransactionRequest();
        request.setAccountId(1L);
        request.setOperationTypeId(1L);
        request.setAmount(-50.0);

        when(transactionService.createTransaction(anyLong(), anyLong(), anyDouble()))
                .thenThrow(new ResourceNotFoundException("Operation Type not found"));

        mockMvc.perform(post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound());
    }
}
