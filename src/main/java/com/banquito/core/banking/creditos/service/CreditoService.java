package com.banquito.core.banking.creditos.service;

import java.util.List;
import java.util.Optional;

import com.banquito.core.banking.creditos.domain.Credito;

public interface CreditoService {

   List<Credito> GetAll();

   Optional<Credito> GetById(Integer id);

   Credito Save(Credito credito);

   Boolean Delete(Integer id);
}
