import datos.Dataset;
import plataforma.Experimento;
import plataforma.Plataforma;
import usuarios.Investigador;

/**
 * Escenario demostrativo — TODO estudiante: completar para evidenciar
 * el funcionamiento integral del sistema.
 */
public class Main {

    public static void main(String[] args) {
        Plataforma plataforma = new Plataforma("MLOps Lab");
        Dataset dataset = new Dataset("sensores-2024", 10000);

        // Composición: cada experimento crea su modelo internamente
        Experimento e1 = new Experimento("E1", "CNN", 10);
        Experimento e2 = new Experimento("E2", "RNN", 10);
        Experimento e3 = new Experimento("E3", "REGRESION", 10);

        // Agregación: se agregan a la plataforma
        plataforma.agregarExperimento(e1);
        plataforma.agregarExperimento(e2);
        plataforma.agregarExperimento(e3);

        // Ejecutar entrenamientos (polimorfismo)
        e1.ejecutar(dataset);
        e2.ejecutar(dataset);
        e3.ejecutar(dataset);

        // Asociación N:M
        Investigador inv = new Investigador("Dra. Ruiz");
        inv.asociarExperimento(e1);
        inv.asociarExperimento(e2);

        // Reporte
        System.out.println("Reporte de mejor loss:");
        plataforma.reporteMejorLoss().forEach(System.out::println);

        System.out.println("Experimentos de " + inv.getNombre() + ":");
        inv.getExperimentos().forEach(e -> System.out.println(" - " + e.getId()));
    }
}