package lista07_1;

public class Aluno {
	private int numMat;
	private String nomeAluno;

	public Aluno(int numMat, String nomeAluno) {
		super();
		this.numMat = numMat;
		this.nomeAluno = nomeAluno;
	}

	public String getNomeAluno() {
		return nomeAluno;
	}

	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}

	public int getnumMat() {
		return numMat;
	}

}
