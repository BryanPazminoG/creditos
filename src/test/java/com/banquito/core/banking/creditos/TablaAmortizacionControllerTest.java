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
import com.banquito.core.banking.creditos.controller.TablaAmortizacionController;
import com.banquito.core.banking.creditos.dao.CreditoIntervinienteRepository;
import com.banquito.core.banking.creditos.dao.TablaAmortizacionRepository;
import com.banquito.core.banking.creditos.domain.CreditoInterviniente;
import com.banquito.core.banking.creditos.domain.TablaAmortizacion;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@ExtendWith(MockitoExtension.class)
public class TablaAmortizacionControllerTest {

    private MockMvc mockMvcTablaAmortizacion;
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectwrittWriter = objectMapper.writer();

    @Mock
    private TablaAmortizacionRepository tablaAmortizacionRepository;

    @InjectMocks
    private TablaAmortizacionController tablaAmortizacionController;

    TablaAmortizacion REGISTRO_1 = new TablaAmortizacion();

    @BeforeEach

    public void setUp() {
        MockitoAnnotations.initMocks(this);
        tablaAmortizacionController = new TablaAmortizacionController();
        this.mockMvcTablaAmortizacion = MockMvcBuilders.standaloneSetup(tablaAmortizacionController).build();
    }

    @Test
    public void obtenerTablaAmortizacion_success() throws Exception {
        mockMvcTablaAmortizacion.perform(MockMvcRequestBuilders
                .get("/api/v1/tablaAmortizacion/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

}
