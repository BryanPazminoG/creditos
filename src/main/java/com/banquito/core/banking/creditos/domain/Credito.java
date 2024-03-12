package com.banquito.core.banking.creditos.domain;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

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
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "CREDITO")
public class Credito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_CREDITO", nullable = false)
    private Integer codCredito;

    @Column(name = "COD_TIPO_CREDITO", nullable = false)
    private Integer codTipoCredito;

    @Column(name = "COD_TRANSACCION", nullable = true, length = 64)
    private String codTransaccion;

    @Column(name = "COD_CLIENTE", nullable = false, length = 64)
    private String codCliente;

    @Column(name = "NUMERO_OPERACION", nullable = false, length = 64)
    private String numeroOperacion;

    @Column(name = "NUMERO_CUENTA", nullable = false, length = 10)
    private String numeroCuenta;

    @Column(name = "MONTO", nullable = false, precision = 18, scale = 2)
    private BigDecimal monto;

    @Column(name = "PLAZO", nullable = false)
    private Integer plazo;

    @Column(name = "NUMERO_CUOTAS", nullable = false)
    private Integer numeroCuotas;

    @Column(name = "CUOTAS_PAGADAS", nullable = true)
    private Integer cuotasPagadas;

    @Column(name = "CAPITAL_PENDIENTE", nullable = false, precision = 18, scale = 2)
    private BigDecimal capitalPendiente;

    @Column(name = "TASA_INTERES", nullable = false, precision = 5, scale = 2)
    private BigDecimal tasaInteres;

    @Column(name = "CANAL_TRANSACCION", nullable = false, length = 3)
    private String canalTransaccion;

    @Column(name = "ESTADO", nullable = false, length = 3)
    private String estado;

    @Column(name = "FECHA_CREACION", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

    @Column(name = "FECHA_APROBACION", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp fechaAprobacion;

    @Column(name = "FECHA_DESEMBOLSO", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp fechaDesembolso;

    @Column(name = "FECHA_CIERRE", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date fechaCierre;

    @Column(name = "FECHA_ULTIMO_CAMBIO", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp fechaUltimoCambio;

    @ManyToOne()
    @JoinColumn(name = "COD_TIPO_CREDITO", nullable = false, updatable = false, insertable = false)
    private TipoCredito tipoCredito;

    @Version
    private Long version;

    public Credito() {
    }

    public Credito(Integer codCredito) {
        this.codCredito = codCredito;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codCredito == null) ? 0 : codCredito.hashCode());
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
        Credito other = (Credito) obj;
        if (codCredito == null) {
            if (other.codCredito != null)
                return false;
        } else if (!codCredito.equals(other.codCredito))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Credito [codCredito=" + codCredito + ", codTipoCredito=" + codTipoCredito + ", codTransaccion="
                + codTransaccion + ", codCliente=" + codCliente + ", numeroOperacion=" + numeroOperacion
                + ", numeroCuenta=" + numeroCuenta + ", monto=" + monto + ", plazo=" + plazo + ", numeroCuotas="
                + numeroCuotas + ", cuotasPagadas=" + cuotasPagadas + ", capitalPendiente=" + capitalPendiente
                + ", tasaInteres=" + tasaInteres + ", canalTransaccion=" + canalTransaccion + ", estado=" + estado
                + ", fechaCreacion=" + fechaCreacion + ", fechaAprobacion=" + fechaAprobacion + ", fechaDesembolso="
                + fechaDesembolso + ", fechaCierre=" + fechaCierre + ", fechaUltimoCambio=" + fechaUltimoCambio
                + ", tipoCredito=" + tipoCredito + ", version=" + version + "]";
    }

}