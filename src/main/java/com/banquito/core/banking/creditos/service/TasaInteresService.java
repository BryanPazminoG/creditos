package com.banquito.core.banking.creditos.service;

import java.util.List;
import java.util.Optional;

import com.banquito.core.banking.creditos.domain.TasaInteres;

public interface TasaInteresService {
    List<TasaInteres> GetAll();

    Optional<TasaInteres> GetById(String id);

    TasaInteres Save(TasaInteres tasaInteres);

    Boolean Delete(String id);

    Optional<List<TasaInteres>> GetByTipoTasaInteres(String tipoTasaInteres);
}