package com.banquito.core.banking.creditos.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.banquito.core.banking.creditos.dao.TipoCreditoRepository;
import com.banquito.core.banking.creditos.domain.TipoCredito;
import com.banquito.core.banking.creditos.dto.TipoCreditoDTO;
import com.banquito.core.banking.creditos.dto.Builder.TipoCreditoBuilder;
import com.banquito.core.banking.creditos.service.exeption.CreateException;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TipoCreditoService {
    private final TipoCreditoRepository tipoCreditoRepository;

    public TipoCreditoService(TipoCreditoRepository tipoCreditoRepository) {
        this.tipoCreditoRepository = tipoCreditoRepository;
    }

    public TipoCreditoDTO obtenerPorId(Integer id) {
        Optional<TipoCredito> tipoCredito = this.tipoCreditoRepository.findById(id);
        if (tipoCredito.isPresent()) {
            log.info("El tipo credito con id {} se ha obtenido", id);
            return TipoCreditoBuilder.toDTO(tipoCredito.get());
        } else {
            throw new RuntimeException("El Tipo Credito con id" + id + " no existe");
        }
    }

    public List<TipoCreditoDTO> listar() {
        List<TipoCreditoDTO> listDTO = new ArrayList<>();
        for (TipoCredito tipoCredito : this.tipoCreditoRepository.findAll()) {
            listDTO.add(TipoCreditoBuilder.toDTO(tipoCredito));
        }
        return listDTO;
    }

    public List<TipoCreditoDTO> listarActivos() {
        List<TipoCreditoDTO> listDTO = new ArrayList<>();
        for (TipoCredito tipoCredito : this.tipoCreditoRepository.findByEstadoOrderByNombre("ACT")) {
            listDTO.add(TipoCreditoBuilder.toDTO(tipoCredito));
        }
        return listDTO;
    }

    @Transactional
    public TipoCreditoDTO crear(TipoCreditoDTO dto) {
        try {
            TipoCredito tipoCredito = TipoCreditoBuilder.toTipoCredito(dto);
            this.tipoCreditoRepository.save(tipoCredito);
            log.info("El tipo credito se ha almacenado correctamente: {}", dto);
            return dto;
        } catch (Exception e) {
            throw new CreateException("Ocurrio un error al crear el Tipo Credito: " + dto.toString(), e);
        }
    }

    @Transactional
    public TipoCreditoDTO actualizar(TipoCreditoDTO dto) {
        try {
            TipoCreditoDTO tipoCredito = obtenerPorId(dto.getCodTipoCredito());
            if (tipoCredito != null) {
                return crear(tipoCredito);
            } else {
                throw new RuntimeException(
                        "El Tipo Credito con id" + dto.getCodTipoCredito() + " no existe");
            }
        } catch (Exception e) {
            throw new CreateException("Ocurrio un error al actualizar la Tipo Credito, error: " + e.getMessage(), e);
        }
    }

    @Transactional
    public TipoCreditoDTO cambiarEstado(Integer codTipoCredito, String estado) {
        try {
            if ("ACT".equals(estado) || "INA".equals(estado)) {
                Optional<TipoCredito> tipoCredito = this.tipoCreditoRepository.findById(codTipoCredito);
                if (tipoCredito.isPresent()) {
                    log.info("Se obtuvo la tasa de interes con el id {}", tipoCredito);
                    tipoCredito.get().setEstado(estado);
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
