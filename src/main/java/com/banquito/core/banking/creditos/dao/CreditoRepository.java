package com.banquito.core.banking.creditos.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.banquito.core.banking.creditos.domain.Credito;

@Repository
public interface CreditoRepository extends CrudRepository<Credito, Integer> {
    public List<Credito> findByCodTipoCreditoOrder(Integer codTipoCredito);

    public List<Credito> findByCodClienteOrderByFechaCreacion(Integer codCliente);

    public List<Credito> findByEstadoAndCodClienteOrderByFechaCreacion(String estado, Integer codCliente);
}
