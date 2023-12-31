package com.banquito.core.banking.creditos.service.logica;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.Date;

public class ReglasNegocio {

    public ReglasNegocio() {
    }

    public double CalcularTasaInteres(double tasaMinima, double tasaMaxima, double monto, Integer plazo) {

        // Calcular la tasa de interés en función del monto y el plazo
        double tasaInteres = tasaMinima + ((monto * plazo) / 1000000.0);
        tasaInteres = Math.max(tasaMinima, Math.min(tasaMaxima, tasaInteres));

        // Redondear a 2 decimales
        BigDecimal bigDecimalValue = BigDecimal.valueOf(tasaInteres);
        BigDecimal valorRedondeado = bigDecimalValue.setScale(2, RoundingMode.HALF_UP);

        return valorRedondeado.doubleValue();
    }

    public List<PreTablaPagos> PreVistaTbAmortizacion(double tasaInteres, double montoPrestamo, Integer numeroPagos) {

        double interesMensual = (tasaInteres / 12) /100;
        double cuotaPago = calcularPMT(interesMensual,numeroPagos, montoPrestamo);

        DecimalFormat df = new DecimalFormat("#.##");

        List<PreTablaPagos> listaPreTablaPagos = new ArrayList<>();

        double capital = montoPrestamo;
        Date fechaPagos = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaPagos);

        for (Integer numeroCupta = 1; numeroCupta <= numeroPagos; numeroCupta++) {
            PreTablaPagos preTablaPagos = new PreTablaPagos();

            calendar.add(Calendar.MONTH, 1);

            double interes = capital * interesMensual;
            double capitalAmortizado = cuotaPago- interes;
            capital = capital - capitalAmortizado;

            preTablaPagos.setPeriodo(numeroCupta);
            preTablaPagos.setFechaPlanificadoPago(calendar.getTime());
            preTablaPagos.setCuota(df.format(cuotaPago));
            preTablaPagos.setInteresPeriodo(df.format(interes));
            preTablaPagos.setAmortizacionPeriodo(df.format(capitalAmortizado));
            preTablaPagos.setCapitalPendiente(df.format(capital));

            listaPreTablaPagos.add(preTablaPagos);
        }

        return listaPreTablaPagos;
    }
    public static double calcularPMT(double tasa, int nper, double va) {
        double numerador = tasa * va;
        double denominador = 1 - Math.pow(1 + tasa, -nper);
        
        return numerador / denominador;
    }
}
