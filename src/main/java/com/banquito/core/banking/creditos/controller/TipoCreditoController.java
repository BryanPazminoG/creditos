package com.banquito.core.banking.creditos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.banquito.core.banking.creditos.domain.TipoCredito;
import com.banquito.core.banking.creditos.service.TipoCreditoService;

import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("api/v1/tipoCreditos")
public class TipoCreditoController {

    private TipoCreditoService tipoCreditoService;

    public TipoCreditoController(TipoCreditoService tipoCreditoService) {
        this.tipoCreditoService = tipoCreditoService;
    }

    @GetMapping
    public ResponseEntity<Iterable<TipoCredito>> listar() {
        try {
            log.info("Obteniendo la lista de tipo credito");
            return ResponseEntity.ok(tipoCreditoService.listAll());
        } catch (RuntimeException rte) {
            log.error("Error al listar tipo credito: ", rte);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<TipoCredito> obtenerPorId(@PathParam("id") Integer id) {
        log.info("Obteniendo el tipo credito con el id: {}", id);
        return tipoCreditoService.getById(id)
                .map(register -> ResponseEntity.ok(register))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TipoCredito> save(@RequestBody TipoCredito tipoCredito) {
        try {
            log.info("Guardando nuevo registro de tipo credito: {}", tipoCredito);
            return ResponseEntity.ok(tipoCreditoService.create(tipoCredito));
        } catch (RuntimeException rte) {
            log.error("Error al crear el nuevo registro: ", rte);
             return ResponseEntity.badRequest().build();   
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> delete(@PathParam("id") Integer id) {
        try {
            log.info("Eliminando el tipo credito con id: {}", id);
            tipoCreditoService.delete(id);
            return ResponseEntity.ok(true);            
        } catch (RuntimeException rte) {
            log.error("Error al eliminar el registro ", rte);
            return ResponseEntity.notFound().build();    
        }
    }

    @PutMapping
    public ResponseEntity<TipoCredito> update(@RequestBody TipoCredito tipoCredito) {
        try {
            log.info("Actualizando el tipo credito: {}", tipoCredito);
            return ResponseEntity.ok(tipoCreditoService.update(tipoCredito));
        } catch (RuntimeException rte) {
            log.error("Error al actualizar el registro ", rte);
            return ResponseEntity.badRequest().build();
        }
    }
}
