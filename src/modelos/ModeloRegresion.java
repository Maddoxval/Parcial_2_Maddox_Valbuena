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

    @Override
    public double entrenar(Dataset dataset) {
        // TODO: implementar
        return 0;
    }

    public int getGrado() { return grado; }
}