package com.banquito.core.banking.creditos.dto.Builder;

import com.banquito.core.banking.creditos.domain.TransaccionCredito;
import com.banquito.core.banking.creditos.dto.TransaccionCreditoDTO;

public class TransaccionCreditoBuilder {
        public static TransaccionCreditoDTO toDTO(TransaccionCredito transaccionCredito) {

            TransaccionCreditoDTO dto = TransaccionCreditoDTO.builder()
                .codTransaccionCredito(transaccionCredito.getCodTransaccionCredito())
                .codCredito(transaccionCredito.getCodCredito())
                .codCuota(transaccionCredito.getCodCuota())
                .codTransaccion(transaccionCredito.getCodTransaccion())
                .interesTotal(transaccionCredito.getInteresTotal())
                .numeroCuenta(transaccionCredito.getNumeroCuenta())
                .estado(transaccionCredito.getEstado())
                .tipoPago(transaccionCredito.getTipoPago()).build();
        return dto;
    }

    public static TransaccionCredito toTransaccionCredito(TransaccionCreditoDTO dto) {

        TransaccionCredito transaccionCredito = new TransaccionCredito();
        transaccionCredito.setCodTransaccionCredito(dto.getCodTransaccionCredito());
        transaccionCredito.setCodCredito(dto.getCodCredito());
        transaccionCredito.setCodCuota(dto.getCodCuota());
        transaccionCredito.setCodTransaccion(dto.getCodTransaccion());
        transaccionCredito.setInteresTotal(dto.getInteresTotal());
        transaccionCredito.setNumeroCuenta(dto.getNumeroCuenta());
        transaccionCredito.setEstado(dto.getEstado());
        transaccionCredito.setTipoPago(dto.getTipoPago());

        return transaccionCredito;
    }
}
