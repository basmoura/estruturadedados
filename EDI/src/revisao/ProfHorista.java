package revisao;

public class ProfHorista extends Professor {
	private float salHora;
	private int numHora;

	public ProfHorista(int numMat, String nomeProf, float salHora, int numHora) {
		super(nomeProf, numMat);
		setSalHora(salHora);
		setNumHora(numHora);
	}

	public float getSalHora() {
		return salHora;
	}

	public void setSalHora(float salHora) {
		this.salHora = salHora;
	}

	public int getNumHora() {
		return numHora;
	}

	public void setNumHora(int numHora) {
		this.numHora = numHora;
	}

	@Override
	public float getSalario() {
		return salHora * numHora;
	}

}
