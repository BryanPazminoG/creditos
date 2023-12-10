package com.banquito.core.banking.creditos.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.banquito.core.banking.creditos.domain.Credito;

@Repository
public interface CreditoRepository extends CrudRepository<Credito, Integer> {
    public List<Credito> findByCodTipoCredito(Integer codTipoCredito);

    public Optional<List<Credito>> findByCodCliente(Integer codCliente);

    public Optional<List<Credito>> findByEstadoAndCodCliente(String estado, Integer codCliente);
}
