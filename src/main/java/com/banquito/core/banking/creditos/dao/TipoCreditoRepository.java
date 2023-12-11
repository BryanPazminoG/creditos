package com.banquito.core.banking.creditos.dao;

import java.util.List;
import java.math.BigDecimal;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.banquito.core.banking.creditos.domain.TipoCredito;

@Repository
public interface TipoCreditoRepository extends CrudRepository<TipoCredito, Integer> {

    public List<TipoCredito> findByTipoCliente(String tipoCliente);

    public List<TipoCredito> findByMontoMinimoGreaterThanEqualAndMontoMaximoLessThanEqual(BigDecimal montoMinimo,
            BigDecimal montoMaximo);

    public List<TipoCredito> findByPlazoMinimoGreaterThanEqualAndPlazoMaximoLessThanEqual(BigDecimal plazoMinimo,
            BigDecimal plazoMaximo);

    public List<TipoCredito> findByEstado(String estado);
}