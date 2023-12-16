package com.banquito.core.banking.creditos.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "TASA_INTERES")
public class TasaInteres {
    @Id
    @Column(name = "COD_TASA_INTERES", nullable = false, length = 8)
    private String codTasaInteres;

    @Column(name = "TIPO_TASA_INTERES", nullable = false, length = 3)
    private String tipoTasaInteres;

    @Column(name = "NOMBRE", nullable = false, length = 50)
    private String nombre;

    @Column(name = "TASA_MINIMA", nullable = false, precision = 5, scale = 2)
    private BigDecimal tasaMinima;

    @Column(name = "TASA_MAXIMA", nullable = false, precision = 5, scale = 2)
    private BigDecimal tasaMaxima;

    @Column(name = "FECHA_ULTIMO_CAMBIO", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp fechaUltimoCambio;

    @Version
    private Long version;

    public TasaInteres() {
    }

    public TasaInteres(String codTasaInteres) {
        this.codTasaInteres = codTasaInteres;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codTasaInteres == null) ? 0 : codTasaInteres.hashCode());
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
        TasaInteres other = (TasaInteres) obj;
        if (codTasaInteres == null) {
            if (other.codTasaInteres != null)
                return false;
        } else if (!codTasaInteres.equals(other.codTasaInteres))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TasaInteres [codTasaInteres=" + codTasaInteres + ", tipoTasaInteres=" + tipoTasaInteres + ", nombre="
                + nombre + ", tasaMinima=" + tasaMinima + ", tasaMaxima=" + tasaMaxima + ", fechaUltimoCambio="
                + fechaUltimoCambio + ", version=" + version + "]";
    }
}