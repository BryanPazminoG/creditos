package com.banquito.core.banking.creditos.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.banquito.core.banking.creditos.domain.Credito;

@Repository
public interface CreditoRepository extends CrudRepository<Credito, Integer> {
    public List<Credito> findByCodTipoCreditoOrderByCodTipoCredito(Integer codTipoCredito);

    public List<Credito> findByIdentificacionClienteOrderByFechaCreacion(String identificacionCliente);

    public List<Credito> findByEstadoAndIdentificacionClienteOrderByFechaCreacion(String estado, String identificacionCliente);
}
