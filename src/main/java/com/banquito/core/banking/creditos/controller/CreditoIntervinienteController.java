package com.banquito.core.banking.creditos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.banking.creditos.dto.CreditoIntervinienteDTO;
import com.banquito.core.banking.creditos.service.CreditoIntervinienteService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//@CrossOrigin
@CrossOrigin(origins = "", allowedHeaders = "", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
@RestController
@RequestMapping("api/v1/intervinientes")
public class CreditoIntervinienteController {

    private CreditoIntervinienteService creditoIntervinienteService;

    public CreditoIntervinienteController(CreditoIntervinienteService creditoIntervinienteService) {
        this.creditoIntervinienteService = creditoIntervinienteService;
    }

    @GetMapping("{idCredito}/{identificacion}")
    public ResponseEntity<CreditoIntervinienteDTO> obtenerPorId(@PathVariable("idCredito") Integer idCredito,
            @PathVariable("identificacion") String identificacion) {
        try {
            log.info("Obteniendo interviniente con el credito {} y la identificacion {}", identificacion, idCredito);
            CreditoIntervinienteDTO dto = creditoIntervinienteService.obtenerPorId(idCredito, identificacion);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            log.error("No se encontro el credito interviniente {} - {}: ", idCredito, identificacion);
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

    @PutMapping
    public ResponseEntity<CreditoIntervinienteDTO> actualizar(@RequestBody CreditoIntervinienteDTO creditoInterviniente) {
        try {
            log.info("Actualizando el registro creditoInterviniente: {}", creditoInterviniente);
            return ResponseEntity.ok(creditoIntervinienteService.actualizar(creditoInterviniente));

        } catch (RuntimeException rte) {
            log.error("Error al actualizar el registro:", rte);
            return ResponseEntity.notFound().build();
        }
    }
}
