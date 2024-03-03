package com.banquito.core.banking.creditos.domain;

import java.math.BigDecimal;
import java.sql.Date; /***********POSIBLE ERROR*************/

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "TABLA_AMORTIZACION")
public class TablaAmortizacion {

    @EmbeddedId
    private TablaAmortizacionPK PK;

    @Column(name = "CAPITAL", nullable = false, precision = 18, scale = 2)
    private BigDecimal capital;

    @Column(name = "INTERES", nullable = false, precision = 18, scale = 2)
    private BigDecimal interes;

    @Column(name = "MONTO_CUOTA", nullable = false, precision = 18, scale = 2)
    private BigDecimal montoCuota;

    @Column(name = "CAPITAL_RESTANTE", nullable = false, precision = 18, scale = 2)
    private BigDecimal capitalRestante;

    @Column(name = "FECHA_PLANIFICADA_PAGO", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaPlanificadaPago;

    @Column(name = "FECHA_ULTIMO_CAMBIO", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp fechaUltimoCambio;

    @Column(name = "ESTADO", nullable = false, length = 3)
    private String estado;

    @ManyToOne()
    @JoinColumn(name = "COD_CREDITO", updatable = false, insertable = false)
    private Credito credito;

    @Version
    private Long version;

    public TablaAmortizacion() {
    }

    public TablaAmortizacion(TablaAmortizacionPK pK) {
        PK = pK;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((PK == null) ? 0 : PK.hashCode());
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
        TablaAmortizacion other = (TablaAmortizacion) obj;
        if (PK == null) {
            if (other.PK != null)
                return false;
        } else if (!PK.equals(other.PK))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TablaAmortizacion [PK=" + PK + ", capital=" + capital + ", interes=" + interes + ", montoCuota="
                + montoCuota + ", capitalRestante=" + capitalRestante + ", fechaPlanificadaPago=" + fechaPlanificadaPago
                + ", fechaUltimoCambio=" + fechaUltimoCambio + ", estado=" + estado + ", credito=" + credito
                + ", version=" + version + "]";
    }
}