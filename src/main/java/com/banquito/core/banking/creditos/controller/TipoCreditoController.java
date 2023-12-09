package com.banquito.core.banking.creditos.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.banquito.core.banking.creditos.domain.TipoCredito;
import com.banquito.core.banking.creditos.service.TipoCreditoService;

@RestController
@RequestMapping("/tipocredito")
public class TipoCreditoController {
    @Autowired
    private TipoCreditoService tipoCreditoService;

    @GetMapping("/getall")
    public List<TipoCredito> getAll() {
        return tipoCreditoService.getAll();
    }
}
