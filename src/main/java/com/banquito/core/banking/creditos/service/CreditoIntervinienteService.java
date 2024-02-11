package com.banquito.core.banking.creditos.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.banquito.core.banking.creditos.dao.CreditoIntervinienteRepository;
import com.banquito.core.banking.creditos.domain.CreditoInterviniente;
import com.banquito.core.banking.creditos.domain.CreditoIntervinientePK;
import com.banquito.core.banking.creditos.dto.CreditoIntervinienteDTO;
import com.banquito.core.banking.creditos.dto.Builder.CreditoIntervinienteBuilder;
import com.banquito.core.banking.creditos.service.exeption.CreateException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CreditoIntervinienteService {
    private final CreditoIntervinienteRepository creditoIntervinienteRepository;

    public CreditoIntervinienteService(CreditoIntervinienteRepository creditoIntervinienteRepository) {
        this.creditoIntervinienteRepository = creditoIntervinienteRepository;
    }

    public CreditoIntervinienteDTO obtenerPorId(Integer codCredito, String identificacion) {
        CreditoIntervinientePK creditoIntervinientePK = new CreditoIntervinientePK(codCredito, identificacion);
        Optional<CreditoInterviniente> creditoInterviniente = this.creditoIntervinienteRepository
                .findById(creditoIntervinientePK);
        if (creditoInterviniente.isPresent()) {
            log.info("Credito Interviniente encotrado");
            return CreditoIntervinienteBuilder.toDTO(creditoInterviniente.get());
        } else {
            throw new RuntimeException(
                    "No se han encontrado el interviniente" + identificacion + " en el credito " + codCredito);
        }
    }

    public CreditoIntervinienteDTO crear(CreditoIntervinienteDTO dto) {
        try {
            CreditoInterviniente creditoInterviniente = CreditoIntervinienteBuilder.toCreditoInterviniente(dto);
            this.creditoIntervinienteRepository.save(creditoInterviniente);
            log.info("El credito Interviniente : {} se ha creado ", dto);
            return dto;
        } catch (Exception e) {
            throw new CreateException(
                    "Ocurrio un error al crear el Credito Interviniente: " + dto.toString(), e);
        }
    }

    public void eliminar(Integer codCredito, String identificacionCliente) {
        try {
            CreditoIntervinientePK PK = new CreditoIntervinientePK(codCredito, identificacionCliente);
            Optional<CreditoInterviniente> creditoInterviniente = this.creditoIntervinienteRepository.findById(PK);
            if (creditoInterviniente.isPresent()) {
                log.info("Se encontro el credito Interviniente");
                this.creditoIntervinienteRepository.delete(creditoInterviniente.get());
                log.info("El credito Interviniente se ha eliminado ");
            } else {
                throw new RuntimeException(
                        "El Credito Interviniente con id " + codCredito + " - " + identificacionCliente + "no existe");
            }
        } catch (Exception e) {
            throw new CreateException("Ocurrio un error al eliminar el Credito Interviniente, error: " + e.getMessage(),
                    e);
        }
    }

    public CreditoIntervinienteDTO actualizar(CreditoIntervinienteDTO dto) {
        try {
            CreditoIntervinienteDTO creditoInterviniente = obtenerPorId(dto.getCodCredito(), dto.getIdentificacionCliente());
            if (creditoInterviniente != null) {
                log.info("Se encontro el credito Interviniente");
                return crear(creditoInterviniente);
            }  else {
                throw new RuntimeException(
                        "El Credito Interviniente con id " + dto.getCodCredito() + " - " + dto.getIdentificacionCliente() + "no existe");
            }
        } catch (Exception e) {
            throw new CreateException("Ocurrio un error al actualizar el Credito Interviniente, error: " + e.getMessage(),
                    e);
        }
    }
}
