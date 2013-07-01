package lista01_Mapas;

import java.io.Serializable;  
import utilitarios.*; 

public class Pessoa implements Serializable, Comparable<Pessoa> {

	private int codPessoa;
	private String nomePessoa;
	
	public Pessoa(int codPessoa) {
		super();
		this.codPessoa = codPessoa;
	}
	
	public Pessoa(int codPessoa, String nomePessoa) {
		super();
		this.codPessoa = codPessoa;
		this.nomePessoa = nomePessoa;
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
