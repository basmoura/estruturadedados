package lista07_2;

import diversos.*;

public class Aluno implements Comparable<Aluno>{

	private int numMat;
	private String nome;
	private ListaEncOrd<Livro> livros;

	public Aluno(int numMat) {
		this.numMat = numMat;
	}

	public Aluno(int numMat, String nome) {
		this(numMat);
		setNome(nome);
		livros = new ListaEncOrd<Livro>();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getNumMat() {
		return numMat;
	}

	public ListaEncOrd<Livro> getLivros() {
		return livros;
	}

	@Override
	public int compareTo(Aluno o) {
		if (getNumMat() > o.getNumMat())
			return 1;
		if (getNumMat() < o.getNumMat())
			return -1;
		return 0;
	}

}
