package servicios;

import modelos.Modelo;

/**
 * Servicio que calcula métricas de evaluación para un modelo.
 * Se usa como DEPENDENCIA desde Modelo.calcularMetrica(MetricaEvaluacion).
 */
public class MetricaEvaluacion {

    /**
     * Devuelve una métrica derivada del mejor loss del modelo.
     * Fórmula: 100.0 / (1 + modelo.mejorLoss())
     * Si mejorLoss() == Integer.MAX_VALUE (sin épocas), retorna 0.
     */
    public double calcular(Modelo modelo, String nombreMetrica) {
        int mejor = modelo.mejorLoss();

        if (mejor == Integer.MAX_VALUE) {
            return 0;   // sin épocas registradas
        }
        return 100.0 / (1 + mejor);
    }
}