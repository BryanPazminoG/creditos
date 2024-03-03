package com.banquito.core.banking.creditos.dao;


import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.banquito.core.banking.creditos.domain.TransaccionCredito;

@Repository
public interface TransaccionCreditoRepository extends CrudRepository<TransaccionCredito, Integer> {
    public List<TransaccionCredito> findByCodCreditoOrderByFechaCreacion(Integer codCredito);
    public Optional<TransaccionCredito> findByCodCreditoAndCodCuota(Integer codCredito, Integer codCuota);

}