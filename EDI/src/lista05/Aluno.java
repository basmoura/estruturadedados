package lista05;

public class Aluno {
	private int numMat;
	private String nomeAluno;
	private Curso curso;

	public Aluno(int numMat, String nomeAluno, Curso curso) {
		super();
		this.numMat = numMat;
		this.nomeAluno = nomeAluno;
		this.curso = curso;
	}
	public Curso getCurso() {
		return curso;
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
