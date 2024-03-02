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
@Table(name = "TIPO_CREDITO")
public class TipoCredito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_TIPO_CREDITO", nullable = false)
    private Integer codTipoCredito;

    @Column(name = "COD_TASA_INTERES", nullable = false, length = 8)
    private String codTasaInteres;

    @Column(name = "NOMBRE", nullable = false, length = 50)
    private String nombre;

    @Column(name = "DESCRIPCION", nullable = false, length = 500)
    private String descripcion;

    @Column(name = "TIPO_CLIENTE", nullable = false, length = 3)
    private String tipoCliente;

    @Column(name = "UNIDAD_PLAZO", nullable = false, length = 3)
    private String unidadPlazo;

    @Column(name = "PLAZO_MINIMO", nullable = false)
    private Integer plazoMinimo;

    @Column(name = "PLAZO_MAXIMO", nullable = false)
    private Integer plazoMaximo;

    @Column(name = "MONTO_MINIMO", nullable = false, precision = 18, scale = 2)
    private BigDecimal montoMinimo;

    @Column(name = "MONTO_MAXIMO", nullable = false, precision = 18, scale = 2)
    private BigDecimal montoMaximo;

    @Column(name = "ESTADO", nullable = false, length = 3)
    private String estado;

    @Column(name = "FECHA_CREACION", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

    @Column(name = "FECHA_ULTIMO_CAMBIO", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp fechaUltimoCambio;

    @ManyToOne()
    @JoinColumn(name = "COD_TASA_INTERES", insertable = false, updatable = false)
    private TasaInteres tasaInteres;

    @Version
    private Long version;

    public TipoCredito() {
    }

    public TipoCredito(Integer codTipoCredito) {
        this.codTipoCredito = codTipoCredito;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codTipoCredito == null) ? 0 : codTipoCredito.hashCode());
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
        TipoCredito other = (TipoCredito) obj;
        if (codTipoCredito == null) {
            if (other.codTipoCredito != null)
                return false;
        } else if (!codTipoCredito.equals(other.codTipoCredito))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TipoCredito [codTipoCredito=" + codTipoCredito + ", codTasaInteres=" + codTasaInteres + ", nombre="
                + nombre + ", descripcion=" + descripcion + ", tipoCliente=" + tipoCliente + ", unidadPlazo="
                + unidadPlazo + ", plazoMinimo=" + plazoMinimo + ", plazoMaximo=" + plazoMaximo + ", montoMinimo="
                + montoMinimo + ", montoMaximo=" + montoMaximo + ", estado=" + estado + ", fechaCreacion="
                + fechaCreacion + ", fechaUltimoCambio=" + fechaUltimoCambio + ", tasaInteres=" + tasaInteres
                + ", version=" + version + "]";
    }  
}