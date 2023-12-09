package com.banquito.core.banking.creditos.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.banquito.core.banking.creditos.domain.CreditoTablaPagos;
import com.banquito.core.banking.creditos.domain.CreditoTablaPagosPK;


@Repository
public interface CreditoTablaPagosRepository extends CrudRepository<CreditoTablaPagos, CreditoTablaPagosPK>{

    
}