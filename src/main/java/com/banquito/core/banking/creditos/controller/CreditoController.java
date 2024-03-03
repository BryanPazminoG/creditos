package com.banquito.core.banking.creditos.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.banking.creditos.dto.CreditoDTO;
import com.banquito.core.banking.creditos.service.CreditoService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/v1/creditos")
public class CreditoController {

    private CreditoService creditoService;

    public CreditoController(CreditoService creditoService) {
        this.creditoService = creditoService;
    }

    @GetMapping("{codCredito}")
    public ResponseEntity<CreditoDTO> obtenerPorId(@PathVariable("codCredito") Integer codCredito) {
        try {
            log.info("Obteniendo credito por el ID: {}", codCredito);
            CreditoDTO credito = creditoService.obtenerPorId(codCredito);
            return ResponseEntity.ok(credito);
        } catch (Exception e) {
            log.error("Error al obtener el credito con el id: {}", codCredito);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("cliente/{codCliente}")
    public ResponseEntity<List<CreditoDTO>> BuscarPorCodigoCliente(@PathVariable("codCliente") String codCliente) {
        try {
            log.info("Buscando el credito por el codigo del cliente: {}", codCliente);
            return ResponseEntity.ok(creditoService.BuscarPorCliente(codCliente));
        } catch (RuntimeException rte) {
            log.error("Error al obtener el credito: {}", rte);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Integer> save(@RequestBody CreditoDTO credito) {
        try {
            log.info("Guardando nuevo registro credito: {}", credito);
            return ResponseEntity.ok(creditoService.crear(credito));
        } catch (RuntimeException rte) {
            log.error("Error al crear el nuevo registro: {}", credito);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<CreditoDTO> actualizar(@RequestBody CreditoDTO credito) {
        try {
            log.info("Actualizando el registro credito: {}", credito);
            return ResponseEntity.ok(creditoService.actualizar(credito));
        } catch (RuntimeException rte) {
            log.error("Error al actualizar el registro: ", rte);
            return ResponseEntity.badRequest().build();
        }
    }
}
