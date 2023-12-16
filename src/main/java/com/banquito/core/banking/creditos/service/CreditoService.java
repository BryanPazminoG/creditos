package com.banquito.core.banking.creditos.service;

import java.util.Optional;

import com.banquito.core.banking.creditos.dao.CreditoRepository;
import com.banquito.core.banking.creditos.domain.Credito;
import com.banquito.core.banking.creditos.service.exeption.CreateException;

public class CreditoService {

    private  final CreditoRepository creditoRepository;
    
    public CreditoService(CreditoRepository creditoRepository) {
        this.creditoRepository = creditoRepository;
    }

    public Optional<Credito> getById(Integer id) {
        return this.creditoRepository.findById(id);
    }

    // public Iterable<Credito> listAll() {
    //     return this.creditoRepository.findAll();
    // }

    public Credito create(Credito credito) {
        try {
            return this.creditoRepository.save(credito);
        } catch (Exception e) {
            throw new CreateException("Ocurrio un error al crear el Credito: " + credito.toString(), e);
        }
    }

    public void delete(Integer id) {
        try {
            Optional<Credito> credito = getById(id);
            if (credito.isPresent()) {
                this.creditoRepository.delete(credito.get());
            } else {
                throw new RuntimeException("El Credito con id" + id + " no existe");
            }
        } catch (Exception e) {
            throw new CreateException("Ocurrio un error al eliminar la Credito, error: " + e.getMessage(), e);
        }
    }

    public Credito update(Credito creditoUpdate) {
        try {
            Optional<Credito> credito = getById(creditoUpdate.getCodCredito());
            if (credito.isPresent()) {
                return create(creditoUpdate);
            } else {
                throw new RuntimeException(
                        "El Credito con id" + creditoUpdate.getCodCredito() + " no existe");
            }
        } catch (Exception e) {
            throw new CreateException("Ocurrio un error al actualizar el Credito, error: " + e.getMessage(), e);
        }
    }
}
