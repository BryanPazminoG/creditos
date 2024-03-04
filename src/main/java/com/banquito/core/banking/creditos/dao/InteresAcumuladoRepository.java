package com.banquito.core.banking.creditos.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.banquito.core.banking.creditos.domain.InteresAcumulado;

@Repository
public interface InteresAcumuladoRepository extends CrudRepository<InteresAcumulado, Integer> {
    public List<InteresAcumulado> findByCodCreditoOrderByFechaCreacion(Integer codCredito);
    public List<InteresAcumulado> findByEstadoOrderByFechaCreacion(String estado);
}