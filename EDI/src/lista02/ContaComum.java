package lista02;

public class ContaComum extends Conta {

	public ContaComum() {

	}

	public ContaComum(int numeroConta) {
		super(numeroConta);
	}

	public ContaComum(int numeroConta, String nomeCliente, float saldoConta) {
		super(numeroConta, nomeCliente, saldoConta);
	}

	@Override
	public boolean sacar(float valor) {
		if (valor <= saldoConta) {
			saldoConta -= valor;
			return true;
		}
		return false;
	}
}
