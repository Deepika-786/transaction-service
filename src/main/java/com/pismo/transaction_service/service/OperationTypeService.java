package com.pismo.transaction_service.service;

import com.pismo.transaction_service.model.OperationType;

public interface OperationTypeService {
    OperationType createOperationType(Long operationTypeId, String description);
    OperationType getOperationTypeById(Long operationTypeId);
}
