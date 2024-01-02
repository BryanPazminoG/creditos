package com.banquito.core.banking.creditos.controller;

import java.util.List;
// import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.banking.creditos.domain.CreditoTablaPagos;
// import com.banquito.core.banking.creditos.domain.CreditoTablaPagosPK;
import com.banquito.core.banking.creditos.service.CreditoTablaPagosService;
import com.banquito.core.banking.creditos.service.logica.PreTablaPagos;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/creditotablapagos")
public class CreditoTablaPagosController {
    @Autowired
    private CreditoTablaPagosService creditoTablaPagosService;

    @GetMapping("/getbyid")
    public ResponseEntity<CreditoTablaPagos> GetById(@RequestParam("creditoid") Integer creditoId,
            @RequestParam("cuotaid") Integer cuotaId) {
        return creditoTablaPagosService.getById(creditoId, cuotaId)
                .map(register -> new ResponseEntity<>(register, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/pretablapagos")
    public ResponseEntity<List<PreTablaPagos>> PreVistaTbAmortizacion(@RequestParam("tasaInteres") double tasaInteres,
            @RequestParam("montoPrestamo") double montoPrestamo, @RequestParam("numeroPagos") Integer numeroPagos) {
        List<PreTablaPagos> pretabla = creditoTablaPagosService.PreVistaTbAmortizacion(tasaInteres, montoPrestamo,
                numeroPagos);
        return new ResponseEntity<>(pretabla, HttpStatus.OK);
    }

    @GetMapping("/tabla-amortizacion")
    public ResponseEntity<List<CreditoTablaPagos>> getTablaAmortizacion(
            @RequestParam("codCredito") Integer codCredito) {
        List<CreditoTablaPagos> tabla = creditoTablaPagosService.getTablaAmortizacion(codCredito);
        return new ResponseEntity<>(tabla, HttpStatus.OK);
    }

    @GetMapping("/proximo-pago")
    public ResponseEntity<CreditoTablaPagos> getProximoPago(@RequestParam("codCredito") Integer codCredito) {
        CreditoTablaPagos letraPago = creditoTablaPagosService.getProximoPago(codCredito).get();
        return new ResponseEntity<>(letraPago, HttpStatus.OK);
    }

    @GetMapping("/pagos-realizados")
    public ResponseEntity<List<CreditoTablaPagos>> getPagosRealizados(@RequestParam("codCredito") Integer codCredito) {
        List<CreditoTablaPagos> listaPagos = creditoTablaPagosService.getPagosRealizados(codCredito).get();
        return new ResponseEntity<>(listaPagos, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<CreditoTablaPagos> Save(@RequestBody CreditoTablaPagos creditoTablaPagos) {
        return new ResponseEntity<>(creditoTablaPagosService.create(creditoTablaPagos), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<CreditoTablaPagos> Update(@RequestBody CreditoTablaPagos creditoTablaPagos) {
        return new ResponseEntity<>(creditoTablaPagosService.update(creditoTablaPagos), HttpStatus.OK);
    }
    // @GetMapping("/fechapago/{fechapago}/{creditoid}/{cuotaid}")
    // public ResponseEntity<CreditoTablaPagos>
    // ByFechaPago(@PathVariable("fechapago") Date fechaPago,
    // @PathVariable("creditoid") Integer creditoId,
    // @PathVariable("cuotaid") Integer cuotaId) {
    // CreditoTablaPagosPK creditoTablaPagosPK = new CreditoTablaPagosPK(creditoId,
    // cuotaId);
    // return creditoTablaPagosService.ByFechaPago(fechaPago,
    // creditoTablaPagosPK).map(register -> {
    // return new ResponseEntity<>(register, HttpStatus.OK);
    // }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    // }

    // @GetMapping("/estdo/{estado}")
    // public ResponseEntity<List<CreditoTablaPagos>>
    // ByEstado(@PathVariable("estado") String estado) {
    // return new ResponseEntity<>(creditoTablaPagosService.ByEstado(estado),
    // HttpStatus.OK);
    // }
}
