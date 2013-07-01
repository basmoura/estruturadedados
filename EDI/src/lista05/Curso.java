package lista05;

public class Curso {
	private int codigo;
	private String nome;

	public Curso() {

	}

	public Curso(int codigo, String nome) {
		setCodigo(codigo);
		setNome(nome);
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
