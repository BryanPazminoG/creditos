package com.banquito.core.banking.creditos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banquito.core.banking.creditos.dao.TipoCreditoRepository;
import com.banquito.core.banking.creditos.domain.TipoCredito;
import com.banquito.core.banking.creditos.service.TipoCreditoService;

@Service
public class TipoCreditoServiceImpl implements TipoCreditoService{
    @Autowired
    private TipoCreditoRepository tipoCreditoRepository;

    @Override
    public List<TipoCredito> getAll() {
        return (List<TipoCredito>) tipoCreditoRepository.findAll();
    }
}
