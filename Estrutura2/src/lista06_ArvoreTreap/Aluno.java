package lista06_ArvoreTreap;
import java.io.Serializable;
import utilitarios.*;


@SuppressWarnings("serial")
public class Aluno implements Comparable<Aluno>, Serializable {
	private int matricula;
	private String nome;
	private ArvoreBusca<Turma> turmas;
	
	public Aluno(int matricula, String nome) {
		super();
		this.matricula = matricula;
		this.nome = nome;
		this.turmas = new ArvoreBusca<>();
	}

	public Aluno(int matricula) {
		super();
		this.matricula = matricula;
		this.turmas = new ArvoreBusca<>();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getMatricula() {
		return matricula;
	}

	public ArvoreBusca<Turma> getTurmas() {
		return turmas;
	}
	
	public boolean adicionarTurma(Turma turma) {
		return turmas.add(turma);
	}
	
	public boolean removerTurma(Turma turma) {
		return turmas.remove(turma);
	}
	
	public boolean temTurma(Turma turma) {
		return turmas.contains(turma);
	}
	
	@Override
	public String toString() {
		return matricula + " " + nome;
	}

	@Override
	public int compareTo(Aluno o) {
		if (matricula < o.matricula) return -1;
		if (matricula == o.matricula) return 0;
		return 1;
	}
}
