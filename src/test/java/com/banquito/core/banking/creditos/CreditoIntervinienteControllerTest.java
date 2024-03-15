package com.banquito.core.banking.creditos;

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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.banquito.core.banking.creditos.controller.CreditoIntervinienteController;
import com.banquito.core.banking.creditos.dao.CreditoIntervinienteRepository;
import com.banquito.core.banking.creditos.domain.CreditoInterviniente;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@ExtendWith(MockitoExtension.class)
public class CreditoIntervinienteControllerTest {

    private MockMvc mockMvcCreditoInterviniente;
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectwrittWriter = objectMapper.writer();

    @Mock
    private CreditoIntervinienteRepository creditoIntervinienteRepository;

    @InjectMocks
    private CreditoIntervinienteController creditoIntervinienteController;

    CreditoInterviniente REGISTRO_1 = new CreditoInterviniente();

    @BeforeEach

    public void setUp() {
        MockitoAnnotations.initMocks(this);
        creditoIntervinienteController = new CreditoIntervinienteController();
        this.mockMvcCreditoInterviniente = MockMvcBuilders.standaloneSetup(creditoIntervinienteController).build();
    }

    @Test
    public void obtenerCreditoInterviniente_success() throws Exception {
        mockMvcCreditoInterviniente.perform(MockMvcRequestBuilders
                .get("/api/v1/intervinientes/1/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

}
