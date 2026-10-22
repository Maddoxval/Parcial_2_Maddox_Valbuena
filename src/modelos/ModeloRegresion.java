package modelos;

import datos.Dataset;

/**
 * Modelo de regresión. Hiperparámetro: grado del polinomio.
 */
public class ModeloRegresion extends Modelo {

    private int grado;

    public ModeloRegresion(String nombre, int maxEpocas, int grado) {
        super(nombre, maxEpocas);
        this.grado = grado;
    }

    /**
     * Simula el entrenamiento registrando hasta maxEpocas valores decrecientes
     * de loss. Retorna el mejor (menor) loss alcanzado.
     */
    @Override
    public double entrenar(Dataset dataset) {
        int maxEpocas = getLossPorEpoca().length;
        int lossActual = 100 + grado * 5;   // loss inicial: más grado, más complejo
        int paso = 10;                      // baja fija por época

        for (int i = 0; i < maxEpocas; i++) {
            registrarEpoca(lossActual);
            lossActual = lossActual - paso;
            if (lossActual < 1) {
                lossActual = 1;             // evita valores negativos
            }
        }
        return mejorLoss();
    }

    public int getGrado() { return grado; }
}