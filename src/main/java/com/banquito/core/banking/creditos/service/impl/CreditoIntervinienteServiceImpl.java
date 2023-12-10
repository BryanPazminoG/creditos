package com.banquito.core.banking.creditos.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banquito.core.banking.creditos.dao.CreditoIntervinienteRepository;
import com.banquito.core.banking.creditos.domain.CreditoInterviniente;
import com.banquito.core.banking.creditos.domain.CreditoIntervinientePK;
import com.banquito.core.banking.creditos.service.CreditoIntervinienteService;

@Service
public class CreditoIntervinienteServiceImpl implements CreditoIntervinienteService {
    @Autowired
    private CreditoIntervinienteRepository creditoIntervinienteRepository;

    @Override
    public List<CreditoInterviniente> GetAll() {
        return (List<CreditoInterviniente>) creditoIntervinienteRepository.findAll();
    }

    @Override
    public Optional<CreditoInterviniente> GetById(CreditoIntervinientePK id) {
        return creditoIntervinienteRepository.findById(id);
    }

    @Override
    public CreditoInterviniente Save(CreditoInterviniente creditoInterviniente) {
        return creditoIntervinienteRepository.save(creditoInterviniente);
    }

    @Override
    public Boolean Delete(CreditoIntervinientePK id) {
        return GetById(id).map(register ->{
            creditoIntervinienteRepository.deleteById(id);
            return true;
        }).orElse(false);
    }
}
