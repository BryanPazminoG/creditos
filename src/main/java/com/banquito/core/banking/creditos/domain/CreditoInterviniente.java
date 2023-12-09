package com.banquito.core.banking.creditos.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "credito_interviniente")
public class CreditoInterviniente {
    @EmbeddedId
    private CreditoIntervinientePK PK;

    @Column(name = "tipo", nullable = false, length = 3)
    private String tipo;

    @ManyToOne()
    @JoinColumn(name = "cod_credito", nullable = false, updatable = false, insertable = false)
    private Credito credito;

    public CreditoInterviniente() {
    }

    public CreditoInterviniente(CreditoIntervinientePK pK) {
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
        CreditoInterviniente other = (CreditoInterviniente) obj;
        if (PK == null) {
            if (other.PK != null)
                return false;
        } else if (!PK.equals(other.PK))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CreditoInterviniente [PK=" + PK + ", tipo=" + tipo + ", credito=" + credito + "]";
    }    
}