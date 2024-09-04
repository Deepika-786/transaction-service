package com.pismo.transaction_service.controller;

import com.pismo.transaction_service.dto.OperationTypeRequest;
import com.pismo.transaction_service.model.OperationType;
import com.pismo.transaction_service.service.OperationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/operation-types")
public class OpeartionTypeController {
    @Autowired
    private OperationTypeService operationService;

    @PostMapping
    public ResponseEntity<OperationType> createOperation(@RequestBody OperationTypeRequest operationTypeRequest) {
        OperationType operationType = operationService.createOperationType(operationTypeRequest.getOperationTypeId(),operationTypeRequest.getDescription());
        return ResponseEntity.ok(operationType);
    }

    @GetMapping("/{operationTypeId}")
    public ResponseEntity<OperationType> getOperationType(@PathVariable Long operationTypeId) {
        OperationType operationType = operationService.getOperationTypeById(operationTypeId);
        return ResponseEntity.ok(operationType);
    }
}
