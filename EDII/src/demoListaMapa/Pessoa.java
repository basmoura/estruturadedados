package demoListaMapa;

public class Pessoa implements Comparable<Pessoa>{
	
	private int codPessoa;
	private String nomePessoa;
	
	public Pessoa(int codPessoa) {
		this.codPessoa = codPessoa;
	}

	public String getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	public int getCodPessoa() {
		return codPessoa;
	}
	
	@Override
	public int hashCode() {
		return codPessoa;
	}

	@Override
	public int compareTo(Pessoa o) {
		if (codPessoa < o.getCodPessoa())
			return -1;
		if (codPessoa > o.getCodPessoa())
			return 1;
		return 0;
	}
	
	
}
