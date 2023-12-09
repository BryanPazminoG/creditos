package com.banquito.core.banking.creditos.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.banking.creditos.domain.TasaInteres;
import com.banquito.core.banking.creditos.service.TasaInteresService;

@RestController
@RequestMapping("/tasainteres")
public class TasaInteresController {
    @Autowired
    private TasaInteresService tasaInteresService;

    @GetMapping("/getall")
    public List<TasaInteres> getAll(){
        return tasaInteresService.getAll();
    }
}
