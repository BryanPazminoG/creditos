package com.banquito.core.banking.creditos.service;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.banquito.core.banking.creditos.dao.TipoCreditoRepository;
import com.banquito.core.banking.creditos.domain.TipoCredito;
import com.banquito.core.banking.creditos.service.exeption.CreateException;

@Service
public class TipoCreditoService {
    private final TipoCreditoRepository tipoCreditoRepository;

    public TipoCreditoService(TipoCreditoRepository tipoCreditoRepository) {
        this.tipoCreditoRepository = tipoCreditoRepository;
    }

    public Optional<TipoCredito> getById(Integer id) {
        return this.tipoCreditoRepository.findById(id);
    }

    public Iterable<TipoCredito> listAll() {
        return this.tipoCreditoRepository.findAll();
    }

    public TipoCredito create(TipoCredito tipoCredito) {
        try {
            return this.tipoCreditoRepository.save(tipoCredito);
        } catch (Exception e) {
            throw new CreateException("Ocurrio un error al crear el Tipo Credito: " + tipoCredito.toString(), e);
        }
    }

    public void delete(Integer id) {
        try {
            Optional<TipoCredito> tipoCredito = getById(id);
            if (tipoCredito.isPresent()) {
                this.tipoCreditoRepository.delete(tipoCredito.get());
            } else {
                throw new RuntimeException("El Tipo Credito con id" + id + " no existe");
            }
        } catch (Exception e) {
            throw new CreateException("Ocurrio un error al eliminar el Tipo Credito, error: " + e.getMessage(), e);
        }
    }

    public TipoCredito update(TipoCredito tipoCreditoUpdate) {
        try {
            Optional<TipoCredito> tipoCredito = getById(tipoCreditoUpdate.getCodTipoCredito());
            if (tipoCredito.isPresent()) {
                return create(tipoCreditoUpdate);
            } else {
                throw new RuntimeException(
                        "El Tipo Credito con id" + tipoCreditoUpdate.getCodTipoCredito() + " no existe");
            }
        } catch (Exception e) {
            throw new CreateException("Ocurrio un error al actualizar la Tipo Credito, error: " + e.getMessage(), e);
        }
    }
}
