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

        if (tipoModelo == null) {
            this.modelo = null;
            return;
        }

        switch (tipoModelo) {
            case "CNN":
                this.modelo = new ModeloCNN(id + "-cnn", maxEpocas, 8);
                break;
            case "RNN":
                this.modelo = new ModeloRNN(id + "-rnn", maxEpocas, 3);
                break;
            case "REGRESION":
                this.modelo = new ModeloRegresion(id + "-reg", maxEpocas, 2);
                break;
            default:
                this.modelo = null;   // tipo inválido: el modelo queda null
        }
    }

    /** Ejecuta el entrenamiento del modelo sobre el dataset. */
    public double ejecutar(Dataset dataset) {
        if (modelo == null) {
            return 0;   // sin modelo no hay nada que entrenar
        }
        return modelo.entrenar(dataset);
    }

    public String getId() { return id; }
    public Modelo getModelo() { return modelo; }
}