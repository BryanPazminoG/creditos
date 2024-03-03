package com.banquito.core.banking.creditos.service;

import java.util.Optional;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.banquito.core.banking.creditos.dao.TablaAmortizacionRepository;
import com.banquito.core.banking.creditos.domain.TablaAmortizacion;
import com.banquito.core.banking.creditos.domain.TablaAmortizacionPK;
import com.banquito.core.banking.creditos.dto.TablaAmortizacionDTO;
import com.banquito.core.banking.creditos.dto.Builder.TablaAmortizacionBuilder;
import com.banquito.core.banking.creditos.service.exeption.CreateException;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TablaAmortizacionService {
    private final TablaAmortizacionRepository TablaAmortizacionRepository;

    public TablaAmortizacionService(TablaAmortizacionRepository TablaAmortizacionRepository) {
        this.TablaAmortizacionRepository = TablaAmortizacionRepository;
    }

    public TablaAmortizacionDTO obtenerPorId(Integer codCredito, Integer codCuota) {
        TablaAmortizacionPK PK = new TablaAmortizacionPK(codCredito, codCuota);
        Optional<TablaAmortizacion> TablaAmortizacion = this.TablaAmortizacionRepository.findById(PK);

        if (TablaAmortizacion.isPresent()) {
            log.info("Se ha encontrado la cuota {} del credito {}", codCuota, codCredito);
            return TablaAmortizacionBuilder.toDTO(TablaAmortizacion.get());
        } else {
            throw new RuntimeException(
                    "El credito tabla pagos con codCredito" + codCredito + " y  codCuota " + codCuota + " no existe");
        }
    }

    public List<TablaAmortizacionDTO> getTablaAmortizacion(Integer codCredito) {
        List<TablaAmortizacionDTO> listDTO = new ArrayList<>();
        List<TablaAmortizacion> tablaAmortizacion = this.TablaAmortizacionRepository.findByPKCodCredito(codCredito);
        // Ordena las cuotas en orden ascendente
        Collections.sort(tablaAmortizacion,
                Comparator.comparingInt(TablaAmortizacion -> TablaAmortizacion.getPK().getCodCuota()));
        ///////////////////////////////////////

        for (TablaAmortizacion TablaAmortizacion : tablaAmortizacion) {
            listDTO.add(TablaAmortizacionBuilder.toDTO(TablaAmortizacion));
        }
        return listDTO;
    }

    public Optional<TablaAmortizacionDTO> getProximoPago(Integer codCredito) {
        List<TablaAmortizacionDTO> listDTO = this.getTablaAmortizacion(codCredito);
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

    public List<TablaAmortizacionDTO> getPagosRealizados(Integer codCredito) {
        List<TablaAmortizacionDTO> listDTO = this.getTablaAmortizacion(codCredito);
        List<TablaAmortizacionDTO> listaPagos = new ArrayList<>();
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

    @Transactional
    public TablaAmortizacionDTO actualizar(TablaAmortizacionDTO dto) {
        try {
            TablaAmortizacionDTO tablaAmortizacionDTO = obtenerPorId(dto.getCodCredito(), dto.getCodCuota());
            if (tablaAmortizacionDTO != null) {
                TablaAmortizacion tablaAmortizacion = TablaAmortizacionBuilder.toTablaAmortizacion(tablaAmortizacionDTO);
                TablaAmortizacionRepository.save(tablaAmortizacion);
                return dto;
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

    @Transactional
    public TablaAmortizacionDTO cambiarEstado(Integer codCredito, Integer codCuota, String estado) {
        try {
            if ("ACT".equals(estado) || "INA".equals(estado)) {

                TablaAmortizacionPK PK = new TablaAmortizacionPK(codCredito, codCuota);
                Optional<TablaAmortizacion> TablaAmortizacion = this.TablaAmortizacionRepository.findById(PK);

                if (TablaAmortizacion.isPresent()) {
                    log.info("Se obtuvo la tabla de amortizacion con el id {} - {}", codCredito, codCuota);
                    TablaAmortizacion.get().setEstado(estado);
                    this.TablaAmortizacionRepository.save(TablaAmortizacion.get());
                    log.info("El estado de la tabla amortizacion se ha actalizado correctamente a {}", estado);
                    return TablaAmortizacionBuilder.toDTO(TablaAmortizacion.get());
                } else {
                    throw new RuntimeException(
                            "La tabla de amortizacion con id" + codCredito + " - " + codCuota + "no existe");
                }
            } else {
                log.info("El estado {} es invalido", estado);
                throw new RuntimeException("Estado ingresado invalido: " + estado);
            }
        } catch (Exception e) {
            throw new CreateException(
                    "Ocurrio un error al actualizar el estado de la tabla amortizacion, error: " + e.getMessage(), e);
        }
    }

    @Transactional
    public List<TablaAmortizacionDTO> crear(BigDecimal tasaInteres, BigDecimal montoPrestamo, Integer numeroPagos) {

        BigDecimal interesMensual = tasaInteres.divide(new BigDecimal(12))
                .divide(new BigDecimal(100));

        BigDecimal numerador = interesMensual.multiply(montoPrestamo);
        BigDecimal denominador = new BigDecimal(1 - Math.pow(1 + interesMensual.doubleValue(), -numeroPagos));
        BigDecimal cuotaPago = numerador.divide(denominador);

        DecimalFormat df = new DecimalFormat("#.##");

        List<TablaAmortizacionDTO> TablaAmortizacionDTO = new ArrayList<>();

        BigDecimal capital = montoPrestamo;
        Date fechaPagos = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaPagos);

        for (Integer numeroCuota = 1; numeroCuota <= numeroPagos; numeroCuota++) {
            TablaAmortizacion tablaAmortizacion = new TablaAmortizacion();

            calendar.add(Calendar.MONTH, 1);

            BigDecimal interes = capital.multiply(interesMensual);
            BigDecimal capitalAmortizado = cuotaPago.subtract(interes);
            capital = capital.subtract(capitalAmortizado);

            tablaAmortizacion.getPK().setCodCuota(numeroCuota);
            tablaAmortizacion.setFechaPlanificadaPago((java.sql.Date) calendar.getTime());
            tablaAmortizacion.setCapital(new BigDecimal(df.format(cuotaPago)));
            tablaAmortizacion.setInteres(new BigDecimal(df.format(interes)));
            tablaAmortizacion.setMontoCuota(new BigDecimal(df.format(capitalAmortizado)));
            tablaAmortizacion.setCapitalRestante(new BigDecimal(df.format(capital)));

            TablaAmortizacionDTO.add(TablaAmortizacionBuilder.toDTO(tablaAmortizacion));
            TablaAmortizacionRepository.save(tablaAmortizacion);
            /*Anadir fecha de creacion y ultimo */
        }

        return TablaAmortizacionDTO;
    }
}
