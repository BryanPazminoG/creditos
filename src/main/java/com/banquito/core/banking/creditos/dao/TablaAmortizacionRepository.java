package com.banquito.core.banking.creditos.dao;

import java.util.List;
import java.sql.Date;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.banquito.core.banking.creditos.domain.TablaAmortizacion;
import com.banquito.core.banking.creditos.domain.TablaAmortizacionPK;

@Repository
public interface TablaAmortizacionRepository extends CrudRepository<TablaAmortizacion, TablaAmortizacionPK> {

    public List<TablaAmortizacion> findByPKCodCredito(Integer codCredito);

    public TablaAmortizacion findByFechaPlanificadaPagoAndPK(Date fechaPlanificadaPago, TablaAmortizacionPK PK);

    public List<TablaAmortizacion> findByEstadoOrderByFechaPlanificadaPago(String estado);
}