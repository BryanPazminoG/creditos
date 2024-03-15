package com.banquito.core.banking.creditos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import com.banquito.core.banking.creditos.controller.CreditoController;
import com.banquito.core.banking.creditos.dao.CreditoRepository;
import com.banquito.core.banking.creditos.domain.Credito;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@ExtendWith(MockitoExtension.class)
public class CreditoControllerTest {

    private MockMvc mockMvcCredito;
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectwrittWriter = objectMapper.writer();

    @Mock
    private CreditoRepository creditoRepository;

    @InjectMocks
    private CreditoController creditoController;

    Credito REGISTRO_1 = new Credito(1);

    @BeforeEach

    public void setUp() {
        MockitoAnnotations.initMocks(this);
        creditoController = new CreditoController();
        this.mockMvcCredito = MockMvcBuilders.standaloneSetup(creditoController).build();
    }

    @Test
    public void obtenerCredito_success() throws Exception {
        mockMvcCredito.perform(MockMvcRequestBuilders
        .get("http://localhost:8080/api/v1/creditos/1")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());


    }

}

