package com.banquito.core.banking.creditos.dto.Builder;

import com.banquito.core.banking.creditos.domain.TablaAmortizacion;
import com.banquito.core.banking.creditos.domain.TablaAmortizacionPK;
import com.banquito.core.banking.creditos.dto.TablaAmortizacionDTO;

public class TablaAmortizacionBuilder {
    public static TablaAmortizacionDTO toDTO(TablaAmortizacion creditoTablaPagos) {
        TablaAmortizacionDTO dto = TablaAmortizacionDTO.builder()
                .codCredito(creditoTablaPagos.getPK().getCodCredito())
                .codCuota(creditoTablaPagos.getPK().getCodCuota())
                .capital(creditoTablaPagos.getCapital())
                .interes(creditoTablaPagos.getInteres())
                .montoCuota(creditoTablaPagos.getMontoCuota())
                .capitalRestante(creditoTablaPagos.getCapitalRestante())
                .fechaPlanificadaPago(creditoTablaPagos.getFechaPlanificadaPago())
                .estado(creditoTablaPagos.getEstado())
                .build();
        return dto;
    }

    public static TablaAmortizacion toTablaAmortizacion(TablaAmortizacionDTO dto) {

        TablaAmortizacion creditoTablaPagos = new TablaAmortizacion();
        TablaAmortizacionPK PK = new TablaAmortizacionPK();
        PK.setCodCredito(dto.getCodCredito());
        PK.setCodCuota(dto.getCodCuota());
        creditoTablaPagos.setPK(PK);
        creditoTablaPagos.setCapital(dto.getCapital());
        creditoTablaPagos.setInteres(dto.getInteres());
        creditoTablaPagos.setMontoCuota(dto.getMontoCuota());
        creditoTablaPagos.setCapitalRestante(dto.getCapitalRestante());
        creditoTablaPagos.setFechaPlanificadaPago(dto.getFechaPlanificadaPago());
        creditoTablaPagos.setEstado(dto.getEstado());

        return creditoTablaPagos;
    }
}
