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
                .referencia(transaccionCredito.getReferencia())
                .estado(transaccionCredito.getEstado())
                .tipoPago(transaccionCredito.getTipoPago())
                .fechaPago(transaccionCredito.getFechaPago()).build();
        return dto;
    }

    public static TransaccionCredito toInteresAcumulado(TransaccionCreditoDTO dto) {

        TransaccionCredito tasaAcumulado = new TransaccionCredito();
        tasaAcumulado.setCodTransaccionCredito(dto.getCodTransaccionCredito());
        tasaAcumulado.setCodCredito(dto.getCodCredito());
        tasaAcumulado.setCodCuota(dto.getCodCuota());
        tasaAcumulado.setCodInteresAcumulado(dto.getCodInteresAcumulado());
        tasaAcumulado.setCodTransaccion(dto.getCodTransaccion());
        tasaAcumulado.setNumeroCuenta(dto.getNumeroCuenta());
        tasaAcumulado.setReferencia(dto.getReferencia());    
        tasaAcumulado.setEstado(dto.getEstado());
        tasaAcumulado.setTipoPago(dto.getTipoPago());
        tasaAcumulado.setFechaPago(dto.getFechaPago());

        return tasaAcumulado;
    }
}
