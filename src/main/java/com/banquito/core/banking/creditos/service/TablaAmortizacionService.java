package com.banquito.core.banking.creditos.service;

import java.util.Optional;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.sql.Date;

import org.springframework.stereotype.Service;

import com.banquito.core.banking.creditos.dao.TablaAmortizacionRepository;
import com.banquito.core.banking.creditos.domain.TablaAmortizacion;
import com.banquito.core.banking.creditos.domain.TablaAmortizacionPK;
import com.banquito.core.banking.creditos.dto.CreditoDTO;
import com.banquito.core.banking.creditos.dto.TablaAmortizacionDTO;
import com.banquito.core.banking.creditos.dto.Builder.TablaAmortizacionBuilder;
import com.banquito.core.banking.creditos.service.exeption.CreateException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TablaAmortizacionService {
    private final TablaAmortizacionRepository tablaAmortizacionRepository;

    public TablaAmortizacionService(TablaAmortizacionRepository tablaAmortizacionRepository) {
        this.tablaAmortizacionRepository = tablaAmortizacionRepository;
    }

    public List<TablaAmortizacionDTO> BuscarTablaAmortizacion(Integer codCredito) {
        List<TablaAmortizacionDTO> listDTO = new ArrayList<>();
        List<TablaAmortizacion> tablaAmortizacion = this.tablaAmortizacionRepository.findByPKCodCredito(codCredito);
        Collections.sort(tablaAmortizacion,
                Comparator.comparingInt(TablaAmortizacion -> TablaAmortizacion.getPK().getCodCuota()));
        for (TablaAmortizacion TablaAmortizacion : tablaAmortizacion) {
            listDTO.add(TablaAmortizacionBuilder.toDTO(TablaAmortizacion));
        }
        return listDTO;
    }

    public TablaAmortizacionDTO ObtenerPorCuota(Integer codCredito, Integer codCuota) {
        TablaAmortizacionPK PK = new TablaAmortizacionPK(codCredito, codCuota);
        Optional<TablaAmortizacion> TablaAmortizacion = this.tablaAmortizacionRepository.findById(PK);

        if (TablaAmortizacion.isPresent()) {
            log.info("Se ha encontrado la cuota {} del credito {}", codCuota, codCredito);
            return TablaAmortizacionBuilder.toDTO(TablaAmortizacion.get());
        } else {
            throw new RuntimeException(
                    "El credito tabla pagos con codCredito" + codCredito + " y  codCuota " + codCuota + " no existe");
        }
    }

    public List<TablaAmortizacionDTO> ListarPorEstado(Integer codCredito, String estado) {
        if ("ACT".equals(estado) || "PEN".equals(estado) || "MOR".equals(estado) || "PRX".equals(estado)) {
            List<TablaAmortizacionDTO> listDTO = new ArrayList<>();
            for (TablaAmortizacion tablaAmortizacionDTO : this.tablaAmortizacionRepository.findByPKCodCreditoAndEstadoOrderByFechaPlanificadaPago(codCredito, estado)) {
                listDTO.add(TablaAmortizacionBuilder.toDTO(tablaAmortizacionDTO));
            }
            log.info("Se obtuvo la lista de cuotas con el estado: ", estado);
            return listDTO;
        } else {
            throw new RuntimeException("Estado ingresado invalido: " + estado);
        }
    }

    @Transactional
    public TablaAmortizacionDTO CambiarEstado(Integer codCredito, Integer codCuota, String estado) {
        try {
            if ("PEN".equals(estado) || "MOR".equals(estado) || "PAG".equals(estado)) {

                TablaAmortizacionPK PK = new TablaAmortizacionPK(codCredito, codCuota);
                Optional<TablaAmortizacion> TablaAmortizacion = this.tablaAmortizacionRepository.findById(PK);

                if (TablaAmortizacion.isPresent()) {
                    log.info("Se obtuvo la tabla de amortizacion con el id {} - {}", codCredito, codCuota);
                    TablaAmortizacion.get().setEstado(estado);
                    LocalDateTime fechaActualTimestamp = LocalDateTime.now();
                    TablaAmortizacion.get().setFechaUltimoCambio(Timestamp.valueOf(fechaActualTimestamp));
                    this.tablaAmortizacionRepository.save(TablaAmortizacion.get());
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
    public List<TablaAmortizacionDTO> Crear(CreditoDTO dto) {

        BigDecimal tasaInteres = dto.getTasaInteres();
        BigDecimal montoPrestamo = dto.getMonto();
        Integer numeroPagos = dto.getNumeroCuotas();
        BigDecimal interesMensual = tasaInteres.divide(new BigDecimal("12"), MathContext.DECIMAL128)
                .divide(new BigDecimal("100"), MathContext.DECIMAL128);
        BigDecimal numerador = interesMensual.multiply(montoPrestamo);
        BigDecimal denominador = new BigDecimal(1 - Math.pow(1 + interesMensual.doubleValue(), -numeroPagos));
        BigDecimal cuotaPago = numerador.divide(denominador, MathContext.DECIMAL128);

        DecimalFormat df = new DecimalFormat("#.##");
        List<TablaAmortizacionDTO> TablaAmortizacionDTO = new ArrayList<>();
        BigDecimal capital = montoPrestamo;
        LocalDate fechaActualDate = LocalDate.now();
        Date fechaPagos = Date.valueOf(fechaActualDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaPagos);

        for (Integer numeroCuota = 1; numeroCuota <= numeroPagos; numeroCuota++) {
            TablaAmortizacion tablaAmortizacion = new TablaAmortizacion();
            TablaAmortizacionPK tablaAmortizacionPK = new TablaAmortizacionPK(dto.getCodCredito(), numeroCuota);

            calendar.add(Calendar.MONTH, 1);

            BigDecimal interes = capital.multiply(interesMensual);
            BigDecimal capitalAmortizado = cuotaPago.subtract(interes);
            capital = capital.subtract(capitalAmortizado);

            tablaAmortizacion.setPK(tablaAmortizacionPK);
            tablaAmortizacion.setFechaPlanificadaPago(new Date((calendar.getTime()).getTime()));
            tablaAmortizacion.setCapital(new BigDecimal(df.format(cuotaPago)));
            tablaAmortizacion.setInteres(new BigDecimal(df.format(interes)));
            tablaAmortizacion.setMontoCuota(new BigDecimal(df.format(capitalAmortizado)));
            tablaAmortizacion.setCapitalRestante(new BigDecimal(df.format(capital)));
            tablaAmortizacion.setEstado(numeroCuota == 1 ? "PRX": "PEN");

            LocalDateTime fechaActualTimestamp = LocalDateTime.now();
            tablaAmortizacion.setFechaUltimoCambio(Timestamp.valueOf(fechaActualTimestamp));
            TablaAmortizacionDTO.add(TablaAmortizacionBuilder.toDTO(tablaAmortizacion));
            tablaAmortizacionRepository.save(tablaAmortizacion);
        }

        return TablaAmortizacionDTO;
    }
}
