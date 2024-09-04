package com.pismo.transaction_service.repository;

import com.pismo.transaction_service.model.OperationType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationTypeRepository extends JpaRepository<OperationType,Long> {
}
