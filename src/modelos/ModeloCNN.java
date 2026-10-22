package modelos;

import datos.Dataset;

/**
 * Modelo CNN. Hiperparámetro: número de filtros.
 */
public class ModeloCNN extends Modelo {

    private int numFiltros;

    public ModeloCNN(String nombre, int maxEpocas, int numFiltros) {
        super(nombre, maxEpocas);
        this.numFiltros = numFiltros;
    }

    /**
     * TODO estudiante:
     * Simula el entrenamiento registrando hasta maxEpocas valores decrecientes
     * de loss. Retorna el mejor (menor) loss alcanzado.
     * Puedes usar un loss inicial proporcional a numFiltros y decrementarlo
     * en cada época.
     */
    @Override
    public double entrenar(Dataset dataset) {
        // TODO: implementar
        int maxEpocas = getLossPorEpoca().length;
        int lossActual = numFiltros * 10;

        for(int i = 0; i < maxEpocas; i++){
           registrarEpoca(lossActual);
           lossActual = lossActual - numFiltros;
           if (lossActual < 1){
               lossActual = 1;
           }
        }
        return mejorLoss();
    }

    public int getNumFiltros() { return numFiltros; }
}