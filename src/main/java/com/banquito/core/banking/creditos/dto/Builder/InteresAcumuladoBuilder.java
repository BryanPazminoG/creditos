package com.banquito.core.banking.creditos.dto.Builder;

import com.banquito.core.banking.creditos.domain.InteresAcumulado;
import com.banquito.core.banking.creditos.dto.InteresAcumuladoDTO;

public class InteresAcumuladoBuilder {
    public static InteresAcumuladoDTO toDTO(InteresAcumulado interesAcumulado) {

        InteresAcumuladoDTO dto = InteresAcumuladoDTO.builder()
                .codInteresAcumulado(interesAcumulado.getCodInteresAcumulado())
                .codCredito(interesAcumulado.getCodCredito())
                .numeroCuota(interesAcumulado.getNumeroCuota())
                .tasaInteresVigente(interesAcumulado.getTasaInteresVigente())
                .capitalPendiente(interesAcumulado.getCapitalPendiente())
                .interesGenerado(interesAcumulado.getInteresGenerado())
                .estado(interesAcumulado.getEstado()).build();

        return dto;
    }

    public static InteresAcumulado toInteresAcumulado(InteresAcumuladoDTO dto) {

        InteresAcumulado tasaAcumulado = new InteresAcumulado();
        tasaAcumulado.setCodInteresAcumulado(dto.getCodInteresAcumulado());
        tasaAcumulado.setCodCredito(dto.getCodCredito());
        tasaAcumulado.setNumeroCuota(dto.getNumeroCuota());
        tasaAcumulado.setTasaInteresVigente(dto.getTasaInteresVigente());
        tasaAcumulado.setCapitalPendiente(dto.getCapitalPendiente());
        tasaAcumulado.setInteresGenerado(dto.getInteresGenerado());
        tasaAcumulado.setEstado(dto.getEstado());
    
        return tasaAcumulado;
    }
}
