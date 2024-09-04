package com.pismo.transaction_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransactionRequest {

    @JsonProperty("account_id")
    private Long accountId;
    @JsonProperty("operation_type_id")
    private Long operationTypeId;

    private Double amount;

    // Getters and Setters
    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getOperationTypeId() {
        return operationTypeId;
    }

    public void setOperationTypeId(Long operationTypeId) {
        this.operationTypeId = operationTypeId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
