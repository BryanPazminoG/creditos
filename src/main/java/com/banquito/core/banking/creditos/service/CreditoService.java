package com.banquito.core.banking.creditos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.banquito.core.banking.creditos.dao.CreditoRepository;
import com.banquito.core.banking.creditos.domain.Credito;
import com.banquito.core.banking.creditos.service.exeption.CreateException;

import jakarta.transaction.Transactional;

@Service
public class CreditoService {

    private  final CreditoRepository creditoRepository;
    
    public CreditoService(CreditoRepository creditoRepository) {
        this.creditoRepository = creditoRepository;
    }

    public Optional<Credito> getById(Integer id) {
        return this.creditoRepository.findById(id);
    }

    @Transactional
    public Credito create(Credito credito) {
        try {
            return this.creditoRepository.save(credito);
        } catch (Exception e) {
            throw new CreateException("Ocurrio un error al crear el Credito: " + credito.toString(), e);
        }
    }

    @Transactional
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

    public List<Credito> BuscarPorCliente(String identificacion) {
        return creditoRepository.findByIdentificacionClienteOrderByFechaCreacion(identificacion);
    }
}
