package com.banquito.core.banking.creditos.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.banking.creditos.domain.Credito;
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

    @GetMapping("{id}")
    public ResponseEntity<Credito> obtenerPorId(@PathParam("id") Integer id) {
        log.info("Obteniendo credito por el ID: {}", id);
        return creditoService.getById(id)
                .map(register -> ResponseEntity.ok(register))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("clientes/{identificacion}")
    public ResponseEntity<List<Credito>> BuscarPorCodigoCliente(@PathParam("identificacion") String identificacion) {
        try {
            log.info("Buscando el credito por el numero de identificacion del cliente: {}", identificacion);
            return ResponseEntity.ok(creditoService.BuscarPorCliente(identificacion));
        } catch (RuntimeException rte) {
            log.error("Error al obtener el credito: {}", rte);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Credito> save(@RequestBody Credito credito) {
        try {
            log.info("Guardando nuevo registro credito: {}", credito);
            return ResponseEntity.ok(creditoService.create(credito));
        } catch (RuntimeException rte) {
            log.error("Error al crear el nuevo registro: {}", credito);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<Credito> update(@RequestBody Credito credito) {
        try {
            log.info("Actualizando el registro credito: {}", credito);
            return ResponseEntity.ok(creditoService.update(credito));
        } catch (RuntimeException rte) {
            log.error("Error al actualizar el registro: ", rte);
            return ResponseEntity.badRequest().build();
        }
    }
}
