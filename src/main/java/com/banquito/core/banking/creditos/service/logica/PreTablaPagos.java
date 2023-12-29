package com.banquito.core.banking.creditos.service.logica;

public class PreTablaPagos {

    private Integer periodo; 
    private String cuota;
    private String interesPeriodo;
    private String amortizacionPeriodo;
    private String capitalPendiente;
    
    public PreTablaPagos() {
    }

    public Integer getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Integer periodo) {
        this.periodo = periodo;
    }

    public String getCuota() {
        return cuota;
    }

    public void setCuota(String cuota) {
        this.cuota = cuota;
    }

    public String getInteresPeriodo() {
        return interesPeriodo;
    }

    public void setInteresPeriodo(String interesPeriodo) {
        this.interesPeriodo = interesPeriodo;
    }

    public String getAmortizacionPeriodo() {
        return amortizacionPeriodo;
    }

    public void setAmortizacionPeriodo(String amortizacionPeriodo) {
        this.amortizacionPeriodo = amortizacionPeriodo;
    }

    public String getCapitalPendiente() {
        return capitalPendiente;
    }

    public void setCapitalPendiente(String capitalPendiente) {
        this.capitalPendiente = capitalPendiente;
    }
    
}
