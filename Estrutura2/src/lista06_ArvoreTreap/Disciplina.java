package lista06_ArvoreTreap;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Disciplina implements Comparable<Disciplina>, Serializable {
	private int codigo;
	private String nome;
	
	public Disciplina(int codigo, String nome) {
		super();
		this.codigo = codigo;
		this.nome = nome;
	}

	public Disciplina(int codigo) {
		super();
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCodigo() {
		return codigo;
	}
	
    @Override
    public String toString() {
    	return codigo + " " + nome;
    }

	@Override
	public int compareTo(Disciplina o) {
		if (codigo > o.codigo) return 1;
		if (codigo == o.codigo) return 0;
		return -1;
	}
}
