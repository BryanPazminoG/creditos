package com.banquito.core.banking.creditos.service;

import java.util.Optional;

import java.util.List;
import org.springframework.stereotype.Service;

import com.banquito.core.banking.creditos.dao.CreditoTablaPagosRepository;
import com.banquito.core.banking.creditos.domain.CreditoTablaPagos;
import com.banquito.core.banking.creditos.domain.CreditoTablaPagosPK;
import com.banquito.core.banking.creditos.service.exeption.CreateException;
import com.banquito.core.banking.creditos.service.logica.PreTablaPagos;
import com.banquito.core.banking.creditos.service.logica.ReglasNegocio;

@Service
public class CreditoTablaPagosService {
    private final CreditoTablaPagosRepository creditoTablaPagosRepository;
    private final ReglasNegocio reglasNegocio;

    public CreditoTablaPagosService(CreditoTablaPagosRepository creditoTablaPagosRepository) {
        this.creditoTablaPagosRepository = creditoTablaPagosRepository;
        this.reglasNegocio = new ReglasNegocio();
    }

    public Optional<CreditoTablaPagos> getById(Integer codCredito, Integer codCuota) {
        CreditoTablaPagosPK creditoTablaPagosPK = new CreditoTablaPagosPK(codCredito, codCuota);
        return this.creditoTablaPagosRepository.findById(creditoTablaPagosPK);
    }

    public CreditoTablaPagos create(CreditoTablaPagos creditoTablaPagos) {
        try {
            return this.creditoTablaPagosRepository.save(creditoTablaPagos);
        } catch (Exception e) {
            throw new CreateException(
                    "Ocurrio un error al crear el Credito Tabla Pagos : " + creditoTablaPagos.toString(), e);
        }
    }

    public void delete(Integer codCredito, Integer codCuota) {
        try {
            Optional<CreditoTablaPagos> creditoTablaPagos = getById(codCredito, codCuota);
            if (creditoTablaPagos.isPresent()) {
                this.creditoTablaPagosRepository.delete(creditoTablaPagos.get());
            } else {
                throw new RuntimeException("El Credito Tabla Pagos con id " + codCredito + " - " + codCuota + "no existe");
            }
        } catch (Exception e) {
            throw new CreateException("Ocurrio un error al eliminar el Credito Tabla Pagos, error: " + e.getMessage(), e);
        }
    }

    public CreditoTablaPagos update(CreditoTablaPagos creditoTablaPagosUpdate) {
        try {
            Integer codCredito = creditoTablaPagosUpdate.getPK().getCodCredito();
            Integer codCuota = creditoTablaPagosUpdate.getPK().getCodCuota();
            Optional<CreditoTablaPagos> creditoTablaPagos = getById(codCredito, codCuota);
            if (creditoTablaPagos.isPresent()) {
                return create(creditoTablaPagos.get());
            } else {
                throw new RuntimeException("El Credito Tabla Pagos con id " + codCredito + " - " + codCuota + "no existe");
            }
        } catch (Exception e) {
            throw new CreateException("Ocurrio un error al eliminar el Credito Tabla Pagos, error: " + e.getMessage(), e);
        }
    }

    public List<PreTablaPagos> PreVistaTbAmortizacion(double tasaInteres, double montoPrestamo, Integer numeroPagos){
        List<PreTablaPagos> preTablas = reglasNegocio.PreVistaTbAmortizacion(tasaInteres, montoPrestamo, numeroPagos);
        return preTablas;
    }
}
