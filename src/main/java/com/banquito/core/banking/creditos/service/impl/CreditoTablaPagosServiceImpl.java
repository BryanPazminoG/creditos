package com.banquito.core.banking.creditos.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banquito.core.banking.creditos.dao.CreditoTablaPagosRepository;
import com.banquito.core.banking.creditos.domain.CreditoTablaPagos;
import com.banquito.core.banking.creditos.domain.CreditoTablaPagosPK;
import com.banquito.core.banking.creditos.service.CreditoTablaPagosService;

@Service
public class CreditoTablaPagosServiceImpl implements CreditoTablaPagosService {
    @Autowired
    private CreditoTablaPagosRepository creditoTablaPagosRepository;

    @Override
    public List<CreditoTablaPagos> GetAll() {
        return (List<CreditoTablaPagos>) creditoTablaPagosRepository.findAll();
    }

    @Override
    public Optional<CreditoTablaPagos> GetById(CreditoTablaPagosPK id) {
        return creditoTablaPagosRepository.findById(id);
    }

    @Override
    public CreditoTablaPagos Save(CreditoTablaPagos creditoTablaPagos) {
        return creditoTablaPagosRepository.save(creditoTablaPagos);
    }

    @Override
    public Boolean Delete(CreditoTablaPagosPK id) {
        return creditoTablaPagosRepository.findById(id).map(register -> {
            creditoTablaPagosRepository.deleteById(id);
            return true;
        }).orElse(false);
    }
}
