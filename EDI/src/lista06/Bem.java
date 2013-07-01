package lista06;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Bem implements Serializable, Comparable<Bem>{
	private int codigo;
	private String descricao;
	private String dataDeAquisicao;
	private double valor;
	
	public Bem (int codigo) {
		setCodigo(codigo);
	}
	
	public Bem (int codigo, String descricao, String dataDeAquisicao, double valor) {
		this(codigo);
		setDescricao(descricao);
		setDataDeAquisicao(dataDeAquisicao);
		setValor(valor);
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDataDeAquisicao() {
		return dataDeAquisicao;
	}

	public void setDataDeAquisicao(String dataDeAquisicao) {
		this.dataDeAquisicao = dataDeAquisicao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	@Override
	public int compareTo(Bem o) {
		if (getCodigo() > o.getCodigo())
			return 1;
		if (getCodigo() < o.getCodigo())
			return -1;
		return 0;
	}
}
