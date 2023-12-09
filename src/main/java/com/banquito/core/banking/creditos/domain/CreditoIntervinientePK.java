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
    @Column(name="cod_credito", nullable = false)
    private Integer codCredito;
    @Column(name="cod_cliente", nullable = false)
    private Integer codCliente;

    public CreditoIntervinientePK() {
    }
    public CreditoIntervinientePK(Integer codCredito, Integer codCliente) {
        this.codCredito = codCredito;
        this.codCliente = codCliente;
    }
    @Override
    public String toString() {
        return "CreditoIntervinientePK [codCredito=" + codCredito + ", codCliente=" + codCliente + "]";
    }
}
