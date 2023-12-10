package com.banquito.core.banking.creditos.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.banquito.core.banking.creditos.domain.CreditoInterviniente;
import com.banquito.core.banking.creditos.domain.CreditoIntervinientePK;

@Repository
public interface CreditoIntervinienteRepository extends CrudRepository<CreditoInterviniente, CreditoIntervinientePK> {

    public Optional<List<CreditoInterviniente>> findByTipoAndPK(String tipo, CreditoIntervinientePK PK);

}