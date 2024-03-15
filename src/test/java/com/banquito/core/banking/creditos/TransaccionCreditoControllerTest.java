package com.banquito.core.banking.creditos;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.banquito.core.banking.creditos.controller.TransaccionCreditoController;
import com.banquito.core.banking.creditos.dao.TransaccionCreditoRepository;
import com.banquito.core.banking.creditos.domain.TransaccionCredito;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class TransaccionCreditoControllerTest {

    private MockMvc mockMvcTransaccionCredito;
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectwrittWriter = objectMapper.writer();

    @Mock
    private TransaccionCreditoRepository transaccionCreditoRepository;

    @InjectMocks
    private TransaccionCreditoController transaccionCreditoController;

    TransaccionCredito REGISTRO_1 = new TransaccionCredito();

    @BeforeEach

    public void setUp() {
        MockitoAnnotations.initMocks(this);
        transaccionCreditoController = new TransaccionCreditoController();
        this.mockMvcTransaccionCredito = MockMvcBuilders.standaloneSetup(transaccionCreditoController).build();
    }

    @Test
    public void obtenerTransaccionCredito_success() throws Exception {
        mockMvcTransaccionCredito.perform(MockMvcRequestBuilders
                .get("/api/v1/transaccionCredito/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }
}
