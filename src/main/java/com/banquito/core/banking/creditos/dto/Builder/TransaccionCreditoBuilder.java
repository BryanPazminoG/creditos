package com.banquito.core.banking.creditos.dto.Builder;

import com.banquito.core.banking.creditos.domain.TransaccionCredito;
import com.banquito.core.banking.creditos.dto.TransaccionCreditoDTO;

public class TransaccionCreditoBuilder {
        public static TransaccionCreditoDTO toDTO(TransaccionCredito transaccionCredito) {

            TransaccionCreditoDTO dto = TransaccionCreditoDTO.builder()
                .codTransaccionCredito(transaccionCredito.getCodTransaccionCredito())
                .codCredito(transaccionCredito.getCodCredito())
                .codCuota(transaccionCredito.getCodCuota())
                .codInteresAcumulado(transaccionCredito.getCodInteresAcumulado())
                .codTransaccion(transaccionCredito.getCodTransaccion())
                .numeroCuenta(transaccionCredito.getNumeroCuenta())
                .estado(transaccionCredito.getEstado())
                .tipoPago(transaccionCredito.getTipoPago())
                .fechaPago(transaccionCredito.getFechaPago()).build();
        return dto;
    }

    public static TransaccionCredito toTransaccionCredito(TransaccionCreditoDTO dto) {

        TransaccionCredito transaccionCredito = new TransaccionCredito();
        transaccionCredito.setCodTransaccionCredito(dto.getCodTransaccionCredito());
        transaccionCredito.setCodCredito(dto.getCodCredito());
        transaccionCredito.setCodCuota(dto.getCodCuota());
        transaccionCredito.setCodInteresAcumulado(dto.getCodInteresAcumulado());
        transaccionCredito.setCodTransaccion(dto.getCodTransaccion());
        transaccionCredito.setNumeroCuenta(dto.getNumeroCuenta());
        transaccionCredito.setEstado(dto.getEstado());
        transaccionCredito.setTipoPago(dto.getTipoPago());
        transaccionCredito.setFechaPago(dto.getFechaPago());

        return transaccionCredito;
    }
}
