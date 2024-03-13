package com.banquito.core.banking.creditos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.banking.creditos.dto.InteresAcumuladoDTO;
import com.banquito.core.banking.creditos.service.InteresAcumuladoService;
import java.math.BigDecimal;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("api/v1/interesAcumulado")
public class InteresAcumuladoController {
    
    @Autowired
    private InteresAcumuladoService interesAcumuladoService;

    @GetMapping("/{codInteresAcumulado}")
    public ResponseEntity<InteresAcumuladoDTO> ObtenerPorId(
            @PathVariable("codInteresAcumulado") Integer codInteresAcumulado) {
        try {
            log.info("Obteniendo el interes acumulado por el ID: {}", codInteresAcumulado);
            InteresAcumuladoDTO interesAcumuladoDTO = interesAcumuladoService.ObtenerPorId(codInteresAcumulado);
            return ResponseEntity.ok(interesAcumuladoDTO);
        } catch (Exception e) {
            log.error("Error al obtener el interes acumulado por el ID {}", codInteresAcumulado);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/creditos/{codCredito}")
    public ResponseEntity<List<InteresAcumuladoDTO>> ObtenerPorCredito(@PathVariable("codCredito") Integer codCredito) {
        try {
            log.info("Obteniendo el interes acumulado por el ID credito: {}", codCredito);
            List<InteresAcumuladoDTO> listInteresAcumuladoDTO = interesAcumuladoService
                    .ListarPorCodigoCredito(codCredito);
            return ResponseEntity.ok(listInteresAcumuladoDTO);
        } catch (Exception e) {
            log.error("Error al obtener el interes acumulado por el ID credito {}", codCredito);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/estados")
    public ResponseEntity<List<InteresAcumuladoDTO>> ObtenerPorEstado(@RequestParam("estado") String estado) {
        try {
            log.info("Obteniendo el interes acumulado por el estado: {}", estado);
            List<InteresAcumuladoDTO> listInteresAcumuladoDTO = interesAcumuladoService.ListarEstado(estado);
            return ResponseEntity.ok(listInteresAcumuladoDTO);
        } catch (Exception e) {
            log.error("Error al obtener el interes acumulado por el estado {}", estado);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/interes/{codCredito}/{numeroCuota}")
    public ResponseEntity<BigDecimal> ObtenerIntereAcumulado(@PathVariable("codCredito") Integer codCredito, @PathVariable("numeroCuota") Integer numeroCuota) {
        try {
            log.info("Obteniendo el interes acumulado de la cuota {} del credito {}", numeroCuota, codCredito);
            BigDecimal interesGenerado = interesAcumuladoService.InteresAcumuladoCuota(codCredito, numeroCuota);
            return ResponseEntity.ok(interesGenerado);
        } catch (Exception e) {
            log.error("Error al obtener el interes acumulado de la cuota {} del credito {}", numeroCuota, codCredito);
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping
    public ResponseEntity<InteresAcumuladoDTO> CambiarEstado(@RequestParam("codInteresAcumulado") Integer codInteresAcumulado,
            @RequestParam("estado") String estado) {
        try {
            log.info("Actualizando estado del interes acumulado: {} ", estado);
            return ResponseEntity.ok(interesAcumuladoService.CambiarEstado(codInteresAcumulado, estado));
        } catch (RuntimeException rte) {
            log.error("Error al actualizar el estado de la tabla de amortizacion: ", rte);
            return ResponseEntity.badRequest().build();
        }
    }
}
