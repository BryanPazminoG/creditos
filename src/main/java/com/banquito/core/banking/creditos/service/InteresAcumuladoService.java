package com.banquito.core.banking.creditos.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.banquito.core.banking.creditos.dao.CreditoRepository;
import com.banquito.core.banking.creditos.dao.InteresAcumuladoRepository;
import com.banquito.core.banking.creditos.dao.TablaAmortizacionRepository;
import com.banquito.core.banking.creditos.domain.Credito;
import com.banquito.core.banking.creditos.domain.InteresAcumulado;
import com.banquito.core.banking.creditos.domain.TablaAmortizacion;
import com.banquito.core.banking.creditos.dto.CreditoDTO;
import com.banquito.core.banking.creditos.dto.InteresAcumuladoDTO;
import com.banquito.core.banking.creditos.dto.Builder.CreditoBuilder;
import com.banquito.core.banking.creditos.dto.Builder.InteresAcumuladoBuilder;
import com.banquito.core.banking.creditos.service.exeption.CreateException;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@Log4j2
@EnableAsync
@Service
public class InteresAcumuladoService {
    private final InteresAcumuladoRepository interesAcumuladoRepository;
    private final CreditoRepository creditoRepository;
    private final TablaAmortizacionRepository tablaAmortizacionRepository;

    public InteresAcumuladoService(InteresAcumuladoRepository interesAcumuladoRepository,
            CreditoRepository creditoRepository, TablaAmortizacionRepository tablaAmortizacionRepository) {
        this.interesAcumuladoRepository = interesAcumuladoRepository;
        this.creditoRepository = creditoRepository;
        this.tablaAmortizacionRepository = tablaAmortizacionRepository;
    }

    public InteresAcumuladoDTO ObtenerPorId(Integer codInteresAcumulado) {
        Optional<InteresAcumulado> interesAcumulado = this.interesAcumuladoRepository
                .findById(codInteresAcumulado);
        if (interesAcumulado.isPresent()) {
            log.info("Interes Acumulado encotrado");
            return InteresAcumuladoBuilder.toDTO(interesAcumulado.get());
        } else {
            throw new RuntimeException(
                    "No se han encontrado el interes acumulado con el codigo" + codInteresAcumulado);
        }
    }

    public List<InteresAcumuladoDTO> ListarPorCodigoCredito(Integer codCredito) {
        List<InteresAcumuladoDTO> listDTO = new ArrayList<>();
        for (InteresAcumulado interesAcumulado : this.interesAcumuladoRepository
                .findByCodCreditoOrderByFechaCreacion(codCredito)) {
            listDTO.add(InteresAcumuladoBuilder.toDTO(interesAcumulado));
        }
        return listDTO;
    }

    public List<InteresAcumuladoDTO> ListarEstado(String estado) {
        try {
            if ("PAG".equals(estado) || "PEN".equals(estado)) {
                List<InteresAcumuladoDTO> listDTO = new ArrayList<>();
                for (InteresAcumulado interesAcumulado : this.interesAcumuladoRepository
                        .findByEstadoOrderByFechaCreacion(estado)) {
                    listDTO.add(InteresAcumuladoBuilder.toDTO(interesAcumulado));
                }
                return listDTO;
            } else {
                log.info("El estado {} es invalido", estado);
                throw new RuntimeException("Estado ingresado invalido: " + estado);
            }
        } catch (Exception e) {
            throw new CreateException("Ocurrio un error al listar el interes acumulado, error: " + e.getMessage(), e);
        }
    }

    public List<CreditoDTO> ListarCreditosEstado(String estado) {
        List<CreditoDTO> listDTO = new ArrayList<>();
        for (Credito credito : this.creditoRepository
                .findByEstadoOrderByFechaCreacion(estado)) {
            listDTO.add(CreditoBuilder.toDTO(credito));
        }
        return listDTO;
    }

    public BigDecimal InteresAcumuladoCuota(Integer codCredito, Integer numeroCuota) {
        List<InteresAcumulado> listInteresAcumulados = interesAcumuladoRepository
                .findByCodCreditoAndNumeroCuotaOrderByFechaCreacion(codCredito, numeroCuota);
        BigDecimal interesGenerado = new BigDecimal("0");
        for (InteresAcumulado interesAcumulado : listInteresAcumulados) {
            interesGenerado = interesGenerado.add(interesAcumulado.getInteresGenerado());
        }
        log.info("El interes acumulado de la cuota {} del credito {} es:  {}", numeroCuota, codCredito,
                interesGenerado);
        return interesGenerado;
    }

    @Async
    @Scheduled(cron = "0 0 20 * * MON-SUN")
    public void GenerarInteres() {

        for (CreditoDTO creditoDTO : this.ListarCreditosEstado("VIG")) {
            BigDecimal montoCredito = creditoDTO.getCapitalPendiente();
            BigDecimal tasaInteresVigente = creditoDTO.getTasaInteres();
            BigDecimal interesGenerado = montoCredito.multiply(tasaInteresVigente.divide(new BigDecimal("36500"),MathContext.DECIMAL128));
            LocalDate fechaActualDate = LocalDate.now();
            TablaAmortizacion cuotaActiva = this.tablaAmortizacionRepository.findByPKCodCreditoAndEstadoOrderByFechaPlanificadaPago(creditoDTO.getCodCredito(), "PRX").get(0);

            InteresAcumulado interesAcumulado = new InteresAcumulado();
            interesAcumulado.setCodCredito(creditoDTO.getCodCredito());
            interesAcumulado.setNumeroCuota(cuotaActiva.getPK().getCodCuota());
            interesAcumulado.setCapitalPendiente(montoCredito);
            interesAcumulado.setTasaInteresVigente(tasaInteresVigente);
            interesAcumulado.setInteresGenerado(interesGenerado);
            interesAcumulado.setFechaCreacion(Date.valueOf(fechaActualDate));
            interesAcumulado.setEstado("PEN");

            this.interesAcumuladoRepository.save(interesAcumulado);

            log.info("Creado el interes acumulado : {} ", interesAcumulado);
        }
    }

    @Transactional
    public InteresAcumuladoDTO CambiarEstado(Integer codInteresAcumulado, String estado) {
        try {
            if ("PAG".equals(estado) || "PEN".equals(estado)) {
                Optional<InteresAcumulado> interesAcumulado = this.interesAcumuladoRepository
                        .findById(codInteresAcumulado);
                if (interesAcumulado.isPresent()) {
                    log.info("Se obtuvo el interes generado con el id {}", codInteresAcumulado);
                    interesAcumulado.get().setEstado(estado);
                    this.interesAcumuladoRepository.save(interesAcumulado.get());
                    log.info("El estado del interes acumulado se ha actalizado correctamente a {}", estado);
                    return InteresAcumuladoBuilder.toDTO(interesAcumulado.get());
                } else {
                    throw new RuntimeException("El interes generado con id" + codInteresAcumulado + " no existe");
                }
            } else {
                log.info("El estado {} es invalido", estado);
                throw new RuntimeException("Estado ingresado invalido: " + estado);
            }
        } catch (Exception e) {
            throw new CreateException(
                    "Ocurrio un error al actualizar el estado del interes generado, error: " + e.getMessage(), e);
        }
    }
}
