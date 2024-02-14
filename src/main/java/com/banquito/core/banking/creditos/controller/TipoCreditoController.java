package com.banquito.core.banking.creditos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.banking.creditos.dto.TipoCreditoDTO;
import com.banquito.core.banking.creditos.service.TipoCreditoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = {"http://localhost:4200", "http://34.173.161.134:4201", "http://34.176.205.203:4202", 
                        "http://34.176.102.118:4203", "http://34.176.137.180:4204"})
//@CrossOrigin(origins = "", allowedHeaders = "", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
@RestController
@RequestMapping("api/v1/tipoCreditos")
public class TipoCreditoController {

    private TipoCreditoService tipoCreditoService;

    public TipoCreditoController(TipoCreditoService tipoCreditoService) {
        this.tipoCreditoService = tipoCreditoService;
    }

    @GetMapping
    public ResponseEntity<Iterable<TipoCreditoDTO>> listar() {
        try {
            log.info("Obteniendo la lista de tipo credito");
            return ResponseEntity.ok(tipoCreditoService.listar());
        } catch (RuntimeException rte) {
            log.error("Error al listar tipo credito: ", rte);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<TipoCreditoDTO> obtenerPorId(@PathVariable("id") Integer id) {
        try {
            log.info("Obteniendo el tipo credito con el id: {}", id);
            TipoCreditoDTO tipoCredito = tipoCreditoService.obtenerPorId(id);
            return ResponseEntity.ok(tipoCredito);
        } catch (Exception e) {
            log.error("No se encontro la tasa de interes con el id: ", id);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<TipoCreditoDTO> save(@RequestBody TipoCreditoDTO tipoCredito) {
        try {
            log.info("Guardando nuevo registro de tipo credito: {}", tipoCredito);
            return ResponseEntity.ok(tipoCreditoService.crear(tipoCredito));
        } catch (RuntimeException rte) {
            log.error("Error al crear el nuevo registro: ", rte);
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> eliminar(@PathVariable("id") Integer id) {
        try {
            log.info("Eliminando el tipo credito con id: {}", id);
            tipoCreditoService.eliminar(id);
            return ResponseEntity.ok(true);
        } catch (RuntimeException rte) {
            log.error("Error al eliminar el registro ", rte);
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<TipoCreditoDTO> actualizar(@RequestBody TipoCreditoDTO tipoCredito) {
        try {
            log.info("Actualizando el tipo credito: {}", tipoCredito);
            return ResponseEntity.ok(tipoCreditoService.actualizar(tipoCredito));
        } catch (RuntimeException rte) {
            log.error("Error al actualizar el registro ", rte);
            return ResponseEntity.badRequest().build();
        }
    }
}
