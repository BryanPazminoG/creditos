package com.banquito.core.banking.creditos.domain;

import java.math.BigDecimal;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
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
@Table(name = "credito_tabla_pagos")
public class CreditoTablaPagos {
    
    @EmbeddedId
    private CreditoTablaPagosPK PK;
    
    @Column(name = "capital", nullable = false, precision = 18, scale = 2 )
    private BigDecimal capital;
    
    @Column(name = "interes", nullable = false, precision = 18, scale = 2 )
    private BigDecimal interes;
    
    @Column(name = "monto_cuota", nullable = false, precision = 18, scale = 2 )
    private BigDecimal montoCuota;
    
    @Column(name = "capital_restante", nullable = false, precision = 18, scale = 2 )
    private BigDecimal capitalRestante;
    
    @Column(name = "fecha_planificada_pago", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaPlanificadaPago;
    
    @Column(name = "estado", nullable = false, length = 3)
    private String estado;
    
    @Column(name = "fecha_pago_efectivo", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaPagoEfectivo;
    
    @Column(name = "transaccion_pago", nullable = false, length = 64)
    private String transaccionPagoj;

    @ManyToOne()
    @JoinColumn(name = "cod_credito", updatable = false, insertable = false)
    private Credito credito;
    
    public CreditoTablaPagos() {
    }

    public CreditoTablaPagos(CreditoTablaPagosPK pK) {
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
        CreditoTablaPagos other = (CreditoTablaPagos) obj;
        if (PK == null) {
            if (other.PK != null)
                return false;
        } else if (!PK.equals(other.PK))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CreditoTablaPagos [PK=" + PK + ", capital=" + capital + ", interes=" + interes + ", montoCuota="
                + montoCuota + ", capitalRestante=" + capitalRestante + ", fechaPlanificadaPago=" + fechaPlanificadaPago
                + ", estado=" + estado + ", fechaPagoEfectivo=" + fechaPagoEfectivo + ", transaccionPagoj="
                + transaccionPagoj + ", credito=" + credito + "]";
    }

}
