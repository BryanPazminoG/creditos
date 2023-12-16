package com.banquito.core.banking.creditos.service;

// import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.banquito.core.banking.creditos.dao.TasaInteresRepository;
import com.banquito.core.banking.creditos.domain.TasaInteres;
import com.banquito.core.banking.creditos.service.exeption.CreateException;

@Service
public class TasaInteresService {
    private final TasaInteresRepository tasaInteresRepository;

    public TasaInteresService(TasaInteresRepository tasaInteresRepository) {
        this.tasaInteresRepository = tasaInteresRepository;
    }

    public Optional<TasaInteres> getById(String id) {
        return this.tasaInteresRepository.findById(id);
    }

    public Iterable<TasaInteres> listAll() {
        return this.tasaInteresRepository.findAll();
    }

    public TasaInteres create(TasaInteres tasaInteres) {
        try {
            return this.tasaInteresRepository.save(tasaInteres);
        } catch (Exception e) {
            throw new CreateException("Ocurrio un error al crear la tasaInteres: " + tasaInteres.toString(), e);
        }
    }

    public void delete(String id) {
        try {
            Optional<TasaInteres> tasaInteres = getById(id);
            if (tasaInteres.isPresent()) {
                this.tasaInteresRepository.delete(tasaInteres.get());
            } else {
                throw new RuntimeException("La tasa de interes con id" + id + " no existe");
            }
        } catch (Exception e) {
            throw new CreateException("Ocurrio un error al eliminar la tasaInteres, error: " + e.getMessage(), e);
        }
    }

    public TasaInteres update(TasaInteres tasaInteresUpdate) {
        try {
            Optional<TasaInteres> tasaInteres = getById(tasaInteresUpdate.getCodTasaInteres());
            if (tasaInteres.isPresent()) {
                return create(tasaInteresUpdate);
            } else {
                throw new RuntimeException("La tasa de interes con id" + tasaInteresUpdate.getCodTasaInteres() + " no existe");
            }
        } catch (Exception e) {
            throw new CreateException("Ocurrio un error al actualizar la tasaInteres, error: " + e.getMessage(), e);
        }
    }

}
