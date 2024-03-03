package com.banquito.core.banking.creditos.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import com.banquito.core.banking.creditos.dao.TasaInteresRepository;
import com.banquito.core.banking.creditos.domain.TasaInteres;
import com.banquito.core.banking.creditos.dto.TasaInteresDTO;
import com.banquito.core.banking.creditos.dto.Builder.TasaInteresBuilder;
import com.banquito.core.banking.creditos.service.exeption.CreateException;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TasaInteresService {
    private final TasaInteresRepository tasaInteresRepository;

    public TasaInteresService(TasaInteresRepository tasaInteresRepository) {
        this.tasaInteresRepository = tasaInteresRepository;
    }

    public TasaInteresDTO obtenerPorId(String codTasaInteres) {
        Optional<TasaInteres> tasaInteres = this.tasaInteresRepository.findById(codTasaInteres);
        if(tasaInteres.isPresent()){
            log.info("Se obtuvo la tasa de interes con el id {}", codTasaInteres);
            return TasaInteresBuilder.toDTO(tasaInteres.get());
        }else{
            throw new RuntimeException("La tasa de interes con id" + codTasaInteres + " no existe");
        }
    }

    public List<TasaInteresDTO> listar() {
        List<TasaInteresDTO> listDTO = new ArrayList<>();
        for(TasaInteres tasaInteres : this.tasaInteresRepository.findAll()){
            listDTO.add(TasaInteresBuilder.toDTO(tasaInteres));
        }
        return listDTO;
    }

    public List<TasaInteresDTO> listarActivos() {
        List<TasaInteresDTO> listDTO = new ArrayList<>();
        for(TasaInteres tasaInteres : this.tasaInteresRepository.findByEstadoOrderByFechaCreacion("ACT")){
            listDTO.add(TasaInteresBuilder.toDTO(tasaInteres));
        }
        return listDTO;
    }

    @Transactional
    public String crear(TasaInteresDTO dto) {
        try {
            TasaInteres tasaInteres = TasaInteresBuilder.toTasaInteres(dto);
            this.tasaInteresRepository.save(tasaInteres);
            log.info("Se ha creado la tasa de interes exitosamente: {}", dto);
            return dto.getCodTasaInteres();
        } catch (Exception e) {
            throw new CreateException("Ocurrio un error al crear la tasaInteres: " + dto.toString(), e);
        }
    }

    @Transactional
    public TasaInteresDTO cambiarEstado(String codTasaInteres, String estado) {
        try {
            if("ACT".equals(estado) || "INA".equals(estado) ){
                Optional<TasaInteres> tasaInteres = this.tasaInteresRepository.findById(codTasaInteres);
                if(tasaInteres.isPresent()){
                    log.info("Se obtuvo la tasa de interes con el id {}", codTasaInteres);
                    tasaInteres.get().setEstado(estado);
                    this.tasaInteresRepository.save(tasaInteres.get());
                    log.info("El estado de la tasa interes se ha actalizado correctamente a {}", estado);
                    return TasaInteresBuilder.toDTO(tasaInteres.get());
                }else{
                    throw new RuntimeException("La tasa de interes con id" + codTasaInteres + " no existe");
                }
            }else{
                log.info("El estado {} es invalido", estado);
                throw new RuntimeException("Estado ingresado invalido: " + estado);
            }
        } catch (Exception e) {
            throw new CreateException("Ocurrio un error al actualizar la tasaInteres, error: " + e.getMessage(), e);
        }
    }
}
