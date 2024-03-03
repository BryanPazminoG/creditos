package com.banquito.core.banking.creditos.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.banking.creditos.dto.CreditoDTO;
import com.banquito.core.banking.creditos.dto.TablaAmortizacionDTO;
import com.banquito.core.banking.creditos.service.TablaAmortizacionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("api/v1/pagos")
public class TablaAmortizacionController {

    private TablaAmortizacionService TablaAmortizacionService;

    public TablaAmortizacionController(TablaAmortizacionService TablaAmortizacionService) {
        this.TablaAmortizacionService = TablaAmortizacionService;
    }

    @GetMapping("{credito}/{cuota}")
    public ResponseEntity<TablaAmortizacionDTO> BuscarCuota(@PathVariable("credito") Integer credito,
            @PathVariable("cuota") Integer cuota) {

        try {
            log.info("Obteniendo la cuato {} del credito {}", cuota, credito);
            TablaAmortizacionDTO TablaAmortizacion = TablaAmortizacionService.obtenerPorId(credito, cuota);
            return ResponseEntity.ok(TablaAmortizacion);
        } catch (Exception e) {
            log.error("Error al obtener la cuato {} del credito {}", cuota, credito);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{credito}")
    public ResponseEntity<List<TablaAmortizacionDTO>> getTablaAmortizacion(
            @PathVariable("credito") Integer credito) {
        try {
            log.info("Obteniendo la tabla de amortizacion con el id: {}", credito);
            return ResponseEntity.ok(TablaAmortizacionService.getTablaAmortizacion(credito));
        } catch (RuntimeException rte) {
            log.error("Error al obteniendo la tabla de amortizacion: ", rte);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/proximo/{credito}")
    public ResponseEntity<TablaAmortizacionDTO> getProximoPago(@PathVariable("credito") Integer credito) {
        try {
            log.info("Obteniendo el proximo pago del credito con id: {}", credito);
            return ResponseEntity.ok(TablaAmortizacionService.getProximoPago(credito).get());
        } catch (RuntimeException rte) {
            log.error("Error al obteniendo el proximo pago: ", rte);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/realizados/{credito}")
    public ResponseEntity<List<TablaAmortizacionDTO>> getPagosRealizados(@PathVariable("credito") Integer credito) {
        try {
            log.info("Obteniendo los pagos realizados del credito con id: {}", credito);
            return ResponseEntity.ok(TablaAmortizacionService.getPagosRealizados(credito));
        } catch (RuntimeException rte) {
            log.error("Error al obtener los pagos realizados: ", rte);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<List<TablaAmortizacionDTO>> crear(@RequestBody CreditoDTO creditoDTO) {
        try {
            log.info("Creando tabla amortizacion: {}");
            return ResponseEntity.ok(TablaAmortizacionService.crear(creditoDTO));
        } catch (RuntimeException rte) {
            log.error("Error al crear el nuevo registro: ", rte);
            return ResponseEntity.badRequest().build();
        }
    }
}
