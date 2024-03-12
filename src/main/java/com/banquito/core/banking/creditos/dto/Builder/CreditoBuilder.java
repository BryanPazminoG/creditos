package com.banquito.core.banking.creditos.dto.Builder;

import com.banquito.core.banking.creditos.domain.Credito;
import com.banquito.core.banking.creditos.dto.CreditoDTO;

public class CreditoBuilder {
    public static CreditoDTO toDTO(Credito credito) {

        CreditoDTO dto = CreditoDTO.builder()
                .codCredito(credito.getCodCredito())
                .codTipoCredito(credito.getCodTipoCredito())
                .codTransaccion(credito.getCodTransaccion())
                .codCliente(credito.getCodCliente())
                .numeroCuenta(credito.getNumeroCuenta())
                .monto(credito.getMonto())
                .plazo(credito.getPlazo())
                .numeroCuotas(credito.getNumeroCuotas())
                .cuotasPagadas(credito.getCuotasPagadas())
                .capitalPendiente(credito.getCapitalPendiente())
                .tasaInteres(credito.getTasaInteres())
                .canalTransaccion(credito.getCanalTransaccion())
                .estado(credito.getEstado())
                .fechaAprobacion(credito.getFechaAprobacion())
                .fechaDesembolso(credito.getFechaDesembolso())
                .fechaCierre(credito.getFechaCierre()).build();
        return dto;
    }

    public static Credito toCredito(CreditoDTO dto) {

        Credito credito = new Credito();
        credito.setCodCredito(dto.getCodCredito());
        credito.setCodTipoCredito(dto.getCodTipoCredito());
        credito.setCodTransaccion(dto.getCodTransaccion());
        credito.setCodCliente(dto.getCodCliente());
        credito.setNumeroCuenta(dto.getNumeroCuenta());
        credito.setMonto(dto.getMonto());
        credito.setPlazo(dto.getPlazo());
        credito.setNumeroCuotas(dto.getNumeroCuotas());
        credito.setCuotasPagadas(dto.getCuotasPagadas());
        credito.setCapitalPendiente(dto.getCapitalPendiente());
        credito.setTasaInteres(dto.getTasaInteres());
        credito.setCanalTransaccion(dto.getCanalTransaccion());
        credito.setEstado(dto.getEstado());
        credito.setFechaAprobacion(dto.getFechaAprobacion());
        credito.setFechaDesembolso(dto.getFechaDesembolso());
        credito.setFechaCierre(dto.getFechaCierre());

        return credito;
    }
}
