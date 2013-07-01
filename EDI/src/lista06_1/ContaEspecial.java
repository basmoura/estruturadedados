package lista06_1;

public class ContaEspecial extends Conta {

	private float limite;

	public ContaEspecial() {

	}

	public ContaEspecial(int numeroConta, String nomeCliente, float saldoConta,
			float limite) {
		super(numeroConta, nomeCliente, (saldoConta));
		setLimite(limite);
	}

	public float getLimite() {
		return limite;
	}

	public void setLimite(float limite) {
		this.limite = limite;
	}

	@Override
	public boolean sacar(float valor) {
		if (valor <= saldoConta + limite) {
			saldoConta -= valor;
			return true;
		}
		return false;
	}
}
