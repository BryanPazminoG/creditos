package com.banquito.core.banking.creditos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("api/v1/intervinientes")
public class CreditoIntervinienteController {
    @Autowired
    private CreditoIntervinienteService creditoIntervinienteService;

    @GetMapping("/{codCredito}/{codCliente}")
    public ResponseEntity<CreditoIntervinienteDTO> ObtenerPorId(@PathVariable("codCredito") Integer codCredito,
            @PathVariable("codCliente") String codCliente) {
        try {
            log.info("Obteniendo interviniente con el credito {} y el cliente {}", codCredito, codCliente);
            CreditoIntervinienteDTO dto = creditoIntervinienteService.ObtenerPorId(codCredito, codCliente);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            log.error("No se encontro el credito interviniente {} - {}: ", codCredito, codCliente);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/creditos/{codCredito}")
    public ResponseEntity<List<CreditoIntervinienteDTO>> ListarPorCredito(
            @PathVariable("codCredito") Integer codCredito) {
        try {
            log.info("Obteniendo los interviniente con el codigo credito: ", codCredito);
            List<CreditoIntervinienteDTO> listCreditoInterviniente = creditoIntervinienteService
                    .ListarIntervinienteCredito(codCredito);
            return ResponseEntity.ok(listCreditoInterviniente);
        } catch (RuntimeException rte) {
            log.error("Error al listar los interviniente: ", rte);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CreditoIntervinienteDTO> Crear(@RequestBody CreditoIntervinienteDTO creditoInterviniente) {
        try {
            log.info("Guardando nuevo registro creditoInterviniente: {}", creditoInterviniente);
            return ResponseEntity.ok(creditoIntervinienteService.Crear(creditoInterviniente));
        } catch (RuntimeException rte) {
            log.error("Error al crear el nuevo registro: ", rte);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{codCredito}/{codCliente}")
    public ResponseEntity<CreditoIntervinienteDTO> Eliminar(@PathVariable("codCredito") Integer codCredito,
            @PathVariable("codCliente") String codCliente) {
        try {
            log.info("Eliminando el registro credito interviniente con codCredito: {} y codCliente {}", codCredito, codCliente);
            creditoIntervinienteService.Eliminar(codCredito, codCliente);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException rte) {
            log.error("Error al eliminar el registro credito interviniente: ", rte);
            return ResponseEntity.notFound().build();
        }
    }
}
