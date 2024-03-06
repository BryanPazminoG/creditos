package com.banquito.core.banking.creditos.dto.Builder;

import com.banquito.core.banking.creditos.domain.InteresAcumulado;
import com.banquito.core.banking.creditos.dto.InteresAcumuladoDTO;

public class InteresAcumuladoBuilder {
    public static InteresAcumuladoDTO toDTO(InteresAcumulado interesAcumulado) {

        InteresAcumuladoDTO dto = InteresAcumuladoDTO.builder()
                .codInteresAcumulado(interesAcumulado.getCodInteresAcumulado())
                .codCredito(interesAcumulado.getCodCredito())
                .tasaInteresVigente(interesAcumulado.getTasaInteresVigente())
                .montoCredito(interesAcumulado.getMontoCredito())
                .interesGenerado(interesAcumulado.getInteresGenerado())
                .estado(interesAcumulado.getEstado()).build();

        return dto;
    }

    public static InteresAcumulado toInteresAcumulado(InteresAcumuladoDTO dto) {

        InteresAcumulado tasaAcumulado = new InteresAcumulado();
        tasaAcumulado.setCodInteresAcumulado(dto.getCodInteresAcumulado());
        tasaAcumulado.setCodCredito(dto.getCodCredito());
        tasaAcumulado.setTasaInteresVigente(dto.getTasaInteresVigente());
        tasaAcumulado.setMontoCredito(dto.getMontoCredito());
        tasaAcumulado.setInteresGenerado(dto.getInteresGenerado());
        tasaAcumulado.setEstado(dto.getEstado());
    
        return tasaAcumulado;
    }
}
