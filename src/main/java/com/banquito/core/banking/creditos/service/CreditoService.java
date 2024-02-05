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
    public CreditoDTO crear(CreditoDTO dto) {
        try {
            Credito credito = CreditoBuilder.toCredito(dto);
            this.creditoRepository.save(credito);
            log.info("Se ha creado el credito: {}", dto);
            return dto;
        } catch (Exception e) {
            throw new CreateException("Ocurrio un error al crear el Credito: " + dto.toString(), e);
        }
    }

    @Transactional
    public CreditoDTO actualizar(CreditoDTO dto) {
        try {
            CreditoDTO credito = obtenerPorId(dto.getCodCredito());
            if (credito != null) {
                return crear(credito);
            } else {
                throw new RuntimeException(
                        "El Credito con id" + dto.getCodCredito() + " no existe");
            }
        } catch (Exception e) {
            throw new CreateException("Ocurrio un error al actualizar el Credito, error: " + e.getMessage(), e);
        }
    }

    public List<CreditoDTO> BuscarPorCliente(String identificacion) {
        List<CreditoDTO> listDTO = new ArrayList<>();
        for(Credito credito : creditoRepository.findByIdentificacionClienteOrderByFechaCreacion(identificacion)){
            listDTO.add(CreditoBuilder.toDTO(credito));
        }
        return listDTO;
    }
}
