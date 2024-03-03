package com.banquito.core.banking.creditos.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import com.banquito.core.banking.creditos.dao.TransaccionCreditoRepository;
import com.banquito.core.banking.creditos.domain.TransaccionCredito;
import com.banquito.core.banking.creditos.dto.TransaccionCreditoDTO;
import com.banquito.core.banking.creditos.dto.Builder.TransaccionCreditoBuilder;
import com.banquito.core.banking.creditos.service.exeption.CreateException;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TransaccionCreditoService {
    private final TransaccionCreditoRepository transaccionCreditoRepository;

    public TransaccionCreditoService(TransaccionCreditoRepository transaccionCreditoRepository) {
        this.transaccionCreditoRepository = transaccionCreditoRepository;
    }

    public TransaccionCreditoDTO obtenerPorId(Integer id) {
        Optional<TransaccionCredito> transaccionCredito = this.transaccionCreditoRepository.findById(id);
        if (transaccionCredito.isPresent()) {
            log.info("La transaccion credito con id {} se ha obtenido", id);
            return TransaccionCreditoBuilder.toDTO(transaccionCredito.get());
        } else {
            throw new RuntimeException("La transaccion credito con id" + id + " no existe");
        }
    }

    public List<TransaccionCreditoDTO> listarPorCredito(Integer codCredito) {
        List<TransaccionCreditoDTO> listDTO = new ArrayList<>();
        for (TransaccionCredito transaccionCredito : transaccionCreditoRepository.findByCodCreditoOrderByFechaCreacion(codCredito)) {
            listDTO.add(TransaccionCreditoBuilder.toDTO(transaccionCredito));
        }
        return listDTO;
    }

    
    public TransaccionCreditoDTO listarPorCreditoCuota(Integer codCredito,Integer cuota) {
        Optional<TransaccionCredito> transaccionCredito = this.transaccionCreditoRepository.findByCodCreditoAndCodCuota(codCredito, cuota);
        if (transaccionCredito.isPresent()) {
            log.info("La transaccion credito con codCredito {} y cuota {} se ha obtenido", codCredito, cuota);
            return TransaccionCreditoBuilder.toDTO(transaccionCredito.get());
        } else {
            throw new RuntimeException("La transaccion credito  con codCredito" + codCredito + " y cuota " + cuota + " no existe");
        }
    }

    @Transactional
    public TransaccionCreditoDTO crear(TransaccionCreditoDTO dto) {
        try {
            TransaccionCredito transaccionCredito = TransaccionCreditoBuilder.toTransaccionCredito(dto);
            this.transaccionCreditoRepository.save(transaccionCredito);
            log.info("La transaccion credito se ha almacenado correctamente: {}", dto);
            return dto;
        } catch (Exception e) {
            throw new CreateException("Ocurrio un error al crear el Tipo Credito: " + dto.toString(), e);
        }
    }
}
