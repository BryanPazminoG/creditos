package com.banquito.core.banking.creditos.service.logica;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ReglasNegocio {

    public ReglasNegocio(){}

    public double CalcularTasaInteres(double tasaMinima, double tasaMaxima, double monto, Integer plazo){
        double tasaBase = 3.75;

        double ajustePlazo = 0.1 * (plazo - 12);  // Ajuste por Plazo - Por cada mes adicional más allá de 12 meses
        double ajusteMonto = 0.2 * (monto - 5000) / 1000;  // Ajuste por Monto - Por cada $1,000 adicionales

        double tasaCalculada = tasaBase + ajustePlazo - ajusteMonto;
        tasaCalculada = Math.max(2.50, Math.min(5.00, tasaCalculada));

        // Redondear a 2 decimales
        BigDecimal bigDecimalValue = BigDecimal.valueOf(tasaCalculada);
        BigDecimal valorRedondeado = bigDecimalValue.setScale(2, RoundingMode.HALF_UP);

        return valorRedondeado.doubleValue();
    }
}
