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
    @Column(name = "COD_CREDITO")
    private Integer codCredito;
    @Column(name = "COD_CUOTA")
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
