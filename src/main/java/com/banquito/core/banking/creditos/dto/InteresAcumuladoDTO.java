package com.banquito.core.banking.creditos.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class InteresAcumuladoDTO {

    private Integer codInteresAcumulado;
    private Integer codCredito;
    private BigDecimal tasaInteresVigente;
    private BigDecimal capitalPendiente;
    private BigDecimal interesGenerado;
    private String estado;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        InteresAcumuladoDTO other = (InteresAcumuladoDTO) obj;
        if (codInteresAcumulado == null) {
            if (other.codInteresAcumulado != null)
                return false;
        } else if (!codInteresAcumulado.equals(other.codInteresAcumulado))
            return false;
        return true;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codInteresAcumulado == null) ? 0 : codInteresAcumulado.hashCode());
        return result;
    }  
}
