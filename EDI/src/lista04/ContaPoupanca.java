package lista04;

public class ContaPoupanca extends Conta {

	public ContaPoupanca() {

	}

	public ContaPoupanca(int numeroConta, String nomeCliente, float saldo) {
		super(numeroConta, nomeCliente, saldo);
	}

	@Override
	public boolean sacar(float valor) {
		if (valor <= saldoConta) {
			saldoConta -= valor;
			return true;
		}
		return false;
	}

	public void creditar(double taxa) {
		saldoConta += (saldoConta * (taxa / 100));
	}

}
