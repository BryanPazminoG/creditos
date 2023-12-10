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
@Table(name = "credito")
public class Credito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_credito", nullable = false)
    private Integer codCredito;

    @Column(name = "cod_tipo_credito", nullable = true)
    private Integer codTipoCredito;

    @Column(name = "cod_cliente", nullable = true)
    private Integer codCliente;

    @Column(name = "numero_operacion", nullable = false, length = 8)
    private String numeroOperacion;

    @Column(name = "fecha_creacion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp fechaCreacion;

    @Column(name = "monto", nullable = false, precision = 18, scale = 2)
    private BigDecimal monto;

    @Column(name = "plazo", nullable = false)
    private Integer plazo;

    @Column(name = "tasa_interes", nullable = false, precision = 5, scale = 2)
    private BigDecimal tasaInteres;

    @Column(name = "estado", nullable = false, length = 3)
    private String estado;

    @Column(name = "fecha_desembolso", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp fechaDesembolso;

    @Column(name = "fecha_ultimo_pago", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date fechaUltimoPago;

    @Column(name = "capital_pendiente", nullable = false, precision = 18, scale = 2)
    private BigDecimal capitalPendiente;

    @Column(name = "fecha_ultimo_cambio", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp fechaUltimoCambio;

    @ManyToOne()
    @JoinColumn(name = "cod_tipo_credito", nullable = false, updatable = false, insertable = false)
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
        return "Credito [codCredito=" + codCredito + ", codTipoCredito=" + codTipoCredito + ", codCliente=" + codCliente
                + ", numeroOperacion=" + numeroOperacion + ", fechaCreacion=" + fechaCreacion + ", monto=" + monto
                + ", plazo=" + plazo + ", tasaInteres=" + tasaInteres + ", estado=" + estado + ", fechaDesembolso="
                + fechaDesembolso + ", fechaUltimoPago=" + fechaUltimoPago + ", capitalPendiente=" + capitalPendiente
                + ", fechaUltimoCambio=" + fechaUltimoCambio + ", tipoCredito=" + tipoCredito + "]";
    }
}