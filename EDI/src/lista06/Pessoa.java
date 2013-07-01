package lista06;
import java.io.Serializable;

import diversos.*;

@SuppressWarnings("serial")
public class Pessoa implements Serializable, Comparable<Pessoa>{
	private int codigo;
	private String nome;
	private ListaSeqOrd<Bem> bens;
	
	public Pessoa(int codigo) {
		setCodigo(codigo);
	}
	public Pessoa(int codigo, String nome) {
		this(codigo);
		setNome(nome);
		bens = new ListaSeqOrd<Bem>();
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public ListaSeqOrd<Bem> getBens() {
		return bens;
	}
	@Override
	public int compareTo(Pessoa o) {
		if (getCodigo() > o.getCodigo())
			return 1;
		if (getCodigo() < o.getCodigo())
			return -1;
		return 0;
	}
}
