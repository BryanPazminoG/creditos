package com.banquito.core.banking.creditos.dto.Builder;

import com.banquito.core.banking.creditos.domain.TipoCredito;
import com.banquito.core.banking.creditos.dto.TipoCreditoDTO;

public class TipoCreditoBuilder {

    public static TipoCreditoDTO toDTO(TipoCredito tipoCredito) {
        TipoCreditoDTO dto = TipoCreditoDTO.builder()
                .codTipoCredito(tipoCredito.getCodTipoCredito())
                .codTasaInteres(tipoCredito.getCodTasaInteres())
                .nombre(tipoCredito.getNombre())
                .descripcion(tipoCredito.getDescripcion())
                .tipoCliente(tipoCredito.getTipoCliente())
                .unidadPlazo(tipoCredito.getUnidadPlazo())
                .plazoMinimo(tipoCredito.getPlazoMinimo())
                .plazoMaximo(tipoCredito.getPlazoMaximo())
                .montoMinimo(tipoCredito.getMontoMinimo())
                .montoMaximo(tipoCredito.getMontoMaximo())
                .estado(tipoCredito.getEstado())
                .build();
        return dto;
    }

    public static TipoCredito toTipoCredito(TipoCreditoDTO dto) {

        TipoCredito tipoCredito = new TipoCredito();
        tipoCredito.setCodTipoCredito(dto.getCodTipoCredito());
        tipoCredito.setCodTasaInteres(dto.getCodTasaInteres());
        tipoCredito.setNombre(dto.getNombre());
        tipoCredito.setDescripcion(dto.getDescripcion());
        tipoCredito.setTipoCliente(dto.getTipoCliente());
        tipoCredito.setUnidadPlazo(dto.getUnidadPlazo());
        tipoCredito.setPlazoMinimo(dto.getPlazoMinimo());
        tipoCredito.setPlazoMaximo(dto.getPlazoMaximo());
        tipoCredito.setMontoMinimo(dto.getMontoMinimo());
        tipoCredito.setMontoMaximo(dto.getMontoMaximo());
        tipoCredito.setEstado(dto.getEstado());

        return tipoCredito;
    }
}
