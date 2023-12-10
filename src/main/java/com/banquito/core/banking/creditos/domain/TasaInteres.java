package com.banquito.core.banking.creditos.domain;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tasa_interes")
public class TasaInteres {
    @Id
    @Column(name = "cod_tasa_interes", nullable = false, length = 8)
    private String codTasaInteres;

    @Column(name = "tipo_tasa_interes", nullable = false, length = 3)
    private String tipoTasaInteres;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "tasa_minima", nullable = false, precision = 5, scale = 2)
    private BigDecimal tasaMinima;

    @Column(name = "tasa_maxima", nullable = false, precision = 5, scale = 2)
    private BigDecimal tasaMaxima;

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
                + nombre + ", tasaMinima=" + tasaMinima + ", tasaMaxima=" + tasaMaxima + "]";
    }
}