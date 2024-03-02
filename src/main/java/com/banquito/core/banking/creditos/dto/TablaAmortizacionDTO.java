package com.banquito.core.banking.creditos.dto;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.sql.Date;

@Builder
@Data
public class TablaAmortizacionDTO {

    private Integer codCredito;
    private Integer codCuota;
    private BigDecimal capital;
    private BigDecimal interes;
    private BigDecimal montoCuota;
    private BigDecimal capitalRestante;
    private Date fechaPlanificadaPago;
    private String estado;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TablaAmortizacionDTO other = (TablaAmortizacionDTO) obj;
        if (codCredito == null) {
            if (other.codCredito != null)
                return false;
        } else if (!codCredito.equals(other.codCredito))
            return false;
        if (codCuota == null) {
            if (other.codCuota != null)
                return false;
        } else if (!codCuota.equals(other.codCuota))
            return false;
        return true;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codCredito == null) ? 0 : codCredito.hashCode());
        result = prime * result + ((codCuota == null) ? 0 : codCuota.hashCode());
        return result;
    }
}
