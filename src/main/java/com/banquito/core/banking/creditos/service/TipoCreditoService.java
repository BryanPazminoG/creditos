package com.banquito.core.banking.creditos.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.banquito.core.banking.creditos.dao.TipoCreditoRepository;
import com.banquito.core.banking.creditos.domain.TipoCredito;
import com.banquito.core.banking.creditos.dto.TipoCreditoDTO;
import com.banquito.core.banking.creditos.dto.Builder.TipoCreditoBuilder;
import com.banquito.core.banking.creditos.service.exeption.CreateException;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class TipoCreditoService {
    private final TipoCreditoRepository tipoCreditoRepository;

    public TipoCreditoService(TipoCreditoRepository tipoCreditoRepository) {
        this.tipoCreditoRepository = tipoCreditoRepository;
    }

    public List<TipoCreditoDTO> Listar() {
        List<TipoCreditoDTO> listDTO = new ArrayList<>();
        for (TipoCredito tipoCredito : this.tipoCreditoRepository.findAll()) {
            listDTO.add(TipoCreditoBuilder.toDTO(tipoCredito));
        }
        log.info("Se obtuvo la lista de tipo credito: ", listDTO);
        return listDTO;
    }

    public TipoCreditoDTO ObtenerPorId(Integer codTipoCredito) {
        Optional<TipoCredito> tipoCredito = this.tipoCreditoRepository.findById(codTipoCredito);
        if (tipoCredito.isPresent()) {
            log.info("El tipo credito con codTipoCredito {} se ha obtenido", codTipoCredito);
            return TipoCreditoBuilder.toDTO(tipoCredito.get());
        } else {
            throw new RuntimeException("El Tipo Credito con codTipoCredito" + codTipoCredito + " no existe");
        }
    }

    public List<TipoCreditoDTO> ListarPorEstado(String estado) {
        if ("ACT".equals(estado) || "INA".equals(estado)) {
            List<TipoCreditoDTO> listDTO = new ArrayList<>();
            for (TipoCredito tipoCredito : this.tipoCreditoRepository.findByEstadoOrderByNombre(estado)) {
                listDTO.add(TipoCreditoBuilder.toDTO(tipoCredito));
            }
            log.info("Se obtuvo la lista de tipo credito activos: ", listDTO);
            return listDTO;
        } else {
            throw new RuntimeException("Estado ingresado invalido: " + estado);
        }
    }

    @Transactional
    public TipoCreditoDTO Crear(TipoCreditoDTO dto) {
        try {
            TipoCredito tipoCredito = TipoCreditoBuilder.toTipoCredito(dto);
            LocalDate fechaActualDate = LocalDate.now();
            LocalDateTime fechaActualTimestamp = LocalDateTime.now();
            tipoCredito.setFechaCreacion(Date.valueOf(fechaActualDate));
            tipoCredito.setFechaUltimoCambio(Timestamp.valueOf(fechaActualTimestamp));
            log.info("El tipo credito esta en proceso de creacion correctamente: {}", dto);
            return TipoCreditoBuilder.toDTO(this.tipoCreditoRepository.save(tipoCredito));
        } catch (Exception e) {
            throw new CreateException("Ocurrio un error al crear el Tipo Credito: " + dto.toString(), e);
        }
    }

    @Transactional
    public TipoCreditoDTO Actualizar(TipoCreditoDTO dto) {
        try {
            TipoCredito tipoCredito = TipoCreditoBuilder.toTipoCredito(dto);
            if (tipoCredito != null) {
                LocalDateTime fechaActualTimestamp = LocalDateTime.now();
                tipoCredito.setFechaUltimoCambio(Timestamp.valueOf(fechaActualTimestamp));
                log.info("El tipo credito esta en proceso de actualizacion: {}", dto);
                return TipoCreditoBuilder.toDTO(this.tipoCreditoRepository.save(tipoCredito)) ;
            } else {
                throw new RuntimeException(
                        "El Tipo Credito con id" + dto.getCodTipoCredito() + " no existe");
            }
        } catch (Exception e) {
            throw new CreateException("Ocurrio un error al actualizar la Tipo Credito, error: " + e.getMessage(), e);
        }
    }

    @Transactional
    public TipoCreditoDTO CambiarEstado(Integer codTipoCredito, String estado) {
        try {
            if ("ACT".equals(estado) || "INA".equals(estado)) {
                Optional<TipoCredito> tipoCredito = this.tipoCreditoRepository.findById(codTipoCredito);
                if (tipoCredito.isPresent()) {
                    log.info("Se obtuvo la tasa de interes con el id {}", tipoCredito);
                    tipoCredito.get().setEstado(estado);
                    LocalDateTime fechaActualTimestamp = LocalDateTime.now();
                    tipoCredito.get().setFechaUltimoCambio(Timestamp.valueOf(fechaActualTimestamp));
                    this.tipoCreditoRepository.save(tipoCredito.get());
                    log.info("El estado de la tasa interes se ha actalizado correctamente a {}", estado);
                    return TipoCreditoBuilder.toDTO(tipoCredito.get());
                } else {
                    throw new RuntimeException("La tasa de interes con id" + codTipoCredito + " no existe");
                }
            } else {
                log.info("El estado {} es invalido", estado);
                throw new RuntimeException("Estado ingresado invalido: " + estado);
            }
        } catch (Exception e) {
            throw new CreateException("Ocurrio un error al actualizar la tasaInteres, error: " + e.getMessage(), e);
        }
    }
}
