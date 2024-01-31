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

import com.banquito.core.banking.creditos.domain.TasaInteres;
import com.banquito.core.banking.creditos.service.TasaInteresService;

import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("api/v1/tasainteres")
public class TasaInteresController {

    private TasaInteresService tasaInteresService;

    public TasaInteresController(TasaInteresService tasaInteresService) {
        this.tasaInteresService = tasaInteresService;
    }

    @GetMapping
    public ResponseEntity<Iterable<TasaInteres>> listar() {
        try {
            log.info("Obteniendo la lista de tasas de interes");
            return ResponseEntity.ok(tasaInteresService.listAll());
        } catch (RuntimeException rte) {
            log.error("Error al listar las tasas de interes: ", rte);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<TasaInteres> obtenerPorId(@PathParam("id") String id) {
        log.info("Obteniendo la tasa de interes con el id: {}", id);
        return tasaInteresService.getById(id)
                .map(register -> ResponseEntity.ok(register))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("{id}/{monto}/{plazo}")
    public ResponseEntity<Double> calcularInteres(@PathParam("id") String id, @PathParam("monto") Double monto,
            @PathParam("plazo") Integer plazo) {
        try {
            log.info("Calculando tasa de interes");
            return ResponseEntity.ok(tasaInteresService.calcularTasaInteres(id, monto, plazo));
        } catch (RuntimeException rte) {
            log.error("Error al calcular la tasa de interes: ", rte);
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<TasaInteres> save(@RequestBody TasaInteres tasaInteres) {
        try {
            log.info("Guardando nuevo registro de tasa interes: {}", tasaInteres);
            return ResponseEntity.ok(tasaInteresService.create(tasaInteres));
        } catch (RuntimeException rte) {
            log.error("Error al crear el nuevo registro: ", rte);
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> delete(@PathParam("id") String id) {
        try {
            log.info("Eliminando la tasa de interes con id: {}", id);
            tasaInteresService.delete(id);
            return ResponseEntity.ok(true);
        } catch (RuntimeException rte) {
            log.error("Error al eliminar el registro ", rte);
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<TasaInteres> update(@RequestBody TasaInteres tasaInteres) {
        try {
            log.info("Actualizando la tasa de interes: {}", tasaInteres);
            return ResponseEntity.ok(tasaInteresService.update(tasaInteres));
        } catch (RuntimeException rte) {
            log.error("Error al actualizar el registro ", rte);
            return ResponseEntity.badRequest().build();
        }
    }
}
