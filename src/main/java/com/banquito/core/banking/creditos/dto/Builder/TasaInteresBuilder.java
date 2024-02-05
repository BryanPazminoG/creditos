package com.banquito.core.banking.creditos.dto.Builder;

import com.banquito.core.banking.creditos.domain.TasaInteres;
import com.banquito.core.banking.creditos.dto.TasaInteresDTO;
import java.sql.Timestamp;
import java.util.Date;

public class TasaInteresBuilder {

    public static TasaInteresDTO toDTO(TasaInteres tasaInteres) {

        TasaInteresDTO dto = TasaInteresDTO.builder()
                .codTasaInteres(tasaInteres.getCodTasaInteres())
                .tipoTasaInteres(tasaInteres.getTipoTasaInteres())
                .nombre(tasaInteres.getNombre())
                .tasaMinima(tasaInteres.getTasaMinima())
                .tasaMaxima(tasaInteres.getTasaMaxima()).build();
        return dto;
    }

    public static TasaInteres toTasaInteres(TasaInteresDTO dto) {

        TasaInteres tasaInteres = new TasaInteres();
        tasaInteres.setCodTasaInteres(dto.getCodTasaInteres());
        tasaInteres.setTipoTasaInteres(dto.getTipoTasaInteres());
        tasaInteres.setNombre(dto.getNombre());
        tasaInteres.setTasaMinima(dto.getTasaMinima());
        tasaInteres.setTasaMaxima(dto.getTasaMaxima());
        tasaInteres.setCodTasaInteres(dto.getCodTasaInteres());

        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        
        tasaInteres.setFechaUltimoCambio(timestamp);

        return tasaInteres;
    }
}
