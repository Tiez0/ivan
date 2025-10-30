package testCases;

import busServices.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TestPassageiro {

    @Test
    void getters_e_setters_devem_funcionar() {
        Passageiro p = new Passageiro("Nome", "RG123");
        assertEquals("Nome", p.getNome());
        assertEquals("RG123", p.getRG());

        p.setNome("Outro");
        p.setRG("RG999");
        assertEquals("Outro", p.getNome());
        assertEquals("RG999", p.getRG());
    }
}
