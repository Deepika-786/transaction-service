package com.pismo.transaction_service.service;

import com.pismo.transaction_service.exception.ResourceNotFoundException;
import com.pismo.transaction_service.model.OperationType;
import com.pismo.transaction_service.repository.OperationTypeRepository;
import com.pismo.transaction_service.service.impl.OperationTypeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class OperationTypeServiceTest {

    @InjectMocks
    private OperationTypeServiceImpl operationTypeService;

    @Mock
    private OperationTypeRepository operationTypeRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetOperationTypeById_Success() {
        OperationType operationType = new OperationType();
        operationType.setOperationTypeId(1L);
        operationType.setDescription("Normal Purchase");

        when(operationTypeRepository.findById(1L)).thenReturn(Optional.of(operationType));

        OperationType result = operationTypeService.getOperationTypeById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getOperationTypeId());
        assertEquals("Normal Purchase", result.getDescription());
    }

    @Test
    public void testGetOperationTypeById_NotFound() {
        when(operationTypeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            operationTypeService.getOperationTypeById(1L);
        });
    }
}
