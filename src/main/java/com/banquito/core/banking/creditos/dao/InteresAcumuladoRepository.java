package com.banquito.core.banking.creditos.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.banquito.core.banking.creditos.domain.TransaccionCredito;

@Repository
public interface InteresAcumuladoRepository extends CrudRepository<TransaccionCredito, Integer> {

}