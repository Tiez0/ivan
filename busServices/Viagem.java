package busServices;

import java.util.Calendar;

public class Viagem {
    private String origem, destino;
    private Calendar dataViagem;
    private double precoLeito, precoPadrão;
    private Onibus onib; // instância de ônibus que fará a viagem

    public Viagem(String origem, String destino, Calendar dataViagem,
                  double precoLeito, double precoPadrão, Onibus on) {
        super();
        this.origem = origem;
        this.destino = destino;
        this.dataViagem = dataViagem;
        this.precoLeito = precoLeito;
        this.precoPadrão = precoPadrão;
        this.onib = on;
    }

    public String getOrigem() { return origem; }
    public void setOrigem(String origem) { this.origem = origem; }
    public String getDestino() { return destino; }
    public void setDestino(String destino) { this.destino = destino; }
    public Calendar getDataViagem() { return dataViagem; }
    public void setDataViagem(Calendar dataViagem) { this.dataViagem = dataViagem; }
    public double getPrecoLeito() { return precoLeito; }

    public double getPrecoPassagem(TipoDePoltrona tp) {
        switch (tp) {
            case PADRAO: return this.precoPadrão;
            case LEITO : return this.precoLeito;
        }
        return 0;
    }

    public void setPrecoPassagem(double preco, TipoDePoltrona tp) {
        switch (tp) {
            case PADRAO:
                this.precoPadrão = preco;
                break; // <-- CORRIGIDO
            case LEITO:
                this.precoLeito = preco;
                break; // <-- CORRIGIDO
        }
    }

    public Onibus getOnibus() { return onib; }
    public void setOnibus(Onibus on) { this.onib = on; }

    // calcula total faturado na viagem
    public double calcularTotalFaturado() {
        int ocupLeito = onib.getCapacidade(TipoDePoltrona.LEITO)
                - onib.getLugaresLivres(TipoDePoltrona.LEITO);
        int ocupPad = onib.getCapacidade(TipoDePoltrona.PADRAO)
                - onib.getLugaresLivres(TipoDePoltrona.PADRAO);

        double faturaLeito = ocupLeito * this.getPrecoPassagem(TipoDePoltrona.LEITO);
        double faturaPad   = ocupPad   * this.getPrecoPassagem(TipoDePoltrona.PADRAO); // <-- CORRIGIDO

        return faturaLeito + faturaPad;
    }

    // taxa de ocupação global do ônibus
    public double getTaxaOcupação() {
        return (double) (onib.getCapacidade() - onib.getLugaresLivres()) / onib.getCapacidade();
    }

    // taxa de ocupação por tipo de poltrona
    public double getTaxaOcupação(TipoDePoltrona tp) {
        return (double) (onib.getCapacidade(tp) - onib.getLugaresLivres(tp)) / onib.getCapacidade(tp); // <-- CORRIGIDO
    }
}
