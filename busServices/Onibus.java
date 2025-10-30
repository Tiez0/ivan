package busServices;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class Onibus {
    private List<Poltrona> config;
    private int lotMaxima, nPoltronasPadrão, nLeito;
    private String placa, modelo, marca;

    // constroi o objeto Onibus contendo placa, modelo e marca
    // configura o ônibus com poltronas comuns especificadas no parâmetro numPoltronas
    // e o numero de leitos especificados no parâmetro numLeitos
    public Onibus(String pl, String md, String mar, int numPoltronasPadrão, int numLeitos) {
        // atribui parametros do construtor aos atributos correspondentes
        this.placa = pl;
        this.modelo = md;
        this.marca = mar;
        this.nPoltronasPadrão = numPoltronasPadrão;
        this.nLeito = numLeitos;
        this.lotMaxima = numPoltronasPadrão + numLeitos;
        config = new ArrayList<Poltrona>(lotMaxima);

        // seta a configuração do Onibus
        // constroi as poltronas do tipo LEITO e atribui a elas as primeiras numerações (id)
        int i = 0;
        for (i = 0; i < numLeitos; i++) {
            String id = "";
            if (i < 9) id = id + "0"; // insere 0 na frente da numeração 1-9
            id = id + Integer.toString(i + 1);
            config.add(new Leito(id, TipoDePoltrona.LEITO, 0.5d, 0.5d, 1.2, 180));
        }
        // constroi as poltronas restantes como tipo PADRÃO, continuando a numeração (id)
        for (int j = 0; j < numPoltronasPadrão; j++, i++) {
            String id = "";
            if (i < 9) id = id + "0"; // insere 0 na frente da numeração 1-9
            id = id + Integer.toString(i + 1);
            config.add(new Poltrona(id, TipoDePoltrona.PADRAO, 0.5d, 0.5d, 1.2));
        }
    } // fim do construtor

    // getters e setters
    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }

    // método que retorna a quantidade total de lugares do onibus
    public int getCapacidade() {
        return lotMaxima;
    }
    // sobrecarga do método que retorna a quantidade total de lugares do onibus por tipo
    public int getCapacidade(TipoDePoltrona tp) {
        switch (tp) {
            case PADRAO: return this.nPoltronasPadrão;
            case LEITO : return this.nLeito;
        }
        return 0;
    }

    // método que retorna a quantidade de lugares livres do tipo passado
    public int getLugaresLivres(TipoDePoltrona tp) {
        Iterator<Poltrona> iter = config.iterator();
        int cont = 0;
        while (iter.hasNext()) {
            Poltrona lugar = iter.next();
            if ((lugar.getTipo() == tp) && (lugar.getOcupante() == null))
                cont++; // se for do tipo e estiver livre, conta
        }
        return cont;
    }

    // sobrecarga: quantidade de lugares livres total (independente do tipo)
    public int getLugaresLivres() {
        Iterator<Poltrona> iter = config.iterator();
        int cont = 0;
        while (iter.hasNext()) {
            Poltrona lugar = iter.next();
            if (lugar.getOcupante() == null)
                cont++; // poltrona vazia
        }
        return cont;
    }

    // próxima poltrona livre por tipo; se não houver, retorna null
    private Poltrona getProxPoltornaLivre(TipoDePoltrona tp) {
        Iterator<Poltrona> iter = config.iterator();
        while (iter.hasNext()) {
            Poltrona lugar = iter.next();
            if ((lugar.getTipo() == tp) && (lugar.getOcupante() == null))
                return lugar;
        }
        return null;
    }

    // aloca passageiro na próxima poltrona livre do tipo e retorna true/false
    public boolean adicionarPassageiro(Passageiro ocp, TipoDePoltrona tp) {
        Poltrona p = this.getProxPoltornaLivre(tp);
        if (p == null)
            return false;
        p.setOcupante(ocp);
        return true;
    }

    // retorna a instância de passageiro que ocupa a poltrona (ident) ou null
    public Passageiro getPassageiroDaPoltrona(String ident) {
        Iterator<Poltrona> iter = config.iterator();
        while (iter.hasNext()) {
            Poltrona lugar = iter.next();
            if ((lugar.getIdent().compareTo(ident) == 0))
                return lugar.getOcupante();
        }
        return null;
    }

    // retorna Poltrona de passageiro (por nome) ou null
    public Poltrona consultarPoltronaDePassageiro(String nome) {
        Iterator<Poltrona> iter = config.iterator();
        while (iter.hasNext()) {
            Poltrona lugar = iter.next();
            if (lugar.getOcupante() == null) continue; // <-- CORRIGIDO
            if (lugar.getOcupante().getNome().compareToIgnoreCase(nome) == 0)
                return lugar;
        }
        return null;
    }

    // sobrecarga: retorna Poltrona do passageiro (instância) ou null
    public Poltrona consultarPoltronaDePassageiro(Passageiro pax) {
        Iterator<Poltrona> iter = config.iterator();
        while (iter.hasNext()) {
            Poltrona lugar = iter.next();
            if (lugar.getOcupante() == null) continue; // <-- CORRIGIDO
            if (lugar.getOcupante().equals(pax))
                return lugar;
        }
        return null;
    }

    public String toString() {
        String S = "\n\n\t\t*** MAPA DE LUGARES ***";
        Iterator<Poltrona> iter = config.iterator();
        while (iter.hasNext()) {
            Poltrona lugar = iter.next();
            S = S + "\n Poltrona " + lugar.getIdent() + ", tipo " + lugar.getTipo()
                    + " de área útil " + lugar.calcularArea() + " m2";
            if (lugar.getOcupante() == null)
                S = S + ("\tVAGA");
            else
                S = S + ("\tOcupada por " + lugar.getOcupante().getNome()
                        + ", RG: " + lugar.getOcupante().getRG());
        }
        return S;
    }
}
