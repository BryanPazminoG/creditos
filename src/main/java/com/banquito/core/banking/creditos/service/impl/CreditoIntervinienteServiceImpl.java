package com.banquito.core.banking.creditos.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banquito.core.banking.creditos.dao.CreditoIntervinienteRepository;
import com.banquito.core.banking.creditos.domain.CreditoInterviniente;
import com.banquito.core.banking.creditos.service.CreditoIntervinienteService;

@Service
public class CreditoIntervinienteServiceImpl implements CreditoIntervinienteService {
    @Autowired
    private CreditoIntervinienteRepository creditoIntervinienteRepository;

    @Override
    public List<CreditoInterviniente> getAll() {
        return (List<CreditoInterviniente>) creditoIntervinienteRepository.findAll();
    }
}
