package testCases;

import busServices.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Calendar;

class TestIntegracaoViagemOnibus {

    private Viagem novaViagem(int padrao, int leito, double precoPadrao, double precoLeito) {
        Onibus on = new Onibus("ZZZ-0000","Modelo","Marca", padrao, leito);
        return new Viagem("Campinas","Vitoria", Calendar.getInstance(), precoLeito, precoPadrao, on);
    }

    // A.i) Atribuicao bem-sucedida (ambos os tipos)
    @Test
    void atribuicao_sucesso_para_leito_e_padrao() {
        Viagem v = novaViagem(5, 2, 80.0, 100.0);
        Passageiro a = new Passageiro("Sr. Madruga","12345678");
        Passageiro b = new Passageiro("Sr. Ivan","12345679");

        assertTrue(v.getOnibus().adicionarPassageiro(a, TipoDePoltrona.LEITO));
        assertTrue(v.getOnibus().adicionarPassageiro(b, TipoDePoltrona.PADRAO));

        assertNotNull(v.getOnibus().consultarPoltronaDePassageiro(a));
        assertNotNull(v.getOnibus().consultarPoltronaDePassageiro(b));
    }

    // A.ii) Onibus lotado
    @Test
    void onibus_lotado_nao_aceita_novos() {
        Viagem v = novaViagem(1, 0, 80.0, 100.0);
        assertTrue(v.getOnibus().adicionarPassageiro(new Passageiro("P1","DP1"), TipoDePoltrona.PADRAO));
        assertFalse(v.getOnibus().adicionarPassageiro(new Passageiro("P2","DP2"), TipoDePoltrona.PADRAO));
    }

    // A.iii) Tipo desejado indisponivel
    @Test
    void tipo_de_poltrona_indisponivel() {
        Viagem v = novaViagem(1, 0, 80.0, 100.0);
        assertTrue(v.getOnibus().adicionarPassageiro(new Passageiro("P1","DP1"), TipoDePoltrona.PADRAO));
        assertFalse(v.getOnibus().adicionarPassageiro(new Passageiro("L1","DL1"), TipoDePoltrona.LEITO));
    }

    // D) Quantidade de poltronas disponiveis (geral/por tipo)
    @Test
    void disponibilidade_geral_e_por_tipo() {
        Viagem v = novaViagem(3, 1, 80.0, 100.0);
        assertEquals(4, v.getOnibus().getLugaresLivres());
        assertTrue(v.getOnibus().adicionarPassageiro(new Passageiro("A","1"), TipoDePoltrona.PADRAO));
        assertEquals(3, v.getOnibus().getLugaresLivres());
        assertEquals(2, v.getOnibus().getLugaresLivres(TipoDePoltrona.PADRAO));
        assertEquals(1, v.getOnibus().getLugaresLivres(TipoDePoltrona.LEITO));
    }

    // E) Taxa de ocupacao (geral e por tipo)
    @Test
    void taxa_ocupacao_geral_e_por_tipo() {
        Viagem v = novaViagem(3, 1, 80.0, 100.0);
        // Ocupa 1 de cada tipo (total: 2 ocupados em 4)
        assertTrue(v.getOnibus().adicionarPassageiro(new Passageiro("L","L1"), TipoDePoltrona.LEITO));
        assertTrue(v.getOnibus().adicionarPassageiro(new Passageiro("P","P1"), TipoDePoltrona.PADRAO));

        assertEquals(0.5, v.getTaxaOcupação(), 1e-9);                        // 2/4
        assertEquals(1.0, v.getTaxaOcupação(TipoDePoltrona.LEITO), 1e-9);    // 1/1
        assertEquals(1.0/3.0, v.getTaxaOcupação(TipoDePoltrona.PADRAO), 1e-9); // 1/3
    }

    // F) Faturamento total
    @Test
    void faturamento_total() {
        Viagem v = novaViagem(3, 1, 80.0, 100.0);
        assertTrue(v.getOnibus().adicionarPassageiro(new Passageiro("L","L1"), TipoDePoltrona.LEITO));   // +100
        assertTrue(v.getOnibus().adicionarPassageiro(new Passageiro("P","P1"), TipoDePoltrona.PADRAO));  // +80
        assertEquals(180.0, v.calcularTotalFaturado(), 1e-9);
    }
}