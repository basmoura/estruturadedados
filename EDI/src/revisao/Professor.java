package revisao;

import java.io.Serializable;

public abstract class Professor implements Serializable, Comparable<Professor> {
	private String nomeProf;
	private int numMat;

	public Professor(String nome, int numMat) {
		setNome(nome);
		setNumMat(numMat);
	}

	public String getNome() {
		return nomeProf;
	}

	public void setNome(String nome) {
		this.nomeProf = nome;
	}

	public int getNumMat() {
		return numMat;
	}

	public void setNumMat(int numMat) {
		this.numMat = numMat;
	}

	public abstract float getSalario();

	@Override
	public int compareTo(Professor o) {
		if (numMat < o.getNumMat())
			return -1;
		if (numMat == o.getNumMat())
			return 0;
		return 1;
	}
}
