package com.banquito.core.banking.creditos;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.banquito.core.banking.creditos.controller.TipoCreditoController;
import com.banquito.core.banking.creditos.dao.TipoCreditoRepository;
import com.banquito.core.banking.creditos.domain.TipoCredito;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@ExtendWith(MockitoExtension.class)

public class TipoCreditoControllerTest {

    private MockMvc mockMvcTipoCredito;
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectwrittWriter = objectMapper.writer();

    @Mock
    private TipoCreditoRepository tipoCreditoRepository;

    @InjectMocks
    private TipoCreditoController tipoCreditoController;

    TipoCredito REGISTRO_1 = new TipoCredito();

    @BeforeEach

    public void setUp() {
        MockitoAnnotations.initMocks(this);
        tipoCreditoController = new TipoCreditoController();
        this.mockMvcTipoCredito = MockMvcBuilders.standaloneSetup(tipoCreditoController).build();
    }

    @Test
    public void obtenerTipoCredito_success() throws Exception {
        mockMvcTipoCredito.perform(MockMvcRequestBuilders
                .get("/api/v1/tipoCreditos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

}
