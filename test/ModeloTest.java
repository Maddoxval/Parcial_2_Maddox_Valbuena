import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import datos.Dataset;
import modelos.*;
import servicios.MetricaEvaluacion;

public class ModeloTest {

    @Test
    void registrarEpocaActualizaMejorLoss() {
        Modelo m = new ModeloCNN("m1", 10, 32);
        m.registrarEpoca(20);
        m.registrarEpoca(15);
        m.registrarEpoca(25);
        assertEquals(15, m.mejorLoss());
    }

    @Test
    void mejorLossSinEpocasRetornaMaxValue() {
        Modelo m = new ModeloRNN("m2", 5, 3);
        assertEquals(Integer.MAX_VALUE, m.mejorLoss());
    }

    @Test
    void historialRespetaCapacidad() {
        Modelo m = new ModeloRegresion("m3", 3, 2);
        m.registrarEpoca(10);
        m.registrarEpoca(9);
        m.registrarEpoca(8);
        m.registrarEpoca(7);
        assertEquals(3, m.getLossPorEpoca().length);
    }

    @Test
    void entrenamientoRegistraEpocasYRetornaLoss() {
        Modelo m = new ModeloCNN("m4", 5, 16);
        Dataset d = new Dataset("ds", 100);
        double mejor = m.entrenar(d);
        assertTrue(mejor > 0);
        assertTrue(m.getEpocaActual() > 0);
    }

    @Test
    void polimorfismoEnArregloDeModelos() {
        Modelo[] modelos = {
            new ModeloCNN("c", 5, 8),
            new ModeloRNN("r", 5, 2),
            new ModeloRegresion("g", 5, 1)
        };
        Dataset d = new Dataset("ds", 50);
        for (Modelo m : modelos) m.entrenar(d);
        for (Modelo m : modelos) assertTrue(m.getEpocaActual() > 0);
    }

    @Test
    void calcularMetricaUsaDependencia() {
        Modelo m = new ModeloCNN("m5", 5, 16);
        m.registrarEpoca(9);
        MetricaEvaluacion met = new MetricaEvaluacion();
        double metrica = m.calcularMetrica(met);
        assertTrue(metrica > 0);
    }
}