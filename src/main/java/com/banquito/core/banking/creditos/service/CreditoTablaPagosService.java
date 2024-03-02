package com.banquito.core.banking.creditos.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.banquito.core.banking.creditos.dao.TablaAmortizacionRepository;
import com.banquito.core.banking.creditos.domain.TablaAmortizacion;
import com.banquito.core.banking.creditos.domain.TablaAmortizacionPK;
import com.banquito.core.banking.creditos.service.exeption.CreateException;

@Service
public class CreditoTablaPagosService {
    private final TablaAmortizacionRepository creditoTablaPagosRepository;

    public CreditoTablaPagosService(TablaAmortizacionRepository creditoTablaPagosRepository) {
        this.creditoTablaPagosRepository = creditoTablaPagosRepository;
    }

    public Optional<TablaAmortizacion> getById(Integer codCredito, Integer codCuota) {
        TablaAmortizacionPK creditoTablaPagosPK = new TablaAmortizacionPK(codCredito, codCuota);
        return this.creditoTablaPagosRepository.findById(creditoTablaPagosPK);
    }

    // public Iterable<CreditoTablaPagos> listAll() {
    // return this.creditoTablaPagosRepository.findAll();
    // }

    public TablaAmortizacion create(TablaAmortizacion creditoTablaPagos) {
        try {
            return this.creditoTablaPagosRepository.save(creditoTablaPagos);
        } catch (Exception e) {
            throw new CreateException(
                    "Ocurrio un error al crear el Credito Tabla Pagos : " + creditoTablaPagos.toString(), e);
        }
    }

    public void delete(Integer codCredito, Integer codCuota) {
        try {
            Optional<TablaAmortizacion> creditoTablaPagos = getById(codCredito, codCuota);
            if (creditoTablaPagos.isPresent()) {
                this.creditoTablaPagosRepository.delete(creditoTablaPagos.get());
            } else {
                throw new RuntimeException("El Credito Tabla Pagos con id " + codCredito + " - " + codCuota + "no existe");
            }
        } catch (Exception e) {
            throw new CreateException("Ocurrio un error al eliminar el Credito Tabla Pagos, error: " + e.getMessage(), e);
        }
    }

    public TablaAmortizacion update(TablaAmortizacion creditoTablaPagosUpdate) {
        try {
            Integer codCredito = creditoTablaPagosUpdate.getPK().getCodCredito();
            Integer codCuota = creditoTablaPagosUpdate.getPK().getCodCuota();
            Optional<TablaAmortizacion> creditoTablaPagos = getById(codCredito, codCuota);
            if (creditoTablaPagos.isPresent()) {
                return create(creditoTablaPagos.get());
            } else {
                throw new RuntimeException("El Credito Tabla Pagos con id " + codCredito + " - " + codCuota + "no existe");
            }
        } catch (Exception e) {
            throw new CreateException("Ocurrio un error al eliminar el Credito Tabla Pagos, error: " + e.getMessage(), e);
        }
    }
}
