package com.pismo.transaction_service.controller;

import com.pismo.transaction_service.exception.ResourceNotFoundException;
import com.pismo.transaction_service.model.OperationType;
import com.pismo.transaction_service.service.OperationTypeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OpeartionTypeController.class)
public class OperationTypeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OperationTypeService operationTypeService;

    @Test
    public void testGetOperationTypeById_Success() throws Exception {
        OperationType operationType = new OperationType();
        operationType.setOperationTypeId(1L);
        operationType.setDescription("Normal Purchase");

        when(operationTypeService.getOperationTypeById(anyLong())).thenReturn(operationType);

        mockMvc.perform(get("/operation-types/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.operationTypeId").value(1L))
                .andExpect(jsonPath("$.description").value("Normal Purchase"));
    }

    @Test
    public void testGetOperationTypeById_NotFound() throws Exception {
        when(operationTypeService.getOperationTypeById(anyLong()))
                .thenThrow(new ResourceNotFoundException("Operation Type not found"));

        mockMvc.perform(get("/operation-types/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
