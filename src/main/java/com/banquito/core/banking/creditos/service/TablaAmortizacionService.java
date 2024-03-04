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

import com.banquito.core.banking.creditos.dao.InteresAcumuladoRepository;
import com.banquito.core.banking.creditos.dao.TablaAmortizacionRepository;
import com.banquito.core.banking.creditos.domain.InteresAcumulado;
import com.banquito.core.banking.creditos.domain.TablaAmortizacion;
import com.banquito.core.banking.creditos.domain.TablaAmortizacionPK;
import com.banquito.core.banking.creditos.dto.CreditoDTO;
import com.banquito.core.banking.creditos.dto.TablaAmortizacionDTO;
import com.banquito.core.banking.creditos.dto.Builder.TablaAmortizacionBuilder;
import com.banquito.core.banking.creditos.service.exeption.CreateException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TablaAmortizacionService {
    private final TablaAmortizacionRepository TablaAmortizacionRepository;
    private final InteresAcumuladoRepository interesAcumuladoRepository;

    public TablaAmortizacionService(TablaAmortizacionRepository TablaAmortizacionRepository,
            InteresAcumuladoRepository interesAcumuladoRepository) {
        this.TablaAmortizacionRepository = TablaAmortizacionRepository;
        this.interesAcumuladoRepository = interesAcumuladoRepository;
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
        Collections.sort(tablaAmortizacion,
                Comparator.comparingInt(TablaAmortizacion -> TablaAmortizacion.getPK().getCodCuota()));
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

    public BigDecimal obtenerInteresVigente(Integer codCredito) {
        List<InteresAcumulado> listInteres = this.interesAcumuladoRepository.findByCodCreditoOrderByFechaCreacion(codCredito);
        if(!listInteres.isEmpty()){
            log.info("Interes Acumulado encotrado");
            return listInteres.get(0).getTasaInteresVigente();
        }else{
            throw new RuntimeException(
                "No se han encontrado ningun interes aculumado con el codigo del credito" + codCredito);
        }
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
    public TablaAmortizacionDTO cambiarEstado(Integer codCredito, Integer codCuota, String estado) {
        try {
            if ("PEN".equals(estado) || "MOR".equals(estado) || "PAG".equals(estado)) {

                TablaAmortizacionPK PK = new TablaAmortizacionPK(codCredito, codCuota);
                Optional<TablaAmortizacion> TablaAmortizacion = this.TablaAmortizacionRepository.findById(PK);

                if (TablaAmortizacion.isPresent()) {
                    log.info("Se obtuvo la tabla de amortizacion con el id {} - {}", codCredito, codCuota);
                    TablaAmortizacion.get().setEstado(estado);
                    LocalDateTime fechaActualTimestamp = LocalDateTime.now();
                    TablaAmortizacion.get().setFechaUltimoCambio(Timestamp.valueOf(fechaActualTimestamp));
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
    public List<TablaAmortizacionDTO> crear(CreditoDTO dto) {

        BigDecimal tasaInteres = this.obtenerInteresVigente(dto.getCodCredito());
        BigDecimal montoPrestamo = dto.getMonto();
        Integer numeroPagos = dto.getNumeroCuotas();

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
            tablaAmortizacion.setEstado("PEN");

            LocalDateTime fechaActualTimestamp = LocalDateTime.now();
            tablaAmortizacion.setFechaUltimoCambio(Timestamp.valueOf(fechaActualTimestamp));
            TablaAmortizacionDTO.add(TablaAmortizacionBuilder.toDTO(tablaAmortizacion));
            TablaAmortizacionRepository.save(tablaAmortizacion);
        }

        return TablaAmortizacionDTO;
    }
}
