package com.banquito.core.banking.creditos.dto;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Builder
@Data
public class CreditoDTO {

    private Integer codCredito;
    private Integer codTipoCredito;
    private String identificacionCliente;
    private String tipoCliente;
    private String numeroCuenta;
    private String numeroOperacion;
    private Timestamp fechaCreacion;
    private BigDecimal monto;
    private Integer plazo;
    private BigDecimal tasaInteres;
    private String estado;
    private Timestamp fechaDesembolso;
    private Date fechaUltimoPago;
    private BigDecimal capitalPendiente;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CreditoDTO other = (CreditoDTO) obj;
        if (codCredito == null) {
            if (other.codCredito != null)
                return false;
        } else if (!codCredito.equals(other.codCredito))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codCredito == null) ? 0 : codCredito.hashCode());
        return result;
    }
}
