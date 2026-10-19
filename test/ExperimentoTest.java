import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import datos.Dataset;
import modelos.*;
import plataforma.Experimento;

public class ExperimentoTest {

    @Test
    void experimentoComponeModeloCNN() {
        Experimento e = new Experimento("E1", "CNN", 5);
        assertNotNull(e.getModelo());
        assertTrue(e.getModelo() instanceof ModeloCNN);
    }

    @Test
    void experimentoComponeModeloRNN() {
        Experimento e = new Experimento("E2", "RNN", 5);
        assertTrue(e.getModelo() instanceof ModeloRNN);
    }

    @Test
    void experimentoComponeModeloRegresion() {
        Experimento e = new Experimento("E3", "REGRESION", 5);
        assertTrue(e.getModelo() instanceof ModeloRegresion);
    }

    @Test
    void tipoInvalidoDejaModeloNull() {
        Experimento e = new Experimento("E4", "XGBOOST", 5);
        assertNull(e.getModelo());
    }

    @Test
    void ejecutarEntrenaElModelo() {
        Experimento e = new Experimento("E5", "CNN", 5);
        Dataset d = new Dataset("ds", 100);
        double mejor = e.ejecutar(d);
        assertTrue(mejor > 0);
        assertTrue(e.getModelo().getEpocaActual() > 0);
    }
}