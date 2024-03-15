package com.banquito.core.banking.creditos.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.banquito.core.banking.creditos.dao.TasaInteresRepository;
import com.banquito.core.banking.creditos.domain.TasaInteres;
import com.banquito.core.banking.creditos.dto.TasaInteresDTO;
import com.banquito.core.banking.creditos.dto.Builder.TasaInteresBuilder;
import com.banquito.core.banking.creditos.service.exeption.CreateException;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class TasaInteresService {
    private final TasaInteresRepository tasaInteresRepository;

    public TasaInteresService(TasaInteresRepository tasaInteresRepository) {
        this.tasaInteresRepository = tasaInteresRepository;
    }
    
    public List<TasaInteresDTO> Listar() {
        List<TasaInteresDTO> listDTO = new ArrayList<>();
        for(TasaInteres tasaInteres : this.tasaInteresRepository.findAll()){
            listDTO.add(TasaInteresBuilder.toDTO(tasaInteres));
        }
        log.info("Se obtuvo la lista de tasa de interes: ", listDTO);
        return listDTO;
    }

    public TasaInteresDTO ObtenerPorId(String codTasaInteres) {
        Optional<TasaInteres> tasaInteres = this.tasaInteresRepository.findById(codTasaInteres);
        if(tasaInteres.isPresent()){
            log.info("Se obtuvo la tasa de interes con el id {}", codTasaInteres);
            return TasaInteresBuilder.toDTO(tasaInteres.get());
        }else{
            throw new RuntimeException("La tasa de interes con id" + codTasaInteres + " no existe");
        }
    }

    public List<TasaInteresDTO> ListarPorEstado(String estado) {
        if ("ACT".equals(estado) || "INA".equals(estado)) {
            List<TasaInteresDTO> listDTO = new ArrayList<>();
            for(TasaInteres tasaInteres : this.tasaInteresRepository.findByEstadoOrderByFechaCreacion(estado)){
                listDTO.add(TasaInteresBuilder.toDTO(tasaInteres));
            }
            log.info("Se obtuvo la lista de tasa de interes con el estado: ", estado);
            return listDTO;
        }else{
            throw new RuntimeException("Estado ingresado invalido: " + estado);
        }
    }

    @Transactional
    public TasaInteresDTO Crear(TasaInteresDTO dto) {
        try {
            TasaInteres tasaInteres = TasaInteresBuilder.toTasaInteres(dto);
            LocalDate fechaActualDate = LocalDate.now();
            LocalDateTime fechaActualTimestamp = LocalDateTime.now();
            tasaInteres.setFechaCreacion(Date.valueOf(fechaActualDate));
            tasaInteres.setFechaUltimoCambio(Timestamp.valueOf(fechaActualTimestamp));
            log.info("La tasa de interes esta en proceso de creacion: {}", dto);
            return TasaInteresBuilder.toDTO(this.tasaInteresRepository.save(tasaInteres));
        } catch (Exception e) {
            throw new CreateException("Ocurrio un error al crear la tasaInteres: " + dto.toString(), e);
        }
    }

    @Transactional
    public TasaInteresDTO CambiarEstado(String codTasaInteres, String estado) {
        try {
            if("ACT".equals(estado) || "INA".equals(estado)){
                Optional<TasaInteres> tasaInteres = this.tasaInteresRepository.findById(codTasaInteres);
                if(tasaInteres.isPresent()){
                    log.info("Se obtuvo la tasa de interes con el id {}", codTasaInteres);
                    tasaInteres.get().setEstado(estado);
                    LocalDateTime fechaActualTimestamp = LocalDateTime.now();
                    tasaInteres.get().setFechaUltimoCambio(Timestamp.valueOf(fechaActualTimestamp));
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
