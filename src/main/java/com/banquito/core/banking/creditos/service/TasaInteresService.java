package com.banquito.core.banking.creditos.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import com.banquito.core.banking.creditos.dao.TasaInteresRepository;
import com.banquito.core.banking.creditos.domain.TasaInteres;
import com.banquito.core.banking.creditos.dto.TasaInteresDTO;
import com.banquito.core.banking.creditos.dto.Builder.TasaInteresBuilder;
import com.banquito.core.banking.creditos.service.exeption.CreateException;
import com.banquito.core.banking.creditos.service.logica.ReglasNegocio;

import java.util.List;
import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TasaInteresService {
    private final TasaInteresRepository tasaInteresRepository;
    private final ReglasNegocio reglasNegocio;

    public TasaInteresService(TasaInteresRepository tasaInteresRepository) {
        this.tasaInteresRepository = tasaInteresRepository;
        this.reglasNegocio = new ReglasNegocio();
    }

    public TasaInteresDTO obtenerPorId(String id) {
        Optional<TasaInteres> tasaInteres = this.tasaInteresRepository.findById(id);
        if(tasaInteres.isPresent()){
            log.info("Se obtuvo la tasa de interes con el id {}", id);
            return TasaInteresBuilder.toDTO(tasaInteres.get());
        }else{
            throw new RuntimeException("La tasa de interes con id" + id + " no existe");
        }
    }

    public Double calcularTasaInteres(String codTasaInteres, Double monto, Integer plazo) {
        TasaInteresDTO tasaInteres = obtenerPorId(codTasaInteres);
        Double tasaMinima = tasaInteres.getTasaMinima().doubleValue();
        Double tasaMaxima = tasaInteres.getTasaMaxima().doubleValue();
        return reglasNegocio.CalcularTasaInteres(tasaMinima, tasaMaxima, monto, plazo);
    }

    public List<TasaInteresDTO> listar() {
        List<TasaInteresDTO> listDTO = new ArrayList<>();
        for(TasaInteres tasaInteres : this.tasaInteresRepository.findAll()){
            listDTO.add(TasaInteresBuilder.toDTO(tasaInteres));
        }
        return listDTO;
    }

    public TasaInteresDTO crear(TasaInteresDTO dto) {
        try {
            TasaInteres tasaInteres = TasaInteresBuilder.toTasaInteres(dto);
            this.tasaInteresRepository.save(tasaInteres);
            log.info("Se ha creado la tasa de interes exitosamente: {}", dto);
            return dto;
        } catch (Exception e) {
            throw new CreateException("Ocurrio un error al crear la tasaInteres: " + dto.toString(), e);
        }
    }

    public void eliminar(String id) {
        try {
            Optional<TasaInteres> tasaInteres = this.tasaInteresRepository.findById(id);
            if (tasaInteres.isPresent()) {
                this.tasaInteresRepository.delete(tasaInteres.get());
                log.info("La tasa de interes con el id {} se ha eliminado", id);
            } else {
                throw new RuntimeException("La tasa de interes con id" + id + " no existe");
            }
        } catch (Exception e) {
            throw new CreateException("Ocurrio un error al eliminar la tasaInteres, error: " + e.getMessage(), e);
        }
    }

    public TasaInteresDTO actualizar(TasaInteresDTO dto) {
        try {
            TasaInteresDTO tasaInteres = obtenerPorId(dto.getCodTasaInteres());
            if (tasaInteres != null) {
                return crear(tasaInteres);
            } else {
                throw new RuntimeException(
                        "La tasa de interes con id" + dto.getCodTasaInteres() + " no existe");
            }
        } catch (Exception e) {
            throw new CreateException("Ocurrio un error al actualizar la tasaInteres, error: " + e.getMessage(), e);
        }
    }

}
