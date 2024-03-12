package com.banquito.core.banking.creditos.dto.Builder;

import com.banquito.core.banking.creditos.domain.CreditoInterviniente;
import com.banquito.core.banking.creditos.domain.CreditoIntervinientePK;
import com.banquito.core.banking.creditos.dto.CreditoIntervinienteDTO;

public class CreditoIntervinienteBuilder {

    public static CreditoIntervinienteDTO toDTO(CreditoInterviniente creditoInterviniente) {

        CreditoIntervinienteDTO dto = CreditoIntervinienteDTO.builder()
                .codCredito(creditoInterviniente.getPK().getCodCredito())
                .codCliente(creditoInterviniente.getPK().getCodCliente())
                .tipo(creditoInterviniente.getTipo())
                .build();
        return dto;
    }

    public static CreditoInterviniente toCreditoInterviniente(CreditoIntervinienteDTO dto) {

        CreditoInterviniente creditoInterviniente = new CreditoInterviniente();
        CreditoIntervinientePK PK = new CreditoIntervinientePK();
        PK.setCodCredito(dto.getCodCredito());
        PK.setCodCliente(dto.getCodCliente());
        creditoInterviniente.setPK(PK);
        creditoInterviniente.setTipo(dto.getTipo());

        return creditoInterviniente;
    }
}
