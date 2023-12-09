package com.banquito.core.banking.creditos.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.banking.creditos.domain.Credito;
import com.banquito.core.banking.creditos.service.CreditoService;

@RestController
@RequestMapping("/credito")
public class CreditoController {
    @Autowired
    private CreditoService creditoService;

    @GetMapping("/getall")
    public List<Credito> getAll() {
        return creditoService.getAll();
    }
}
