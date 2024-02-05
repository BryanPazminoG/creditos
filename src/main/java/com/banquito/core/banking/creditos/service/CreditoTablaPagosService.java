package com.banquito.core.banking.creditos.service;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import org.springframework.stereotype.Service;

import com.banquito.core.banking.creditos.dao.CreditoTablaPagosRepository;
import com.banquito.core.banking.creditos.domain.CreditoTablaPagos;
import com.banquito.core.banking.creditos.domain.CreditoTablaPagosPK;
import com.banquito.core.banking.creditos.dto.CreditoTablaPagosDTO;
import com.banquito.core.banking.creditos.dto.Builder.CreditoTablaPagosBuilder;
import com.banquito.core.banking.creditos.service.exeption.CreateException;
import com.banquito.core.banking.creditos.service.logica.PreTablaPagos;
import com.banquito.core.banking.creditos.service.logica.ReglasNegocio;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CreditoTablaPagosService {
    private final CreditoTablaPagosRepository creditoTablaPagosRepository;
    private final ReglasNegocio reglasNegocio;

    public CreditoTablaPagosService(CreditoTablaPagosRepository creditoTablaPagosRepository) {
        this.creditoTablaPagosRepository = creditoTablaPagosRepository;
        this.reglasNegocio = new ReglasNegocio();
    }

    public CreditoTablaPagosDTO obtenerPorId(Integer codCredito, Integer codCuota) {
        CreditoTablaPagosPK PK = new CreditoTablaPagosPK(codCredito, codCuota);
        Optional<CreditoTablaPagos> creditoTablaPagos = this.creditoTablaPagosRepository.findById(PK);

        if (creditoTablaPagos.isPresent()) {
            log.info("Se ha encontrado la cuota {} del credito {}", codCuota, codCredito);
            return CreditoTablaPagosBuilder.toDTO(creditoTablaPagos.get());
        } else {
            throw new RuntimeException(
                    "El credito tabla pagos con codCredito" + codCredito + " y  codCuota " + codCuota + " no existe");
        }
    }

    public List<CreditoTablaPagosDTO> getTablaAmortizacion(Integer codCredito) {
        List<CreditoTablaPagosDTO> listDTO = new ArrayList<>();
        List<CreditoTablaPagos> tablaAmortizacion = this.creditoTablaPagosRepository.findByPKCodCredito(codCredito);
        Collections.sort(tablaAmortizacion,
                Comparator.comparingInt(CreditoTablaPagos -> CreditoTablaPagos.getPK().getCodCuota()));
        for (CreditoTablaPagos creditoTablaPagos : tablaAmortizacion) {
            listDTO.add(CreditoTablaPagosBuilder.toDTO(creditoTablaPagos));
        }
        return listDTO;
    }

    public Optional<CreditoTablaPagosDTO> getProximoPago(Integer codCredito) {
        List<CreditoTablaPagosDTO> listDTO = this.getTablaAmortizacion(codCredito);
        if (!listDTO.isEmpty()) {
            log.info("Cuota proxima encontrada");
            for (int i = 0; i < listDTO.size(); i++) {
                String elemento = listDTO.get(i).getEstado();
                if ("PEN".equals(elemento)) {
                    return Optional.of(listDTO.get(i));
                }
            }
        }
        return Optional.empty();
    }

    public List<CreditoTablaPagosDTO> getPagosRealizados(Integer codCredito) {
        List<CreditoTablaPagosDTO> listDTO = this.getTablaAmortizacion(codCredito);
        List<CreditoTablaPagosDTO> listaPagos = new ArrayList<>();
        if (!listDTO.isEmpty()) {
            log.info("Pagos realizados encontrados");
            for (int i = 0; i < listDTO.size(); i++) {
                String elemento = listDTO.get(i).getEstado();
                if ("PAG".equals(elemento)) {
                    listaPagos.add(listDTO.get(i));
                }
            }
        }
        return listaPagos;
    }

    public CreditoTablaPagosDTO crear(CreditoTablaPagosDTO dto) {
        try {
            CreditoTablaPagos creditoTablaPagos = CreditoTablaPagosBuilder.toCreditoTablaPagos(dto);
            this.creditoTablaPagosRepository.save(creditoTablaPagos);
            log.info("Se guardo la tabla de amortizacion exitosamente");
            return dto;
        } catch (Exception e) {
            throw new CreateException(
                    "Ocurrio un error al crear el Credito Tabla Pagos : " + dto.toString(), e);
        }
    }

    public void eliminar(Integer codCredito, Integer codCuota) {
        try {
            CreditoTablaPagosPK PK = new CreditoTablaPagosPK(codCredito, codCuota);
            Optional<CreditoTablaPagos> creditoTablaPagos = this.creditoTablaPagosRepository.findById(PK);
            if (creditoTablaPagos.isPresent()) {
                this.creditoTablaPagosRepository.delete(creditoTablaPagos.get());
                log.info("La tabla de amortizacion {} - {} se ha eliminado correctamente", codCredito, codCuota);
            } else {
                throw new RuntimeException(
                        "El Credito Tabla Pagos con id " + codCredito + " - " + codCuota + "no existe");
            }
        } catch (Exception e) {
            throw new CreateException("Ocurrio un error al eliminar el Credito Tabla Pagos, error: " + e.getMessage(),
                    e);
        }
    }

    public CreditoTablaPagosDTO actualizar(CreditoTablaPagosDTO dto) {
        try {
            CreditoTablaPagosDTO creditoTablaPagos = obtenerPorId(dto.getCodCredito(), dto.getCodCuota());
            if (creditoTablaPagos != null) {
                return crear(creditoTablaPagos);
            } else {
                throw new RuntimeException(
                        "El Credito Tabla pagos con id " + dto.getCodCredito() + " - "
                                + dto.getCodCuota() + "no existe");
            }
        } catch (Exception e) {
            throw new CreateException("Ocurrio un error al actualizar el Credito Tabla Pagos, error: " + e.getMessage(),
                    e);
        }
    }

    public List<PreTablaPagos> PreVistaTbAmortizacion(double tasaInteres, double montoPrestamo, Integer numeroPagos) {
        List<PreTablaPagos> preTablas = reglasNegocio.PreVistaTbAmortizacion(tasaInteres, montoPrestamo, numeroPagos);
        log.info("La previsualizacion de la tabla de amortizacion se creo exitosamente");
        return preTablas;
    }
}
