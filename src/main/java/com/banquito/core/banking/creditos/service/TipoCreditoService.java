package com.banquito.core.banking.creditos.service;

import java.util.List;
import java.util.Optional;

import com.banquito.core.banking.creditos.domain.TipoCredito;

public interface TipoCreditoService {
    List<TipoCredito> GetAll();

    Optional<TipoCredito> GetById(Integer id);

    TipoCredito Save(TipoCredito tipoCredito);

    Boolean Delete(Integer id);
}
