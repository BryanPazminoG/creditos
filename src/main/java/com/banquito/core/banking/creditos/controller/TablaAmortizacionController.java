package com.banquito.core.banking.creditos.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.banking.creditos.dto.CreditoDTO;
import com.banquito.core.banking.creditos.dto.TablaAmortizacionDTO;
import com.banquito.core.banking.creditos.service.TablaAmortizacionService;

import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("api/v1/tablaAmortizacion")
public class TablaAmortizacionController {

    private TablaAmortizacionService tablaAmortizacionService;

    public TablaAmortizacionController(TablaAmortizacionService tablaAmortizacionService) {
        this.tablaAmortizacionService = tablaAmortizacionService;
    }

    @GetMapping("{codCredito}")
    public ResponseEntity<List<TablaAmortizacionDTO>> BuscarTablaAmortizacion(
            @PathVariable("codCredito") Integer codCredito) {
        try {
            log.info("Obteniendo la tabla de amortizacion con el id: {}", codCredito);
            return ResponseEntity.ok(tablaAmortizacionService.BuscarTablaAmortizacion(codCredito));
        } catch (RuntimeException rte) {
            log.error("Error al obteniendo la tabla de amortizacion: ", rte);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{codCredito}/{numeroCuota}")
    public ResponseEntity<TablaAmortizacionDTO> BuscarPorCuota(@PathVariable("codCredito") Integer codCredito,
            @PathVariable("numeroCuota") Integer numeroCuota) {

        try {
            log.info("Obteniendo la cuato {} del credito {}", codCredito, numeroCuota);
            TablaAmortizacionDTO TablaAmortizacion = tablaAmortizacionService.ObtenerPorCuota(codCredito, numeroCuota);
            return ResponseEntity.ok(TablaAmortizacion);
        } catch (Exception e) {
            log.error("Error al obtener la cuato {} del credito {}", codCredito, numeroCuota);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/proximoPago/{codCredito}")
    public ResponseEntity<TablaAmortizacionDTO> ProximoPago(@PathVariable("codCredito") Integer codCredito) {
        try {
            log.info("Obteniendo el proximo pago del credito con id: {}", codCredito);
            return ResponseEntity.ok(tablaAmortizacionService.ProximoPago(codCredito).get());
        } catch (RuntimeException rte) {
            log.error("Error al obteniendo el proximo pago: ", rte);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/pagosRealizados/{codCredito}")
    public ResponseEntity<List<TablaAmortizacionDTO>> PagosRealizados(@PathVariable("codCredito") Integer codCredito) {
        try {
            log.info("Obteniendo los pagos realizados del credito con id: {}", codCredito);
            return ResponseEntity.ok(tablaAmortizacionService.PagosRealizados(codCredito));
        } catch (RuntimeException rte) {
            log.error("Error al obtener los pagos realizados: ", rte);
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping
    public ResponseEntity<TablaAmortizacionDTO> CambiarEstado(@PathParam("codCredito") Integer codCredito,
            @PathParam("numeroCuota") Integer numeroCuota, @PathParam("estado") String estado) {
        try {
            log.info("Actualizando estado de la cuota {} del credito {}: ", codCredito, numeroCuota);
            return ResponseEntity.ok(tablaAmortizacionService.CambiarEstado(codCredito, numeroCuota, estado));
        } catch (RuntimeException rte) {
            log.error("Error al actualizar el estado de la tabla de amortizacion: ", rte);
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<List<TablaAmortizacionDTO>> Crear(@RequestBody CreditoDTO creditoDTO) {
        try {
            log.info("Creando tabla amortizacion: {}");
            return ResponseEntity.ok(tablaAmortizacionService.Crear(creditoDTO));
        } catch (RuntimeException rte) {
            log.error("Error al crear el nuevo registro: ", rte);
            return ResponseEntity.badRequest().build();
        }
    }
}
