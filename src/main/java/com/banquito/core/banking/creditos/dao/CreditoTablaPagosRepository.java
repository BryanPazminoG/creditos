package com.banquito.core.banking.creditos.dao;

import java.util.List;
import java.sql.Date;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.banquito.core.banking.creditos.domain.CreditoTablaPagos;
import com.banquito.core.banking.creditos.domain.CreditoTablaPagosPK;

@Repository
public interface CreditoTablaPagosRepository extends CrudRepository<CreditoTablaPagos, CreditoTablaPagosPK> {

    public Optional<CreditoTablaPagos> findByFechaPlanificadaPagoAndPK(Date fechaPlanificadaPago, CreditoTablaPagosPK PK);

    public List<CreditoTablaPagos> findByEstado(String estado);
}