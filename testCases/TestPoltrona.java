package testCases;

import busServices.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TestPoltrona {

    @Test
    void estado_ocupada_e_area() {
        // Sua Poltrona.calcularArea() = comprimento * largura
        Poltrona p = new Poltrona("1A", TipoDePoltrona.PADRAO, 1.0d, 3.0d, 6.0d);
        Passageiro pax = new Passageiro("Sr. Madruga", "12345678");

        p.setOcupante(pax);
        assertNotNull(p.getOcupante());
        assertEquals(pax, p.getOcupante());
        assertEquals(3.0d, p.calcularArea(), 1e-9); // 3.0 * 1.0
    }

    @Test
    void estado_livre_getters_e_area() {
        Poltrona p = new Poltrona("1A", TipoDePoltrona.PADRAO, 2.0d, 3.0d, 6.0d);

        assertNull(p.getOcupante());
        assertEquals(6.0d, p.calcularArea(), 1e-9); // 3.0 * 2.0
        assertEquals(2.0d, p.getLargura(), 1e-9);
    }
}