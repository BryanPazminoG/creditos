package com.banquito.core.banking.creditos.dto.Builder;

import com.banquito.core.banking.creditos.domain.TasaInteres;
import com.banquito.core.banking.creditos.dto.TasaInteresDTO;

public class TasaInteresBuilder {

    public static TasaInteresDTO toDTO(TasaInteres tasaInteres) {

        TasaInteresDTO dto = TasaInteresDTO.builder()
                .codTasaInteres(tasaInteres.getCodTasaInteres())
                .tipoTasaInteres(tasaInteres.getTipoTasaInteres())
                .tasaMinima(tasaInteres.getTasaMinima())
                .tasaMaxima(tasaInteres.getTasaMaxima())
                .estado(tasaInteres.getEstado())
                .fechaInicioVigencia(tasaInteres.getFechaInicioVigencia())
                .fechaFinVigencia(tasaInteres.getFechaFinVigencia()).build();
        return dto;
    }

    public static TasaInteres toTasaInteres(TasaInteresDTO dto) {

        TasaInteres tasaInteres = new TasaInteres();
        tasaInteres.setCodTasaInteres(dto.getCodTasaInteres());
        tasaInteres.setTipoTasaInteres(dto.getTipoTasaInteres());
        tasaInteres.setTasaMinima(dto.getTasaMinima());
        tasaInteres.setTasaMaxima(dto.getTasaMaxima());
        tasaInteres.setEstado(dto.getEstado());
        tasaInteres.setFechaInicioVigencia(dto.getFechaInicioVigencia());
        tasaInteres.setFechaFinVigencia(dto.getFechaFinVigencia());

        return tasaInteres;
    }
}
