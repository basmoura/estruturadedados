package lista06_1;

import java.io.Serializable;

public class Movimento implements Serializable, Comparable<Movimento> {
	private int numeroConta;
	private String data;
	private char tipo;
	private double valor;

	public Movimento(int numeroConta) {
		this.numeroConta = numeroConta;
	}

	public Movimento(int numeroConta, String data, char tipo, double valor) {
		this(numeroConta);
		this.data = data;
		setTipo(tipo);
		setValor(valor);
	}

	public int getNumeroConta() {
		return numeroConta;
	}

	public char getTipo() {
		return tipo;
	}

	public void setTipo(char tipo) {
		this.tipo = tipo;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getData() {
		return data;
	}

	@Override
	public int compareTo(Movimento o) {
		if (getNumeroConta() > o.getNumeroConta())
			return 1;
		if (getNumeroConta() < o.getNumeroConta())
			return -1;
		return 0;
	}
}
