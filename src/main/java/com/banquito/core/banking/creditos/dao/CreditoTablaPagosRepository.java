package com.banquito.core.banking.creditos.dao;

import java.util.List;
import java.sql.Date;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.banquito.core.banking.creditos.domain.TablaAmortizacion;
import com.banquito.core.banking.creditos.domain.TablaAmortizacionPK;

@Repository
public interface CreditoTablaPagosRepository extends CrudRepository<TablaAmortizacion, TablaAmortizacionPK> {

    public TablaAmortizacion findByFechaPlanificadaPagoAndPK(Date fechaPlanificadaPago, TablaAmortizacionPK PK);

    public List<TablaAmortizacion> findByEstadoOrderByFechaPlanificadaPago(String estado);
}