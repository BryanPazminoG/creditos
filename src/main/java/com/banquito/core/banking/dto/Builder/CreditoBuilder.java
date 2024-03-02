package com.banquito.core.banking.creditos.dto.Builder;

import java.sql.Timestamp;
import java.util.Date;

import com.banquito.core.banking.creditos.domain.Credito;
import com.banquito.core.banking.creditos.dto.CreditoDTO;

public class CreditoBuilder {
    public static CreditoDTO toDTO(Credito credito) {

        CreditoDTO dto = CreditoDTO.builder()
                .codCredito(credito.getCodCredito())
                .codTipoCredito(credito.getCodTipoCredito())
                .identificacionCliente(credito.getIdentificacionCliente())
                .tipoCliente(credito.getTipoCliente())
                .numeroCuenta(credito.getNumeroCuenta())
                .numeroOperacion(credito.getNumeroOperacion())
                .fechaCreacion(credito.getFechaCreacion())
                .monto(credito.getMonto())
                .plazo(credito.getPlazo())
                .tasaInteres(credito.getTasaInteres())
                .estado(credito.getEstado())
                .fechaDesembolso(credito.getFechaDesembolso())
                .fechaUltimoPago(credito.getFechaUltimoPago())
                .capitalPendiente(credito.getCapitalPendiente()).build();
        return dto;
    }

    public static Credito toCredito(CreditoDTO dto) {

        Credito credito = new Credito();
        credito.setCodCredito(dto.getCodCredito());
        credito.setCodTipoCredito(dto.getCodTipoCredito());
        credito.setIdentificacionCliente(dto.getIdentificacionCliente());
        credito.setTipoCliente(dto.getTipoCliente());
        credito.setNumeroCuenta(dto.getNumeroCuenta());
        credito.setNumeroOperacion(dto.getNumeroOperacion());
        credito.setFechaCreacion(dto.getFechaCreacion());
        credito.setMonto(dto.getMonto());
        credito.setPlazo(dto.getPlazo());
        credito.setTasaInteres(dto.getTasaInteres());
        credito.setEstado(dto.getEstado());
        credito.setFechaDesembolso(dto.getFechaDesembolso());
        credito.setFechaUltimoPago(dto.getFechaUltimoPago());
        credito.setCapitalPendiente(dto.getCapitalPendiente());

        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());

        credito.setFechaUltimoCambio(timestamp);

        return credito;
    }
}
