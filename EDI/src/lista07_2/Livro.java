package lista07_2;

import diversos.*;

public class Livro implements Comparable<Livro>{
	
	private int codigo;
	private String titulo;
	private Aluno aluno;
	private String dataDeEmprestimo;


	public Livro(int codigo) {
		this.codigo = codigo;
	}

	public Livro(int codigo, String titulo) {
		this(codigo);
		setTitulo(titulo);
		aluno = null;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	public String getDataDeEmprestimo() {
		return dataDeEmprestimo;
	}

	public void setDataDeEmprestimo(String dataDeEmprestimo) {
		this.dataDeEmprestimo = dataDeEmprestimo;
	}

	@Override
	public int compareTo(Livro obj) {
		if (getCodigo() > obj.getCodigo())
			return 1;
		if (getCodigo() < obj.getCodigo())
			return -1;
		return 0;
	}
}
