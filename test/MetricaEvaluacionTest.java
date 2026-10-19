import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import modelos.ModeloCNN;
import servicios.MetricaEvaluacion;

public class MetricaEvaluacionTest {

    @Test
    void metricaEsCeroSiNoHayEpocas() {
        ModeloCNN m = new ModeloCNN("m", 5, 8);
        MetricaEvaluacion met = new MetricaEvaluacion();
        assertEquals(0.0, met.calcular(m, "accuracy"), 0.01);
    }

    @Test
    void metricaPositivaConEpocas() {
        ModeloCNN m = new ModeloCNN("m", 5, 8);
        m.registrarEpoca(9);
        MetricaEvaluacion met = new MetricaEvaluacion();
        assertTrue(met.calcular(m, "accuracy") > 0);
    }
}