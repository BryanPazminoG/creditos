package com.banquito.core.banking.creditos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.banking.creditos.dto.CreditoIntervinienteDTO;
import com.banquito.core.banking.creditos.service.CreditoIntervinienteService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("api/v1/intervinientes")
public class CreditoIntervinienteController {

    private CreditoIntervinienteService creditoIntervinienteService;

    public CreditoIntervinienteController(CreditoIntervinienteService creditoIntervinienteService) {
        this.creditoIntervinienteService = creditoIntervinienteService;
    }

    @GetMapping("{credito}/{cliente}")
    public ResponseEntity<CreditoIntervinienteDTO> obtenerPorId(@PathVariable("credito") Integer credito,
            @PathVariable("cliente") String cliente) {
        try {
            log.info("Obteniendo interviniente con el credito {} y el cliente {}", credito, cliente);
            CreditoIntervinienteDTO dto = creditoIntervinienteService.obtenerPorId(credito, cliente);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            log.error("No se encontro el credito interviniente {} - {}: ", credito, cliente);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CreditoIntervinienteDTO> crear(@RequestBody CreditoIntervinienteDTO creditoInterviniente) {
        try {
            log.info("Guardando nuevo registro creditoInterviniente: {}", creditoInterviniente);
            return ResponseEntity.ok(creditoIntervinienteService.crear(creditoInterviniente));
        } catch (RuntimeException rte) {
            log.error("Error al crear el nuevo registro: ", rte);
            return ResponseEntity.notFound().build();
        }
    }
}
