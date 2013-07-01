package exemploMapa;

public class Pessoa implements Comparable<Pessoa>{

	private int codPessoa;
	private String nomePessoa;
	
	public Pessoa(int codPessoa) {
		super();
		this.codPessoa = codPessoa;		
	}
	
	
	public int getCodPessoa() {
		return codPessoa;
	}

	public void setCodPessoa(int codPessoa) {
		this.codPessoa = codPessoa;
	}

	public String getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}



	@Override
	public int compareTo(Pessoa o) {
		if (codPessoa < o.codPessoa)
			return -1;
		if (codPessoa == o.codPessoa)
			return 0;		
		return 1;
	}
	
	
	
	
	
}
