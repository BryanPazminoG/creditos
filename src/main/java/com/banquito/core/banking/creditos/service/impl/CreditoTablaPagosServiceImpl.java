package com.banquito.core.banking.creditos.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banquito.core.banking.creditos.dao.CreditoTablaPagosRepository;
import com.banquito.core.banking.creditos.domain.CreditoTablaPagos;
import com.banquito.core.banking.creditos.service.CreditoTablaPagosService;

@Service
public class CreditoTablaPagosServiceImpl implements CreditoTablaPagosService {
    @Autowired
    private CreditoTablaPagosRepository creditoTablaPagosRepository;

    @Override
    public List<CreditoTablaPagos> getAll(){
        return (List<CreditoTablaPagos>) creditoTablaPagosRepository.findAll();
    }
}
