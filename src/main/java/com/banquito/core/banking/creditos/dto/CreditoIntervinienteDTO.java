package com.banquito.core.banking.creditos.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreditoIntervinienteDTO {

    private Integer codCredito;
    private String codCliente;
    private String tipo;
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CreditoIntervinienteDTO other = (CreditoIntervinienteDTO) obj;
        if (codCredito == null) {
            if (other.codCredito != null)
                return false;
        } else if (!codCredito.equals(other.codCredito))
            return false;
        if (codCliente == null) {
            if (other.codCliente != null)
                return false;
        } else if (!codCliente.equals(other.codCliente))
            return false;
        return true;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codCredito == null) ? 0 : codCredito.hashCode());
        result = prime * result + ((codCliente == null) ? 0 : codCliente.hashCode());
        return result;
    }
}
