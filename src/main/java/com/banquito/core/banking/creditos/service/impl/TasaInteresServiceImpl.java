package com.banquito.core.banking.creditos.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banquito.core.banking.creditos.dao.TasaInteresRepository;
import com.banquito.core.banking.creditos.service.TasaInteresService;
import com.banquito.core.banking.creditos.domain.TasaInteres;

@Service
public class TasaInteresServiceImpl implements TasaInteresService{
    @Autowired
    private TasaInteresRepository tasaInteresRepository;

    @Override
    public List<TasaInteres> getAll(){
        return (List<TasaInteres>) tasaInteresRepository.findAll();
    }
}
