package revisao;

public class ProfMensalista extends Professor {
	private float salMensal;

	public ProfMensalista(int numMat, String nomeProf, float salMensal) {
		super(nomeProf, numMat);
		setSalMensal(salMensal);
	}

	public void setSalMensal(float salMensal) {
		this.salMensal = salMensal;
	}

	@Override
	public float getSalario() {
		return salMensal;
	}

}
