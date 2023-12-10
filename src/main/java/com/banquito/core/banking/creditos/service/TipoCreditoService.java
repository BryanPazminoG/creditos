package com.banquito.core.banking.creditos.service;

import java.util.List;
import java.util.Optional;
import java.math.BigDecimal;

import com.banquito.core.banking.creditos.domain.TipoCredito;

public interface TipoCreditoService {
    List<TipoCredito> GetAll();

    Optional<TipoCredito> GetById(Integer id);

    TipoCredito Save(TipoCredito tipoCredito);

    Boolean Delete(Integer id);

    List<TipoCredito> ByTipoCliente(String tipoCliente);

    Optional<List<TipoCredito>> ByMonto(BigDecimal montoMinimo, BigDecimal montoMaximo);

    Optional<List<TipoCredito>> ByPlazo(BigDecimal plazoMinimo, BigDecimal plazoMaximo);

    List<TipoCredito> ByEstado(String estado);
}
