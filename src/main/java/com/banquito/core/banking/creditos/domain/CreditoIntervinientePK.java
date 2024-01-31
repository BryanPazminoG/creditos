package com.banquito.core.banking.creditos.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class CreditoIntervinientePK implements Serializable {
    @Column(name="COD_CREDITO", nullable = false)
    private Integer codCredito;
    @Column(name="IDENTIFICACION_CLIENTE", nullable = false, length = 64)
    private String identificacionCliente;

    public CreditoIntervinientePK() {
    }

    public CreditoIntervinientePK(Integer codCredito, String identificacionCliente) {
        this.codCredito = codCredito;
        this.identificacionCliente = identificacionCliente;
    }

    @Override
    public String toString() {
        return "CreditoIntervinientePK [codCredito=" + codCredito + ", identificacionCliente=" + identificacionCliente
                + "]";
    }

}
