package com.banquito.core.banking.creditos.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.banquito.core.banking.creditos.dto.InteresAcumuladoDTO;
import com.banquito.core.banking.creditos.service.InteresAcumuladoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("api/v1/interesAcumulado")
public class InteresAcumuladoController {
    private InteresAcumuladoService interesAcumuladoService;

    public InteresAcumuladoController(InteresAcumuladoService interesAcumuladoService) {
        this.interesAcumuladoService = interesAcumuladoService;
    }

    @GetMapping("{codInteresAcumulado}")
    public ResponseEntity<InteresAcumuladoDTO> obtenerPorId(@PathVariable("codInteresAcumulado") Integer codInteresAcumulado) {
        try {
            log.info("Obteniendo el interes acumulado por el ID: {}", codInteresAcumulado);
            InteresAcumuladoDTO interesAcumuladoDTO = interesAcumuladoService.obtenerPorId(codInteresAcumulado);
            return ResponseEntity.ok(interesAcumuladoDTO);
        } catch (Exception e) {
            log.error("Error al obtener el interes acumulado por el ID {}", codInteresAcumulado);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/creditos/{codCredito}")
    public ResponseEntity<List<InteresAcumuladoDTO>> obtenerPorCredito(@PathVariable("codCredito") Integer codCredito) {
        try {
            log.info("Obteniendo el interes acumulado por el ID credito: {}", codCredito);
            List<InteresAcumuladoDTO> listInteresAcumuladoDTO = interesAcumuladoService.listarPorCodigoCredito(codCredito);
            return ResponseEntity.ok(listInteresAcumuladoDTO);
        } catch (Exception e) {
            log.error("Error al obtener el interes acumulado por el ID credito {}", codCredito);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/estados/{estado}")
    public ResponseEntity<List<InteresAcumuladoDTO>> obtenerPorEstado(@PathVariable("estado") String estado) {
        try {
            log.info("Obteniendo el interes acumulado por el estado: {}", estado);
            List<InteresAcumuladoDTO> listInteresAcumuladoDTO = interesAcumuladoService.listarEstado(estado);
            return ResponseEntity.ok(listInteresAcumuladoDTO);
        } catch (Exception e) {
            log.error("Error al obtener el interes acumulado por el estado {}", estado);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<InteresAcumuladoDTO> crear(@RequestBody InteresAcumuladoDTO interesAcumuladoDTO) {
        try {
            log.info("Creando el interes acumulado: {}", interesAcumuladoDTO);
            return ResponseEntity.ok(interesAcumuladoService.crear(interesAcumuladoDTO));
        } catch (RuntimeException rte) {
            log.error("Error al crear el interes acumulado: ", rte);
            return ResponseEntity.badRequest().build();
        }
    }
}
