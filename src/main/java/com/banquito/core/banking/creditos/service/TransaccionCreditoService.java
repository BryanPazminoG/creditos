package com.banquito.core.banking.creditos.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.banquito.core.banking.creditos.dao.InteresAcumuladoRepository;
import com.banquito.core.banking.creditos.dao.TransaccionCreditoRepository;
import com.banquito.core.banking.creditos.domain.InteresAcumulado;
import com.banquito.core.banking.creditos.domain.TransaccionCredito;
import com.banquito.core.banking.creditos.dto.TransaccionCreditoDTO;
import com.banquito.core.banking.creditos.dto.Builder.TransaccionCreditoBuilder;
import com.banquito.core.banking.creditos.service.exeption.CreateException;

import jakarta.transaction.Transactional;

import java.util.List;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TransaccionCreditoService {
    private final TransaccionCreditoRepository transaccionCreditoRepository;
    private final InteresAcumuladoRepository interesAcumuladoRepository;

    public TransaccionCreditoService(TransaccionCreditoRepository transaccionCreditoRepository,
            InteresAcumuladoRepository interesAcumuladoRepository) {
        this.transaccionCreditoRepository = transaccionCreditoRepository;
        this.interesAcumuladoRepository = interesAcumuladoRepository;
    }

    public TransaccionCreditoDTO ObtenerPorId(Integer id) {
        Optional<TransaccionCredito> transaccionCredito = this.transaccionCreditoRepository.findById(id);
        if (transaccionCredito.isPresent()) {
            log.info("La transaccion credito con id {} se ha obtenido", id);
            return TransaccionCreditoBuilder.toDTO(transaccionCredito.get());
        } else {
            throw new RuntimeException("La transaccion credito con id" + id + " no existe");
        }
    }

    public List<TransaccionCreditoDTO> ListarPorCredito(Integer codCredito) {
        List<TransaccionCreditoDTO> listDTO = new ArrayList<>();
        for (TransaccionCredito transaccionCredito : transaccionCreditoRepository
                .findByCodCreditoOrderByFechaCreacion(codCredito)) {
            listDTO.add(TransaccionCreditoBuilder.toDTO(transaccionCredito));
        }
        return listDTO;
    }

    public TransaccionCreditoDTO ListarPorCreditoCuota(Integer codCredito, Integer cuota) {
        Optional<TransaccionCredito> transaccionCredito = this.transaccionCreditoRepository
                .findByCodCreditoAndCodCuota(codCredito, cuota);
        if (transaccionCredito.isPresent()) {
            log.info("La transaccion credito con codCredito {} y cuota {} se ha obtenido", codCredito, cuota);
            return TransaccionCreditoBuilder.toDTO(transaccionCredito.get());
        } else {
            throw new RuntimeException(
                    "La transaccion credito  con codCredito" + codCredito + " y cuota " + cuota + " no existe");
        }
    }

    @Transactional
    public TransaccionCreditoDTO Crear(TransaccionCreditoDTO dto) {
        try {
            TransaccionCredito transaccionCredito = TransaccionCreditoBuilder.toTransaccionCredito(dto);
            LocalDate fechaActualDate = LocalDate.now();

            log.info("Calculado el interes generado en la cuota {} del prestamo {}", dto.getCodCredito(),
                    dto.getCodCuota());
            List<InteresAcumulado> listInteresAcumulados = interesAcumuladoRepository
                    .findByCodCreditoAndNumeroCuotaOrderByFechaCreacion(dto.getCodCredito(), dto.getCodCuota());
            BigDecimal interesGenerado = new BigDecimal("0");
            for (InteresAcumulado interesAcumulado : listInteresAcumulados) {
                interesGenerado = interesGenerado.add(interesAcumulado.getInteresGenerado());
            }
            log.info("El interes generado es de: ", interesGenerado);
            transaccionCredito.setInteresTotal(interesGenerado);
            transaccionCredito.setFechaCreacion(Date.valueOf(fechaActualDate));
            return TransaccionCreditoBuilder.toDTO(this.transaccionCreditoRepository.save(transaccionCredito));

        } catch (Exception e) {
            throw new CreateException("Ocurrio un error al crear el Tipo Credito: " + dto.toString(), e);
        }
    }
}
