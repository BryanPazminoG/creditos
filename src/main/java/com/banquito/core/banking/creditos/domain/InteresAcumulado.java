package com.banquito.core.banking.creditos.domain;

import java.math.BigDecimal;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "INTERES_ACUMULADO")
public class InteresAcumulado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_INTERES_ACUMULADO", nullable = false)
    private Integer codInteresAcumulado;

    @Column(name = "COD_CREDITO", nullable = false)
    private Integer codCredito;

    @Column(name = "TASA_INTERES_VIGENTE", nullable = false, precision = 8, scale = 2)
    private BigDecimal tasaInteresVigente;

    @Column(name = "CAPITAL_PENDIENTE", nullable = false, precision = 18, scale = 2)
    private BigDecimal capitalPendiente;

    @Column(name = "INTERES_GENERADO", nullable = false, precision = 18, scale = 2)
    private BigDecimal interesGenerado;

    @Column(name = "FECHA_CREACION", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

    @Column(name = "ESTADO", nullable = false, length = 3)
    private String estado;

    @ManyToOne()
    @JoinColumn(name = "COD_CREDITO", insertable = false, updatable = false)
    private Credito credito;

    public InteresAcumulado() {
    }

    public InteresAcumulado(Integer codInteresAcumulado) {
        this.codInteresAcumulado = codInteresAcumulado;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codInteresAcumulado == null) ? 0 : codInteresAcumulado.hashCode());
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
        InteresAcumulado other = (InteresAcumulado) obj;
        if (codInteresAcumulado == null) {
            if (other.codInteresAcumulado != null)
                return false;
        } else if (!codInteresAcumulado.equals(other.codInteresAcumulado))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "InteresAcumulado [codInteresAcumulado=" + codInteresAcumulado + ", codCredito=" + codCredito
                + ", tasaInteresVigente=" + tasaInteresVigente + ", capitalPendiente=" + capitalPendiente
                + ", interesGenerado=" + interesGenerado + ", fechaCreacion=" + fechaCreacion + ", estado=" + estado
                + ", credito=" + credito + "]";
    }


}