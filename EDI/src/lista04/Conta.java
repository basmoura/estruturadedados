package lista04;

import java.io.Serializable;

public abstract class Conta implements Serializable {

	private int numeroConta;
	private String nomeCliente;
	protected float saldoConta;

	public Conta() {

	}

	public Conta(int numeroConta) {
		this.numeroConta = numeroConta;
	}

	public Conta(int numeroConta, String nomeCliente, float saldoConta) {
		this(numeroConta);
		setNomeCliente(nomeCliente);
		this.saldoConta = saldoConta;
	}

	public int getNumeroConta() {
		return numeroConta;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public float getSaldoConta() {
		return saldoConta;
	}

	public void depositar(float valor) {
		saldoConta += valor;
	}

	public abstract boolean sacar(float valor);
}
