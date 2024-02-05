package com.banquito.core.banking.creditos.dto.Builder;

import com.banquito.core.banking.creditos.domain.CreditoTablaPagos;
import com.banquito.core.banking.creditos.domain.CreditoTablaPagosPK;
import com.banquito.core.banking.creditos.dto.CreditoTablaPagosDTO;

import java.sql.Timestamp;
import java.util.Date;

public class CreditoTablaPagosBuilder {
    public static CreditoTablaPagosDTO toDTO(CreditoTablaPagos creditoTablaPagos) {
        CreditoTablaPagosDTO dto = CreditoTablaPagosDTO.builder()
                .codCredito(creditoTablaPagos.getPK().getCodCredito())
                .codCuota(creditoTablaPagos.getPK().getCodCuota())
                .capital(creditoTablaPagos.getCapital())
                .interes(creditoTablaPagos.getInteres())
                .montoCuota(creditoTablaPagos.getMontoCuota())
                .capitalRestante(creditoTablaPagos.getCapitalRestante())
                .fechaPlanificadaPago(creditoTablaPagos.getFechaPlanificadaPago())
                .estado(creditoTablaPagos.getEstado())
                .fechaPagoEfectivo(creditoTablaPagos.getFechaPagoEfectivo())
                .transaccionPago(creditoTablaPagos.getTransaccionPago())
                .build();
        return dto;
    }

    public static CreditoTablaPagos toCreditoTablaPagos(CreditoTablaPagosDTO dto) {

        CreditoTablaPagos creditoTablaPagos = new CreditoTablaPagos();
        CreditoTablaPagosPK PK = new CreditoTablaPagosPK();
        PK.setCodCredito(dto.getCodCredito());
        PK.setCodCuota(dto.getCodCuota());
        creditoTablaPagos.setPK(PK);
        creditoTablaPagos.setCapital(dto.getCapital());
        creditoTablaPagos.setInteres(dto.getInteres());
        creditoTablaPagos.setMontoCuota(dto.getMontoCuota());
        creditoTablaPagos.setCapitalRestante(dto.getCapitalRestante());
        creditoTablaPagos.setFechaPlanificadaPago(dto.getFechaPlanificadaPago());
        creditoTablaPagos.setEstado(dto.getEstado());
        creditoTablaPagos.setFechaPagoEfectivo(dto.getFechaPagoEfectivo());
        creditoTablaPagos.setTransaccionPago(dto.getTransaccionPago());

        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        creditoTablaPagos.setFechaUltimoCambio(timestamp);

        return creditoTablaPagos;
    }
}
