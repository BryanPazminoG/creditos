package com.banquito.core.banking.creditos.dao;

import java.util.List;
import java.math.BigDecimal;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.banquito.core.banking.creditos.domain.TipoCredito;

@Repository
public interface TipoCreditoRepository extends CrudRepository<TipoCredito, Integer> {

    public List<TipoCredito> findByTipoCliente(String tipoCliente);

    public List<TipoCredito> findByMontoMinimoGreaterThanEqualAndMontoMaximoLessThanEqualAndCodTipoCreditoEqualsOrderByNombre(BigDecimal montoMinimo,
            BigDecimal montoMaximo, Integer codTipoCredito);

    public List<TipoCredito> findByPlazoMinimoGreaterThanEqualAndPlazoMaximoLessThanEqualAndCodTipoCreditoEqualsOrderByNombre(BigDecimal plazoMinimo,
            BigDecimal plazoMaximo, Integer codTipoCredito);

    public List<TipoCredito> findByEstadoOrderByNombre(String estado);
}