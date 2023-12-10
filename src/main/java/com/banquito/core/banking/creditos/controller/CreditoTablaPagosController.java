package com.banquito.core.banking.creditos.controller;

import java.util.List;
import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.banking.creditos.domain.CreditoTablaPagos;
import com.banquito.core.banking.creditos.domain.CreditoTablaPagosPK;
import com.banquito.core.banking.creditos.service.CreditoTablaPagosService;

@RestController
@RequestMapping("/creditotablapagos")
public class CreditoTablaPagosController {
    @Autowired
    private CreditoTablaPagosService creditoTablaPagosService;

    @GetMapping("/getall")
    public ResponseEntity<List<CreditoTablaPagos>> GetAll() {
        return new ResponseEntity<>(creditoTablaPagosService.GetAll(), HttpStatus.OK);
    }

    @GetMapping("/getbyid/{creditoid}/{cuotaid}")
    public ResponseEntity<CreditoTablaPagos> GetById(@PathVariable("creditoid") Integer creditoId,
            @PathVariable("cuotaid") Integer cuotaId) {
        return creditoTablaPagosService.GetById(new CreditoTablaPagosPK(creditoId, cuotaId))
                .map(register -> new ResponseEntity<>(register, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity<CreditoTablaPagos> Save(@RequestBody CreditoTablaPagos creditoTablaPagos) {
        return new ResponseEntity<>(creditoTablaPagosService.Save(creditoTablaPagos), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{creditoid}/{cuotaid}")
    public ResponseEntity<Boolean> Delete(@PathVariable("creditoid") Integer creditoId,
            @PathVariable("cuotaid") Integer cuotaId) {
        if (creditoTablaPagosService.Delete(new CreditoTablaPagosPK(creditoId, cuotaId))) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/fechapago/{fechapago}/{creditoid}/{cuotaid}")
    public ResponseEntity<CreditoTablaPagos> ByFechaPago(@PathVariable("fechapago") Date fechaPago,
            @PathVariable("creditoid") Integer creditoId,
            @PathVariable("cuotaid") Integer cuotaId) {
        CreditoTablaPagosPK creditoTablaPagosPK = new CreditoTablaPagosPK(creditoId, cuotaId);
        return creditoTablaPagosService.ByFechaPago(fechaPago, creditoTablaPagosPK).map(register -> {
            return new ResponseEntity<>(register, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/estdo/{estado}")
    public ResponseEntity<List<CreditoTablaPagos>> ByEstado(@PathVariable("estado") String estado) {
        return new ResponseEntity<>(creditoTablaPagosService.ByEstado(estado), HttpStatus.OK);
    }
}
