package testCases;

import busServices.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TestOnibus {

    @Test
    void deve_alocar_por_tipo_e_consultar_poltrona() {
        Onibus on = new Onibus("XXX-0000", "Modelo", "Marca", /*PADRAO=*/5, /*LEITO=*/2);

        Passageiro pLeito1  = new Passageiro("A", "A1");
        Passageiro pLeito2  = new Passageiro("B", "B1");
        Passageiro pPadrao1 = new Passageiro("C", "C1");

        assertTrue(on.adicionarPassageiro(pLeito1,  TipoDePoltrona.LEITO));
        assertTrue(on.adicionarPassageiro(pLeito2,  TipoDePoltrona.LEITO));
        assertTrue(on.adicionarPassageiro(pPadrao1, TipoDePoltrona.PADRAO));

        assertNotNull(on.consultarPoltronaDePassageiro(pLeito1));
        assertNotNull(on.consultarPoltronaDePassageiro(pLeito2));
        assertNotNull(on.consultarPoltronaDePassageiro(pPadrao1));
    }

    @Test
    void nao_deve_ultrapassar_lotacao_por_tipo() {
        Onibus on = new Onibus("YYY-1111", "M1", "M2", /*PADRAO=*/1, /*LEITO=*/1);

        assertTrue(on.adicionarPassageiro(new Passageiro("L1", "DL1"), TipoDePoltrona.LEITO));
        // tentar mais um LEITO -> deve falhar (retorna false)
        assertFalse(on.adicionarPassageiro(new Passageiro("L2", "DL2"), TipoDePoltrona.LEITO));
    }

    @Test
    void deve_reportar_lugares_livres_geral_e_por_tipo() {
        Onibus on = new Onibus("ZZZ-2222", "M1", "M2", /*PADRAO=*/3, /*LEITO=*/1);

        // capacidade inicial
        assertEquals(4, on.getCapacidade());
        assertEquals(3, on.getCapacidade(TipoDePoltrona.PADRAO));
        assertEquals(1, on.getCapacidade(TipoDePoltrona.LEITO));

        // livres no início
        assertEquals(4, on.getLugaresLivres());
        assertEquals(3, on.getLugaresLivres(TipoDePoltrona.PADRAO));
        assertEquals(1, on.getLugaresLivres(TipoDePoltrona.LEITO));

        // ocupa 1 PADRAO
        assertTrue(on.adicionarPassageiro(new Passageiro("P", "P1"), TipoDePoltrona.PADRAO));

        // livres após ocupar
        assertEquals(3, on.getLugaresLivres());
        assertEquals(2, on.getLugaresLivres(TipoDePoltrona.PADRAO));
        assertEquals(1, on.getLugaresLivres(TipoDePoltrona.LEITO));
    }
}