package com.banquito.core.banking.creditos.dto.Builder;

import com.banquito.core.banking.creditos.domain.CreditoInterviniente;
import com.banquito.core.banking.creditos.domain.CreditoIntervinientePK;
import com.banquito.core.banking.creditos.dto.CreditoIntervinienteDTO;
import java.sql.Timestamp;
import java.util.Date;

public class CreditoIntervinienteBuilder {

    public static CreditoIntervinienteDTO toDTO(CreditoInterviniente creditoInterviniente) {

        CreditoIntervinienteDTO dto = CreditoIntervinienteDTO.builder()
                .codCredito(creditoInterviniente.getPK().getCodCredito())
                .identificacionCliente(creditoInterviniente.getPK().getIdentificacionCliente())
                .tipo(creditoInterviniente.getTipo())
                .build();
        return dto;
    }

    public static CreditoInterviniente toCreditoInterviniente(CreditoIntervinienteDTO dto) {

        CreditoInterviniente creditoInterviniente = new CreditoInterviniente();
        CreditoIntervinientePK PK = new CreditoIntervinientePK();
        PK.setCodCredito(dto.getCodCredito());
        PK.setIdentificacionCliente(dto.getIdentificacionCliente());
        creditoInterviniente.setPK(PK);
        creditoInterviniente.setTipo(dto.getTipo());

        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        creditoInterviniente.setFechaUltimoCambio(timestamp);

        return creditoInterviniente;
    }
}
