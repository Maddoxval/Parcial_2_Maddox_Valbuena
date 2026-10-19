import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import plataforma.Experimento;
import usuarios.Investigador;

public class InvestigadorTest {

    @Test
    void investigadorSeAsociaAExperimentos() {
        Investigador inv = new Investigador("Ana");
        inv.asociarExperimento(new Experimento("E1", "CNN", 5));
        inv.asociarExperimento(new Experimento("E2", "RNN", 5));
        assertEquals(2, inv.getExperimentos().size());
    }

    @Test
    void investigadorNoDuplicaElMismoExperimento() {
        Investigador inv = new Investigador("Ana");
        Experimento e = new Experimento("E1", "CNN", 5);
        inv.asociarExperimento(e);
        inv.asociarExperimento(e);
        assertEquals(1, inv.getExperimentos().size());
    }
}