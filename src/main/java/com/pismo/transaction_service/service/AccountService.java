package com.pismo.transaction_service.service;

import com.pismo.transaction_service.model.Account;

public interface AccountService {

    Account createAccount(String documentNumber);
    Account getAccountById(Long accountId);
}
