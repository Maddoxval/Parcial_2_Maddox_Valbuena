package datos;

/**
 * Dataset de entrenamiento. Contiene un nombre y cantidad de muestras.
 */
public class Dataset {

    private String nombre;
    private int cantidadMuestras;

    public Dataset(String nombre, int cantidadMuestras) {
        this.nombre = nombre;
        this.cantidadMuestras = cantidadMuestras;
    }

    public String getNombre() { return nombre; }
    public int getCantidadMuestras() { return cantidadMuestras; }
}