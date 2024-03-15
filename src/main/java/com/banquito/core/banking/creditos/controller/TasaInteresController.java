package com.banquito.core.banking.creditos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.banking.creditos.dto.TasaInteresDTO;
import com.banquito.core.banking.creditos.service.TasaInteresService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("api/v1/tasaInteres")
public class TasaInteresController {

    @Autowired
    private TasaInteresService tasaInteresService;
    
    @GetMapping
    public ResponseEntity<List<TasaInteresDTO>> Listar() {
        try {
            log.info("Obteniendo la lista de tasas de interes");
            return ResponseEntity.ok(tasaInteresService.Listar());
        } catch (RuntimeException rte) {
            log.error("Error al listar las tasas de interes: ", rte);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{codTasaInteres}")
    public ResponseEntity<TasaInteresDTO> ObtenerPorId(@PathVariable("codTasaInteres") String codTasaInteres) {
        log.info("Obteniendo la tasa de interes con el codTasaInteres: {}", codTasaInteres);
        try {
            TasaInteresDTO tasaInteresDTO = tasaInteresService.ObtenerPorId(codTasaInteres);
            return ResponseEntity.ok(tasaInteresDTO);
        } catch (Exception e) {
            log.error("No se encontro la tasa de interes con el codTasaInteres: ", codTasaInteres);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/estados")
    public ResponseEntity<List<TasaInteresDTO>> ListarPorEstado(@RequestParam("estado") String estado) {
        log.info("Obteniendo las tasas de interes por el estado: {}", estado);
        try {
            List<TasaInteresDTO> listTasaInteresDTO = tasaInteresService.ListarPorEstado(estado);
            return ResponseEntity.ok(listTasaInteresDTO);
        } catch (Exception e) {
            log.error("No se encontro las tasas de interes por el estado: ", estado);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<TasaInteresDTO> Crear(@RequestBody TasaInteresDTO tasaInteres) {
        try {
            log.info("Guardando nuevo registro de tasa interes: {}", tasaInteres);
            return ResponseEntity.ok(tasaInteresService.Crear(tasaInteres));
        } catch (RuntimeException rte) {
            log.error("Error al crear el nuevo registro: ", rte);
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PatchMapping
    public ResponseEntity<TasaInteresDTO> CambiarEstado(@RequestParam("codTasaInteres") String codTasaInteres, @RequestParam("estado") String estado) {
        try {
            log.info("Actualizando estado de la tasa interes");
            return ResponseEntity.ok(tasaInteresService.CambiarEstado(codTasaInteres, estado));
        } catch (RuntimeException rte) {
            log.error("Error al actualizar el estado de la tasa interes: ", rte);
            return ResponseEntity.badRequest().build();
        }
    }
}
