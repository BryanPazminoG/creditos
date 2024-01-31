package com.banquito.core.banking.creditos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.banking.creditos.domain.CreditoInterviniente;
import com.banquito.core.banking.creditos.service.CreditoIntervinienteService;

import jakarta.websocket.server.PathParam;
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

    @GetMapping("{idCredito}/{identificacion}")
    public ResponseEntity<CreditoInterviniente> obtenerPorId(@PathParam("idCredito") Integer idCredito,
            @PathParam("identificacion") String identificacion) {
        log.info("Obteniendo interviniente con el credito {} y la identificacion {}", identificacion, idCredito);

        return creditoIntervinienteService.getById(idCredito, identificacion)
                .map(register -> ResponseEntity.ok(register))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CreditoInterviniente> save(@RequestBody CreditoInterviniente creditoInterviniente) {
        try {
            log.info("Guardando nuevo registro creditoInterviniente: {}", creditoInterviniente);
            return ResponseEntity.ok(creditoIntervinienteService.create(creditoInterviniente));
        } catch (RuntimeException rte) {
            log.error("Error al crear el nuevo registro: ", rte);
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<CreditoInterviniente> update(@RequestBody CreditoInterviniente creditoInterviniente) {
        try {
            log.info("Actualizando el registro creditoInterviniente: {}", creditoInterviniente);
            return ResponseEntity.ok(creditoIntervinienteService.update(creditoInterviniente));

        } catch (RuntimeException rte) {
            log.error("Error al actualizar el registro:", rte);
            return ResponseEntity.notFound().build();
        }
    }
}
