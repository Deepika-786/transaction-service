package com.pismo.transaction_service.controller;

import com.pismo.transaction_service.dto.AccountRequest;
import com.pismo.transaction_service.model.Account;
import com.pismo.transaction_service.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@Tag(name = "Accounts", description = "Operations related to accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Operation(summary = "Create a new account")
    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody AccountRequest accountRequest) {
        Account account = accountService.createAccount(accountRequest.getDocumentNumber());
        return ResponseEntity.ok(account);
    }

    @Operation(summary = "Get account by ID")
    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccount(@PathVariable Long accountId) {
        Account account = accountService.getAccountById(accountId);
        return ResponseEntity.ok(account);
    }
}
