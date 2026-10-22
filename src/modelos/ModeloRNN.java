package modelos;

import datos.Dataset;

/**
 * Modelo RNN. Hiperparámetro: número de capas.
 */
public class ModeloRNN extends Modelo {

    private int numCapas;

    public ModeloRNN(String nombre, int maxEpocas, int numCapas) {
        super(nombre, maxEpocas);
        this.numCapas = numCapas;
    }

    /**
     * Simula el entrenamiento registrando hasta maxEpocas valores decrecientes
     * de loss. Retorna el mejor (menor) loss alcanzado.
     */
    @Override
    public double entrenar(Dataset dataset) {
        int maxEpocas = getLossPorEpoca().length;
        int lossActual = numCapas * 20;   // loss inicial proporcional a numCapas
        int paso = numCapas * 2;          // cuánto baja por época

        for (int i = 0; i < maxEpocas; i++) {
            registrarEpoca(lossActual);
            lossActual = lossActual - paso;
            if (lossActual < 1) {
                lossActual = 1;           // evita valores negativos
            }
        }
        return mejorLoss();
    }

    public int getNumCapas() { return numCapas; }
}
