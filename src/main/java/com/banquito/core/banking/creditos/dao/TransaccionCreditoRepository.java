package com.banquito.core.banking.creditos.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.banquito.core.banking.creditos.domain.TransaccionCredito;

@Repository
public interface TransaccionCreditoRepository extends CrudRepository<TransaccionCredito, Integer> {

}