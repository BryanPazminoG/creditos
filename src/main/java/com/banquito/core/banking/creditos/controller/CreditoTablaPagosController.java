package com.banquito.core.banking.creditos.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.banking.creditos.domain.CreditoTablaPagos;
import com.banquito.core.banking.creditos.service.CreditoTablaPagosService;

@RestController
@RequestMapping("/creditotablapagos")
public class CreditoTablaPagosController {
    @Autowired
    private CreditoTablaPagosService creditoTablaPagosService;

    @GetMapping("/getall")
    public List<CreditoTablaPagos> getAll() {
        return creditoTablaPagosService.getAll();
    }
}
