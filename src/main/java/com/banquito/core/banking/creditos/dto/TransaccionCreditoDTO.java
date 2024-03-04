package com.banquito.core.banking.creditos.dto;

import lombok.Builder;
import lombok.Data;
import java.sql.Timestamp;

@Builder
@Data
public class TransaccionCreditoDTO {

    private Integer codTransaccionCredito;
    private Integer codCredito;
    private Integer codCuota;
    private Integer codInteresAcumulado;
    private String codTransaccion;
    private String numeroCuenta;
    private String estado;
    private String tipoPago;
    private Timestamp fechaPago;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TransaccionCreditoDTO other = (TransaccionCreditoDTO) obj;
        if (codTransaccionCredito == null) {
            if (other.codTransaccionCredito != null)
                return false;
        } else if (!codTransaccionCredito.equals(other.codTransaccionCredito))
            return false;
        return true;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codTransaccionCredito == null) ? 0 : codTransaccionCredito.hashCode());
        return result;
    }
}
