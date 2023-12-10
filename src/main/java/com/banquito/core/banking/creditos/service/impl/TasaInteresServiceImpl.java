package com.banquito.core.banking.creditos.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banquito.core.banking.creditos.dao.TasaInteresRepository;
import com.banquito.core.banking.creditos.service.TasaInteresService;
import com.banquito.core.banking.creditos.domain.TasaInteres;

@Service
public class TasaInteresServiceImpl implements TasaInteresService {
    @Autowired
    private TasaInteresRepository tasaInteresRepository;

    @Override
    public List<TasaInteres> GetAll() {
        return (List<TasaInteres>) tasaInteresRepository.findAll();
    }

    @Override
    public Optional<TasaInteres> GetById(String id) {
        return tasaInteresRepository.findById(id);
    }

    @Override
    public TasaInteres Save(TasaInteres tasaInteres) {
        return tasaInteresRepository.save(tasaInteres);
    }

    @Override
    public Boolean Delete(String id) {
        return tasaInteresRepository.findById(id).map(register -> {
            tasaInteresRepository.deleteById(id);
            return true;
        }).orElse(false);
    }

    @Override
    public Optional<List<TasaInteres>> GetByTipoTasaInteres(String tipoTasaInteres) {
        return tasaInteresRepository.findByTipoTasaInteres(tipoTasaInteres);
    }
}
