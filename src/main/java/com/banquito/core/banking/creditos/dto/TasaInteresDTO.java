package com.banquito.core.banking.creditos.dto;

import lombok.Builder;
import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.Data;

@Builder
@Data
public class TasaInteresDTO {

    private String codTasaInteres;
    private String tipoTasaInteres;
    private BigDecimal tasaMinima;
    private BigDecimal tasaMaxima;
    private String estado;
    private Timestamp fechaInicioVigencia;
    private Timestamp fechaFinVigencia;
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TasaInteresDTO other = (TasaInteresDTO) obj;
        if (codTasaInteres == null) {
            if (other.codTasaInteres != null)
                return false;
        } else if (!codTasaInteres.equals(other.codTasaInteres))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codTasaInteres == null) ? 0 : codTasaInteres.hashCode());
        return result;
    }

}
