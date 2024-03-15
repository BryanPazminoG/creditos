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

import com.banquito.core.banking.creditos.controller.InteresAcumuladoController;
import com.banquito.core.banking.creditos.dao.InteresAcumuladoRepository;
import com.banquito.core.banking.creditos.domain.InteresAcumulado;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@ExtendWith(MockitoExtension.class)
public class InteresAcumuladoControllerTest {

    private MockMvc mockMvcInteresAcumulado;
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectwrittWriter = objectMapper.writer();

    @Mock
    private InteresAcumuladoRepository interesAcumuladoRepository;

    @InjectMocks
    private InteresAcumuladoController interesAcumuladoController;

    InteresAcumulado REGISTRO_1 = new InteresAcumulado(1);

    @BeforeEach

    public void setUp() {
        MockitoAnnotations.initMocks(this);
        interesAcumuladoController = new InteresAcumuladoController();
        this.mockMvcInteresAcumulado = MockMvcBuilders.standaloneSetup(interesAcumuladoController).build();
    }

    @Test
    public void obtenerInteresAcumulado_success() throws Exception {
        mockMvcInteresAcumulado.perform(MockMvcRequestBuilders
                .get("/api/v1/interesAcumulado/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

}
