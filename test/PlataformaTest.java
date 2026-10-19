import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import datos.Dataset;
import plataforma.*;

public class PlataformaTest {

    @Test
    void plataformaAgregaExperimentosExistentes() {
        Plataforma p = new Plataforma("MLOps");
        p.agregarExperimento(new Experimento("E1", "CNN", 5));
        p.agregarExperimento(new Experimento("E2", "RNN", 5));
        assertEquals(2, p.getExperimentos().size());
    }

    @Test
    void reporteMejorLossGeneraUnaLineaPorExperimento() {
        Plataforma p = new Plataforma("MLOps");
        Experimento e1 = new Experimento("E1", "CNN", 5);
        e1.ejecutar(new Dataset("ds", 100));
        p.agregarExperimento(e1);
        assertEquals(1, p.reporteMejorLoss().size());
    }
}