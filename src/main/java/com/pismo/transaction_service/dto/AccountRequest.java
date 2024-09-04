package com.pismo.transaction_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountRequest {

    public AccountRequest() {}

    @JsonProperty("document_number")
    private String documentNumber;

    // Getters and Setters
    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }
}
