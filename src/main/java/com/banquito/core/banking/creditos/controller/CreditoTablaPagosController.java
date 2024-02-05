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

import com.banquito.core.banking.creditos.dto.CreditoTablaPagosDTO;
import com.banquito.core.banking.creditos.service.CreditoTablaPagosService;
import com.banquito.core.banking.creditos.service.logica.PreTablaPagos;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("api/v1/pagos")
public class CreditoTablaPagosController {

    private CreditoTablaPagosService creditoTablaPagosService;

    public CreditoTablaPagosController(CreditoTablaPagosService creditoTablaPagosService) {
        this.creditoTablaPagosService = creditoTablaPagosService;
    }

    @GetMapping("{credito}/{cuota}")
    public ResponseEntity<CreditoTablaPagosDTO> BuscarCuota(@PathVariable("credito") Integer credito,
            @PathVariable("cuota") Integer cuota) {

        try {
            log.info("Obteniendo la cuato {} del credito {}", cuota, credito);
            CreditoTablaPagosDTO creditoTablaPagos = creditoTablaPagosService.obtenerPorId(credito, cuota);
            return ResponseEntity.ok(creditoTablaPagos);
        } catch (Exception e) {
            log.error("Error al obtener la cuato {} del credito {}", cuota, credito);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{tasaInteres}/{montoPrestamo}/{numeroPagos}")
    public ResponseEntity<List<PreTablaPagos>> PreVistaTbAmortizacion(@PathVariable("tasaInteres") double tasaInteres,
            @PathVariable("montoPrestamo") double montoPrestamo, @PathVariable("numeroPagos") Integer numeroPagos) {
        try {
            log.info("Calculando la tabla de Amortizacion");
            return ResponseEntity.ok(creditoTablaPagosService.PreVistaTbAmortizacion(tasaInteres, montoPrestamo,
                    numeroPagos));
        } catch (RuntimeException rte) {
            log.error("Error al calculando la tabla de Amortizacion: ", rte);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("{credito}")
    public ResponseEntity<List<CreditoTablaPagosDTO>> getTablaAmortizacion(
            @PathVariable("credito") Integer credito) {
        try {
            log.info("Obteniendo la tabla de amortizacion con el id: {}", credito);
            return ResponseEntity.ok(creditoTablaPagosService.getTablaAmortizacion(credito));
        } catch (RuntimeException rte) {
            log.error("Error al obteniendo la tabla de amortizacion: ", rte);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/proximo/{credito}")
    public ResponseEntity<CreditoTablaPagosDTO> getProximoPago(@PathVariable("credito") Integer credito) {
        try {
            log.info("Obteniendo el proximo pago del credito con id: {}", credito);
            return ResponseEntity.ok(creditoTablaPagosService.getProximoPago(credito).get());
        } catch (RuntimeException rte) {
            log.error("Error al obteniendo el proximo pago: ", rte);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/realizados/{credito}")
    public ResponseEntity<List<CreditoTablaPagosDTO>> getPagosRealizados(@PathVariable("credito") Integer credito) {
        try {
            log.info("Obteniendo los pagos realizados del credito con id: {}", credito);
            return ResponseEntity.ok(creditoTablaPagosService.getPagosRealizados(credito));
        } catch (RuntimeException rte) {
            log.error("Error al obtener los pagos realizados: ", rte);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CreditoTablaPagosDTO> crear(@RequestBody CreditoTablaPagosDTO creditoTablaPagos) {
        try {
            log.info("Guardando nuevo registro creditoTablaPagos: {}", creditoTablaPagos);
            return ResponseEntity.ok(creditoTablaPagosService.crear(creditoTablaPagos));

        } catch (RuntimeException rte) {
            log.error("Error al crear el nuevo registro: ", rte);
            return ResponseEntity.badRequest().build();
        }
    }
}
