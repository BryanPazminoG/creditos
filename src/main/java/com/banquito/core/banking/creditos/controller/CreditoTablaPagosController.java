package com.banquito.core.banking.creditos.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.banking.creditos.domain.CreditoTablaPagos;
import com.banquito.core.banking.creditos.service.CreditoTablaPagosService;
import com.banquito.core.banking.creditos.service.logica.PreTablaPagos;

import jakarta.websocket.server.PathParam;
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
    public ResponseEntity<CreditoTablaPagos> BuscarCuota(@PathParam("credito") Integer credito,
            @PathParam("cuota") Integer cuota) {
        log.info("Obteniendo la cuato {} del credito {}" ,cuota ,credito);

        return creditoTablaPagosService.getById(credito, cuota)
                .map(register -> ResponseEntity.ok(register))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("{tasaInteres}/{montoPrestamo}/{numeroPagos}")
    public ResponseEntity<List<PreTablaPagos>> PreVistaTbAmortizacion(@PathParam("tasaInteres") double tasaInteres,
            @PathParam("montoPrestamo") double montoPrestamo, @PathParam("numeroPagos") Integer numeroPagos) {
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
    public ResponseEntity<List<CreditoTablaPagos>> getTablaAmortizacion(
            @PathParam("credito") Integer credito) {
        try {
            log.info("Obteniendo la tabla de amortizacion con el id: {}", credito);
            return ResponseEntity.ok(creditoTablaPagosService.getTablaAmortizacion(credito));
        } catch (RuntimeException rte) {
            log.error("Error al obteniendo la tabla de amortizacion: ", rte);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/proximo/{credito}")
    public ResponseEntity<CreditoTablaPagos> getProximoPago(@PathParam("credito") Integer credito) {
        try {
            log.info("Obteniendo el proximo pago del credito con id: {}", credito);
            return ResponseEntity.ok(creditoTablaPagosService.getProximoPago(credito).get());
        } catch (RuntimeException rte) {
            log.error("Error al obteniendo el proximo pago: ", rte);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/realizados/{credito}")
    public ResponseEntity<List<CreditoTablaPagos>> getPagosRealizados(@PathParam("credito") Integer credito) {
        try {
            log.info("Obteniendo los pagos realizados del credito con id: {}", credito);
            return ResponseEntity.ok(creditoTablaPagosService.getPagosRealizados(credito).get());
        } catch (RuntimeException rte) {
            log.error("Error al obtener los pagos realizados: ", rte);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CreditoTablaPagos> save(@RequestBody CreditoTablaPagos creditoTablaPagos) {
        try {
            log.info("Guardando nuevo registro creditoTablaPagos: {}", creditoTablaPagos);
            return ResponseEntity.ok(creditoTablaPagosService.create(creditoTablaPagos));

        } catch (RuntimeException rte) {
            log.error("Error al crear el nuevo registro: ", rte);
            return ResponseEntity.badRequest().build();
        }
    }
}
