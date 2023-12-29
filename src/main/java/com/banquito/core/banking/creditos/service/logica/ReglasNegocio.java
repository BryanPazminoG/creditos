package com.banquito.core.banking.creditos.service.logica;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import java.util.ArrayList;

public class ReglasNegocio {

    public ReglasNegocio() {
    }

    public double CalcularTasaInteres(double tasaMinima, double tasaMaxima, double monto, Integer plazo) {
        double tasaBase = 3.75;

        double ajustePlazo = 0.1 * (plazo - 12); // Ajuste por Plazo - Por cada mes adicional más allá de 12 meses
        double ajusteMonto = 0.2 * (monto - 5000) / 1000; // Ajuste por Monto - Por cada $1,000 adicionales

        double tasaCalculada = tasaBase + ajustePlazo - ajusteMonto;
        tasaCalculada = Math.max(2.50, Math.min(5.00, tasaCalculada));

        // Redondear a 2 decimales
        BigDecimal bigDecimalValue = BigDecimal.valueOf(tasaCalculada);
        BigDecimal valorRedondeado = bigDecimalValue.setScale(2, RoundingMode.HALF_UP);

        return valorRedondeado.doubleValue();
    }

    public List<PreTablaPagos> PreVistaTbAmortizacion(double tasaInteres, double montoPrestamo, Integer numeroPagos) {

        double tasaInteresPeriodica = tasaInteres / 100;
        double cuota = calcularCuotaPeriodica(montoPrestamo, tasaInteresPeriodica, numeroPagos);

        DecimalFormat df = new DecimalFormat("#.##");
        double capitalPendiente = montoPrestamo;
        
        List<PreTablaPagos> listaPreTablaPagos = new ArrayList<>();

        for (Integer periodo = 1; periodo <= numeroPagos; periodo++) {

            PreTablaPagos preTablaPagos = new PreTablaPagos();
            double interesPeriodo = capitalPendiente * tasaInteresPeriodica;
            double amortizacionPeriodo = cuota - interesPeriodo;
            capitalPendiente -= amortizacionPeriodo;

            preTablaPagos.setPeriodo(periodo);
            preTablaPagos.setCuota(df.format(cuota));
            preTablaPagos.setInteresPeriodo(df.format(interesPeriodo));
            preTablaPagos.setAmortizacionPeriodo(df.format(amortizacionPeriodo));
            preTablaPagos.setCapitalPendiente(df.format(capitalPendiente));

            listaPreTablaPagos.add(preTablaPagos);
        }

        return listaPreTablaPagos;

    }

    private double calcularCuotaPeriodica(double montoPrestamo, double tasaInteres, int numeroPagos) {
        double cuota = montoPrestamo * tasaInteres / (1 - Math.pow(1 + tasaInteres, -numeroPagos));
        return cuota;
    }
}
