package com.banquito.core.banking.creditos.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banquito.core.banking.creditos.domain.Credito;
import com.banquito.core.banking.creditos.dao.CreditoRepository;
import com.banquito.core.banking.creditos.service.CreditoService;

@Service
public class CreditoServiceImpl implements CreditoService {
    @Autowired
    private CreditoRepository creditoRepository;

    @Override
    public List<Credito> GetAll() {
        return (List<Credito>) creditoRepository.findAll();
    }

    @Override
    public Optional<Credito> GetById(Integer id) {
        return creditoRepository.findById(id);
    }

    @Override
    public Credito Save(Credito credito) {
        return creditoRepository.save(credito);
    }

    @Override
    public Boolean Delete(Integer id) {
        return creditoRepository.findById(id).map(register -> {
            creditoRepository.deleteById(id);
            return true;
        }).orElse(false);
    }
}
