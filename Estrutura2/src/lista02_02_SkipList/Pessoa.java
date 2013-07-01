package lista02_02_SkipList;

import java.io.Serializable; 
import utilitarios.*; 

public class Pessoa implements Serializable, Comparable<Pessoa> {

	private int codPessoa;
	private String nomePessoa;
	private int idadePessoa;
	private float salarioPessoa;
	
	public Pessoa(int codPessoa) {
		super();
		this.codPessoa = codPessoa;
	}	
	
	public Pessoa(int codPessoa, String nomePessoa, int idadePessoa,
			float salarioPessoa) {
		super();
		this.codPessoa = codPessoa;
		this.nomePessoa = nomePessoa;
		this.idadePessoa = idadePessoa;
		this.salarioPessoa = salarioPessoa;
	}

	public int getIdadePessoa() {
		return idadePessoa;
	}

	public void setIdadePessoa(int idadePessoa) {
		this.idadePessoa = idadePessoa;
	}

	public float getSalarioPessoa() {
		return salarioPessoa;
	}

	public void setSalarioPessoa(float salarioPessoa) {
		this.salarioPessoa = salarioPessoa;
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
	public int compareTo(Pessoa o) {
		if(codPessoa < o.codPessoa)
			return -1;
		if(codPessoa == o.codPessoa)
			return 0;
		return 1;
	}
	
	@Override
	public int hashCode() {
		return codPessoa;
	}
}
