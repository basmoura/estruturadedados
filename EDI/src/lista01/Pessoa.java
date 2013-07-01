package lista01;

import java.io.Serializable;

public class Pessoa implements Serializable, Comparable<Pessoa> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int codigo;
	private String nome;
	private String dataDeNascimento;

	public Pessoa() {

	}

	public Pessoa(int codigo) {
		setCodigo(codigo);
	}

	public Pessoa(int codigo, String nome, String dataDeNascimento) {
		this(codigo);
		setNome(nome);
		setDataDeNascimento(dataDeNascimento);
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int value) {
		codigo = value;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String value) {
		nome = value;
	}

	public String getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(String value) {
		dataDeNascimento = value;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Pessoa) {
			Pessoa p = (Pessoa) obj;
			return getCodigo() == p.getCodigo();
		}
		return false;
	}

	public int hashCode() {
		return getCodigo();
	}
	
	@Override
	public int compareTo(Pessoa o) {
		if (codigo < o.getCodigo())
			return -1;
		if (codigo > o.getCodigo())
			return 1;
		return 0;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(codigo);
		sb.append("\t\t");
		sb.append(nome);
		sb.append("\t\t");
		sb.append(dataDeNascimento);
		return sb.toString();
	}
}
