package com.banquito.core.banking.creditos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.banking.creditos.dto.TipoCreditoDTO;
import com.banquito.core.banking.creditos.service.TipoCreditoService;

import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

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
    public ResponseEntity<List<TipoCreditoDTO>> Listar() {
        try {
            log.info("Obteniendo la lista de tipo credito");
            return ResponseEntity.ok(tipoCreditoService.Listar());
        } catch (RuntimeException rte) {
            log.error("Error al listar tipo credito: ", rte);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{codTipoCredito}")
    public ResponseEntity<TipoCreditoDTO> ObtenerPorId(@PathVariable("codTipoCredito") Integer codTipoCredito) {
        try {
            log.info("Obteniendo el tipo credito con el codTipoCredito: {}", codTipoCredito);
            TipoCreditoDTO tipoCredito = tipoCreditoService.ObtenerPorId(codTipoCredito);
            return ResponseEntity.ok(tipoCredito);
        } catch (Exception e) {
            log.error("No se encontro la tasa de interes con el id: ", codTipoCredito);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("estado/{estado}")
    public ResponseEntity<List<TipoCreditoDTO>> ListarPorEstado(@PathVariable("estado") String estado) {
        try {
            log.info("Obteniendo la lista de tipo credito");
            List<TipoCreditoDTO> listTipoCredito = tipoCreditoService.ListarPorEstado(estado);
            return ResponseEntity.ok(listTipoCredito);
        } catch (RuntimeException rte) {
            log.error("Error al listar tipo credito: ", rte);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<TipoCreditoDTO> Crear(@RequestBody TipoCreditoDTO tipoCredito) {
        try {
            log.info("Guardando nuevo registro de tipo credito: {}", tipoCredito);
            return ResponseEntity.ok(tipoCreditoService.Crear(tipoCredito));
        } catch (RuntimeException rte) {
            log.error("Error al crear el nuevo registro: ", rte);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<TipoCreditoDTO> Actualizar(@RequestBody TipoCreditoDTO tipoCredito) {
        try {
            log.info("Actualizando el tipo credito: {}", tipoCredito);
            return ResponseEntity.ok(tipoCreditoService.Actualizar(tipoCredito));
        } catch (RuntimeException rte) {
            log.error("Error al actualizar el registro ", rte);
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PatchMapping
    public ResponseEntity<TipoCreditoDTO> CambiarEstado(@PathParam("codTipoCredito") Integer codTipoCredito, @PathParam("estado") String estado) {
        try {
            log.info("Actualizando estado deL tipo credito");
            return ResponseEntity.ok(tipoCreditoService.CambiarEstado(codTipoCredito, estado));
        } catch (RuntimeException rte) {
            log.error("Error al actualizar el estado del tipo credito: ", rte);
            return ResponseEntity.badRequest().build();
        }
    }
}
