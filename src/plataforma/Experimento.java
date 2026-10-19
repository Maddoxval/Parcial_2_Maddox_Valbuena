package plataforma;

import datos.Dataset;
import modelos.*;

/**
 * Experimento que COMPONE un modelo.
 * El modelo se crea dentro del constructor (composición real).
 */
public class Experimento {

    private String id;
    private Modelo modelo;

    /**
     * Crea el modelo según el tipo indicado.
     * Tipo: "CNN", "RNN", "REGRESION".
     * Si el tipo es inválido, el modelo queda null.
     */
    public Experimento(String id, String tipoModelo, int maxEpocas) {
        this.id = id;
        // TODO: implementar composición (factory simple con switch)
        this.modelo = null;
    }

    /** Ejecuta el entrenamiento del modelo sobre el dataset. */
    public double ejecutar(Dataset dataset) {
        // TODO: implementar
        return 0;
    }

    public String getId() { return id; }
    public Modelo getModelo() { return modelo; }
}