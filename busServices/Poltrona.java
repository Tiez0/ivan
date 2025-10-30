package busServices;

public class Poltrona {

	private String ident;
	private TipoDePoltrona tipo;
	private double largura, altura, comprimento;
	private Passageiro ocupante;
	
	//construtor
	public Poltrona(String ident, TipoDePoltrona tipo, double largura, double comprimento,
			double altura) {
		super();
		this.ident = ident;
		this.tipo = tipo;
		this.largura = largura;
		this.comprimento = comprimento;
		this.altura = altura;
		ocupante = null; //atribui nulo para ocupante
	}
	
	//getters e setters
	public String getIdent() {
		return ident;
	}
	public void setIdent(String ident) {
		this.ident = ident;
	}
	public TipoDePoltrona getTipo() {
		return tipo;
	}
	public void setTipo(TipoDePoltrona  tipo) {
		this.tipo = tipo;
	}
	public double getLargura() {
		return largura;
	}
	public void setLargura(double largura) {
		this.largura = largura;
	}
	public double getAltura() {
		return altura;
	}
	public void setAltura(double altura) {
		this.altura = altura;
	}
	public double getComprimento() {
		return comprimento;
	}
	public void setComprimento(double comprimento) {
		this.comprimento = comprimento;
	}
	
	public Passageiro getOcupante() {
		return ocupante;
	}

	public void setOcupante(Passageiro ocupante) {
		this.ocupante = ocupante;
	}

	//métodos que são operações sobre os atributos da classe
	public double calcularArea(){
		return this.comprimento * this.largura;
	}
	

		
}
