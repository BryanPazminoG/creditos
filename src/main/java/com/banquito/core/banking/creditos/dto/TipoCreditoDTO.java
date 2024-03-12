package com.banquito.core.banking.creditos.dto;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Builder
@Data
public class TipoCreditoDTO {

    private Integer codTipoCredito;
    private String codTasaInteres;
    private String nombre;
    private String descripcion;
    private String tipoCliente;
    private String unidadPlazo;
    private Integer plazoMinimo;
    private Integer plazoMaximo;
    private BigDecimal montoMinimo;
    private BigDecimal montoMaximo;
    private String estado;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TipoCreditoDTO other = (TipoCreditoDTO) obj;
        if (codTipoCredito == null) {
            if (other.codTipoCredito != null)
                return false;
        } else if (!codTipoCredito.equals(other.codTipoCredito))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codTipoCredito == null) ? 0 : codTipoCredito.hashCode());
        return result;
    }   
}
