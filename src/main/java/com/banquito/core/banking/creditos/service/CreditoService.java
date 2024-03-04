package com.banquito.core.banking.creditos.service;


import org.springframework.stereotype.Service;
import org.apache.commons.codec.digest.DigestUtils;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import com.banquito.core.banking.creditos.dao.CreditoRepository;
import com.banquito.core.banking.creditos.domain.Credito;
import com.banquito.core.banking.creditos.dto.CreditoDTO;
import com.banquito.core.banking.creditos.dto.Builder.CreditoBuilder;
import com.banquito.core.banking.creditos.service.exeption.CreateException;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@Service
public class CreditoService {

    private final CreditoRepository creditoRepository;

    public CreditoService(CreditoRepository creditoRepository) {
        this.creditoRepository = creditoRepository;
    }

    public CreditoDTO ObtenerPorId(Integer id) {
        Optional<Credito> credito = this.creditoRepository.findById(id);
        if (credito.isPresent()) {
            log.info("Se ha encontrado el credito con id: {}", id);
            return CreditoBuilder.toDTO(credito.get());
        } else {
            throw new RuntimeException("La credito con id" + id + " no existe");
        }
    }

    @Transactional
    public Integer Crear(CreditoDTO dto) {
        try {
            Credito credito = CreditoBuilder.toCredito(dto);
            LocalDate fechaActualDate = LocalDate.now();
            LocalDateTime fechaActualTimestamp = LocalDateTime.now();
            credito.setNumeroOperacion(new DigestUtils("MD2").digestAsHex(dto.toString()));
            credito.setFechaCreacion(Date.valueOf(fechaActualDate));
            credito.setFechaUltimoCambio(Timestamp.valueOf(fechaActualTimestamp));
            credito = this.creditoRepository.save(credito);
            log.info("Se ha creado el credito: {}", dto);
            return credito.getCodCredito();
        } catch (Exception e) {
            throw new CreateException("Ocurrio un error al crear el Credito: " + dto.toString(), e);
        }
    }

    public List<CreditoDTO> BuscarPorCliente(String codCliente) {
        List<CreditoDTO> listDTO = new ArrayList<>();
        for (Credito credito : creditoRepository.findByCodClienteOrderByFechaCreacion(codCliente)) {
            listDTO.add(CreditoBuilder.toDTO(credito));
        }
        return listDTO;
    }

    @Transactional
    public CreditoDTO Actualizar(CreditoDTO dto) {
        try {
            Credito credito = CreditoBuilder.toCredito(dto);
            if (credito != null) {
                LocalDateTime fechaActualTimestamp = LocalDateTime.now();
                credito.setFechaUltimoCambio(Timestamp.valueOf(fechaActualTimestamp));
                this.creditoRepository.save(credito);
                log.info("El credito se ha actualizado correctamente: {}", dto);
                return dto;
            } else {
                throw new RuntimeException(
                        "El Credito con id" + dto.getCodCredito() + " no existe");
            }
        } catch (Exception e) {
            throw new CreateException("Ocurrio un error al actualizar el Credito, error: " + e.getMessage(), e);
        }
    }

    @Transactional
    public CreditoDTO CambiarEstado(Integer codCredito, String estado) {
        try {
            if ("APR".equals(estado) || "DES".equals(estado) || "VIG".equals(estado) || "LIQ".equals(estado)) {
                Optional<Credito> credito = this.creditoRepository.findById(codCredito);
                if (credito.isPresent()) {
                    log.info("Se obtuvo el credito con el id {}", codCredito);
                    credito.get().setEstado(estado);
                    LocalDateTime fechaActualTimestamp = LocalDateTime.now();
                    credito.get().setFechaUltimoCambio(Timestamp.valueOf(fechaActualTimestamp));
                    this.creditoRepository.save(credito.get());
                    log.info("El estado de credito se ha actalizado correctamente a {}", estado);
                    return CreditoBuilder.toDTO(credito.get());
                } else {
                    throw new RuntimeException("La tasa de interes con id" + codCredito + " no existe");
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
