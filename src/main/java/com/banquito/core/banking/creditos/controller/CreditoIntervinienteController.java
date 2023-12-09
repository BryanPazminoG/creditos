package com.banquito.core.banking.creditos.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.banking.creditos.domain.CreditoInterviniente;
import com.banquito.core.banking.creditos.service.CreditoIntervinienteService;

@RestController
@RequestMapping("/creditointerviniente")
public class CreditoIntervinienteController {
    @Autowired
    private CreditoIntervinienteService creditoIntervinienteService;

    @GetMapping("/getall")
    public List<CreditoInterviniente> getAll() {
        return creditoIntervinienteService.getAll();
    }
}
