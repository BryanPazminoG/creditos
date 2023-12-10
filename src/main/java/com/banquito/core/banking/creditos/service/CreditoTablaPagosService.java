package com.banquito.core.banking.creditos.service;

import java.util.List;
import java.util.Optional;

import com.banquito.core.banking.creditos.domain.CreditoTablaPagos;
import com.banquito.core.banking.creditos.domain.CreditoTablaPagosPK;

public interface CreditoTablaPagosService {
    List<CreditoTablaPagos> GetAll();

    Optional<CreditoTablaPagos> GetById(CreditoTablaPagosPK id);

    CreditoTablaPagos Save(CreditoTablaPagos creditoTablaPagos);

    Boolean Delete(CreditoTablaPagosPK id);
}
