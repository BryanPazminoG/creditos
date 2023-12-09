package com.banquito.core.banking.creditos.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.banquito.core.banking.creditos.domain.Credito;

@Repository
public interface CreditoRepository extends CrudRepository<Credito, Integer> {
    
}
