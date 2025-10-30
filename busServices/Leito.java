package busServices;

public class Leito extends Poltrona {

	private double anguloInclinacao;
		
	public Leito(String ident, TipoDePoltrona tipo, double largura, double comprimento,
			double altura, double anguloInclinacao) {
		super(ident, tipo, largura, comprimento, altura);
		this.anguloInclinacao = anguloInclinacao;
	}

	public double calcularArea(){
		return (super.getComprimento() * super.getAltura() * super.getLargura()) * this.anguloInclinacao;
	}
	
}
