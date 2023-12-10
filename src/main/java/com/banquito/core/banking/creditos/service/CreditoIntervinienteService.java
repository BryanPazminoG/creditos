package com.banquito.core.banking.creditos.service;

import java.util.List;
import java.util.Optional;

import com.banquito.core.banking.creditos.domain.CreditoInterviniente;
import com.banquito.core.banking.creditos.domain.CreditoIntervinientePK;

public interface CreditoIntervinienteService {
    List<CreditoInterviniente> GetAll();

    Optional<CreditoInterviniente> GetById(CreditoIntervinientePK id);

    CreditoInterviniente Save(CreditoInterviniente creditoInterviniente);

    Boolean Delete(CreditoIntervinientePK id);
}
