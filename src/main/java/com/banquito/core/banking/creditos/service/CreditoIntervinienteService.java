package com.banquito.core.banking.creditos.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.banquito.core.banking.creditos.dao.CreditoIntervinienteRepository;
import com.banquito.core.banking.creditos.domain.CreditoInterviniente;
import com.banquito.core.banking.creditos.domain.CreditoIntervinientePK;
import com.banquito.core.banking.creditos.service.exeption.CreateException;

@Service
public class CreditoIntervinienteService {
    private final CreditoIntervinienteRepository creditoIntervinienteRepository;

    public CreditoIntervinienteService(CreditoIntervinienteRepository creditoIntervinienteRepository) {
        this.creditoIntervinienteRepository = creditoIntervinienteRepository;
    }

    public Optional<CreditoInterviniente> getById(Integer codCredito, Integer codCliente) {
        CreditoIntervinientePK creditoIntervinientePK = new CreditoIntervinientePK(codCredito, codCliente);
        return this.creditoIntervinienteRepository.findById(creditoIntervinientePK);
    }

    public CreditoInterviniente create(CreditoInterviniente creditoInterviniente) {
        try {
            return this.creditoIntervinienteRepository.save(creditoInterviniente);
        } catch (Exception e) {
            throw new CreateException(
                    "Ocurrio un error al crear el Credito Interviniente: " + creditoInterviniente.toString(), e);
        }
    }

    public void delete(Integer codCredito, Integer codCliente) {
        try {
            Optional<CreditoInterviniente> creditoInterviniente = getById(codCredito, codCliente);
            if (creditoInterviniente.isPresent()) {
                this.creditoIntervinienteRepository.delete(creditoInterviniente.get());
            } else {
                throw new RuntimeException(
                        "El Credito Interviniente con id " + codCredito + " - " + codCliente + "no existe");
            }
        } catch (Exception e) {
            throw new CreateException("Ocurrio un error al eliminar el Credito Interviniente, error: " + e.getMessage(), e);
        }
    }

    public CreditoInterviniente update(CreditoInterviniente creditoIntervinienteUpdate) {
        try {
            Integer codCredito = creditoIntervinienteUpdate.getPK().getCodCredito();
            Integer codCliente = creditoIntervinienteUpdate.getPK().getCodCliente();
            Optional<CreditoInterviniente> creditoInterviniente = getById(codCredito, codCliente);
            if (creditoInterviniente.isPresent()) {
                return create(creditoInterviniente.get());
            } else {
                throw new RuntimeException("El Credito Interviniente con id " + codCredito + " - " + codCliente + "no existe");
            }
        } catch (Exception e) {
            throw new CreateException("Ocurrio un error al eliminar el Credito Interviniente, error: " + e.getMessage(), e);
        }
    }
}
