package com.banquito.core.banking.creditos.domain;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class CreditoTablaPagosPK implements Serializable {
    @Column(name = "cod_credito")
    private Integer codCredito;
    @Column(name = "cod_cuota")
    private Integer codCuota;

    public CreditoTablaPagosPK() {
    }
    
    public CreditoTablaPagosPK(Integer codCredito, Integer codCuota) {
        this.codCredito = codCredito;
        this.codCuota = codCuota;
    }

    @Override
    public String toString() {
        return "CreditoTablaPagosPK [codCredito=" + codCredito + ", codCuota=" + codCuota + "]";
    }  
}
