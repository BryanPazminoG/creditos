package com.banquito.core.banking.creditos.domain;

import java.math.BigDecimal;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TRANSACCION_CREDITO")
public class TransaccionCredito {
        @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_TRANSACCION_CREDITO", nullable = false)
    private Integer codTransaccionCredito;

    @Column(name = "COD_CREDITO", nullable = false)
    private Integer codCredito;

    @Column(name = "COD_CUOTA", nullable = false)
    private Integer codCuota;

    @Column(name = "COD_TRANSACCION", nullable = false, length = 64)
    private String codTransaccion;

    @Column(name = "INTERES_TOTAL", nullable = false, precision = 18, scale = 2)
    private BigDecimal interesTotal;

    @Column(name = "NUMERO_CUENTA", nullable = false, length = 10)
    private String numeroCuenta;

    @Column(name = "TIPO_PAGO", nullable = true, length = 3)
    private String tipoPago;

    @Column(name = "ESTADO", nullable = false, length = 3)
    private String estado;

    @Column(name = "FECHA_CREACION", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

    @Version
    private Long version;

    public TransaccionCredito() {
    }

    public TransaccionCredito(Integer codTransaccionCredito) {
        this.codTransaccionCredito = codTransaccionCredito;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codTransaccionCredito == null) ? 0 : codTransaccionCredito.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TransaccionCredito other = (TransaccionCredito) obj;
        if (codTransaccionCredito == null) {
            if (other.codTransaccionCredito != null)
                return false;
        } else if (!codTransaccionCredito.equals(other.codTransaccionCredito))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TransaccionCredito [codTransaccionCredito=" + codTransaccionCredito + ", codCredito=" + codCredito
                + ", codCuota=" + codCuota + ", codTransaccion=" + codTransaccion + ", interesTotal=" + interesTotal
                + ", numeroCuenta=" + numeroCuenta + ", tipoPago=" + tipoPago + ", estado=" + estado
                + ", fechaCreacion=" + fechaCreacion + ", version=" + version + "]";
    }


}
