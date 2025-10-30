package busServices;

public class Passageiro {
	
	private String nome;
	private String RG;
	
	//construtor com todos os parâmetros
	public Passageiro(String no, String rg) {
		super();
		this.nome = no;
		this.RG= rg;
	}
	
	//getters & setters
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getRG() {
		return RG;
	}
	public void setRG(String rg) {
		this.RG = rg;
	}

}
