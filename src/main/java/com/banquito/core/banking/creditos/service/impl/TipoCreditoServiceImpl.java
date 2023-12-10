package com.banquito.core.banking.creditos.service.impl;

import java.util.List;
import java.util.Optional;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banquito.core.banking.creditos.dao.TipoCreditoRepository;
import com.banquito.core.banking.creditos.domain.TipoCredito;
import com.banquito.core.banking.creditos.service.TipoCreditoService;

@Service
public class TipoCreditoServiceImpl implements TipoCreditoService {
    @Autowired
    private TipoCreditoRepository tipoCreditoRepository;

    @Override
    public List<TipoCredito> GetAll() {
        return (List<TipoCredito>) tipoCreditoRepository.findAll();
    }

    @Override
    public Optional<TipoCredito> GetById(Integer id) {
        return tipoCreditoRepository.findById(id);
    }

    @Override
    public TipoCredito Save(TipoCredito tipoCredito) {
        return tipoCreditoRepository.save(tipoCredito);
    }

    @Override
    public Boolean Delete(Integer id) {
        return tipoCreditoRepository.findById(id).map(register -> {
            tipoCreditoRepository.deleteById(id);
            return true;
        }).orElse(false);
    }

    @Override
    public List<TipoCredito> ByTipoCliente(String tipoCliente) {
        return tipoCreditoRepository.findByTipoCliente(tipoCliente);
    }

    @Override
    public Optional<List<TipoCredito>> ByMonto(BigDecimal montoMinimo, BigDecimal montoMaximo) {
        return tipoCreditoRepository.findByMontoMinimoGreaterThanEqualAndMontoMaximoLessThanEqual(montoMinimo, montoMaximo);
    }

    @Override
    public Optional<List<TipoCredito>> ByPlazo(BigDecimal plazoMinimo, BigDecimal plazoMaximo) {
        return tipoCreditoRepository.findByPlazoMinimoGreaterThanEqualAndPlazoMaximoLessThanEqual(plazoMinimo, plazoMaximo);
    }

    @Override
    public List<TipoCredito> ByEstado(String estado) {
        return tipoCreditoRepository.findByEstado(estado);
    }
}
