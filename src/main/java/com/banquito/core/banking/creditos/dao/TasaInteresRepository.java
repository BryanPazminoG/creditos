package com.banquito.core.banking.creditos.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.banquito.core.banking.creditos.domain.TasaInteres;

@Repository
public interface TasaInteresRepository extends CrudRepository<TasaInteres, String> {
    public Optional<List<TasaInteres>> findByTipoTasaInteres(String tipoTasaInteres);
}
