package com.banquito.core.banking.creditos.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.banking.creditos.dto.CreditoDTO;
import com.banquito.core.banking.creditos.service.CreditoService;

import jakarta.websocket.server.PathParam;
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

    @GetMapping("/{codCredito}")
    public ResponseEntity<CreditoDTO> ObtenerPorId(@PathVariable("codCredito") Integer codCredito) {
        try {
            log.info("Obteniendo credito por el ID: {}", codCredito);
            CreditoDTO credito = creditoService.ObtenerPorId(codCredito);
            return ResponseEntity.ok(credito);
        } catch (Exception e) {
            log.error("Error al obtener el credito con el id: {}", codCredito);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/clientes/{codCliente}")
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
    public ResponseEntity<Integer> Crear(@RequestBody CreditoDTO credito) {
        try {
            log.info("Guardando nuevo registro credito: {}", credito);
            return ResponseEntity.ok(creditoService.Crear(credito));
        } catch (RuntimeException rte) {
            log.error("Error al crear el nuevo registro: {}", credito);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<CreditoDTO> Actualizar(@RequestBody CreditoDTO credito) {
        try {
            log.info("Actualizando el registro credito: {}", credito);
            return ResponseEntity.ok(creditoService.Actualizar(credito));
        } catch (RuntimeException rte) {
            log.error("Error al actualizar el registro: ", rte);
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping
    public ResponseEntity<CreditoDTO> CambiarEstado(@RequestParam("codCredito") Integer codCredito,
            @PathParam("estado") String estado) {
        try {
            log.info("Actualizando estado del credito");
            return ResponseEntity.ok(creditoService.CambiarEstado(codCredito, estado));
        } catch (RuntimeException rte) {
            log.error("Error al actualizar el estado del credito: ", rte);
            return ResponseEntity.badRequest().build();
        }
    }
}
