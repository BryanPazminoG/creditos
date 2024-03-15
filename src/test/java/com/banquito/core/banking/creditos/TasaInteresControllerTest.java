package com.banquito.core.banking.creditos;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.banquito.core.banking.creditos.controller.TasaInteresController;
import com.banquito.core.banking.creditos.dao.TasaInteresRepository;
import com.banquito.core.banking.creditos.domain.TasaInteres;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@ExtendWith(MockitoExtension.class)
public class TasaInteresControllerTest {

    private MockMvc mockMvcTasaInteres;
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectwrittWriter = objectMapper.writer();

    @Mock
    private TasaInteresRepository tasaInteresRepository;

    @InjectMocks
    private TasaInteresController tasaInteresController;

    TasaInteres REGISTRO_1 = new TasaInteres();

    @BeforeEach

    public void setUp() {
        MockitoAnnotations.initMocks(this);
        tasaInteresController = new TasaInteresController();
        this.mockMvcTasaInteres = MockMvcBuilders.standaloneSetup(tasaInteresController).build();
    }

    @Test
    public void obtenerTasaInteres_success() throws Exception {
        List<TasaInteres> registros = new ArrayList<>();
        registros.add(REGISTRO_1);
        Mockito.lenient().when(tasaInteresRepository.findAll()).thenReturn(registros);
        mockMvcTasaInteres.perform(MockMvcRequestBuilders
                .get("/api/v1/tasaInteres")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

}
