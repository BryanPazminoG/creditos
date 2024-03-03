package com.banquito.core.banking.creditos.service;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import com.banquito.core.banking.creditos.dao.CreditoRepository;
import com.banquito.core.banking.creditos.domain.Credito;
import com.banquito.core.banking.creditos.dto.CreditoDTO;
import com.banquito.core.banking.creditos.dto.Builder.CreditoBuilder;
import com.banquito.core.banking.creditos.service.exeption.CreateException;

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

    public CreditoDTO obtenerPorId(Integer id) {
        Optional<Credito> credito = this.creditoRepository.findById(id);
        if (credito.isPresent()) {
            log.info("Se ha encontrado el credito con id: {}", id);
            return CreditoBuilder.toDTO(credito.get());
        } else {
            throw new RuntimeException("La credito con id" + id + " no existe");
        }
    }

    @Transactional
    public Integer crear(CreditoDTO dto) {
        try {
            Credito credito = CreditoBuilder.toCredito(dto);
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
    public CreditoDTO actualizar(CreditoDTO dto) {
        try {
            CreditoDTO credito = obtenerPorId(dto.getCodCredito());
            if (credito != null) {
                crear(credito);
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
    public CreditoDTO cambiarEstado(Integer codCredito, String estado) {
        try {
            if ("ACT".equals(estado) || "INA".equals(estado)) {
                Optional<Credito> credito = this.creditoRepository.findById(codCredito);
                if (credito.isPresent()) {
                    log.info("Se obtuvo el credito con el id {}", codCredito);
                    credito.get().setEstado(estado);
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
