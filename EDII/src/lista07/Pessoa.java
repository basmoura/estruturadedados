package lista07;

public class Pessoa implements Comparable<Pessoa> {
	
	private int codigo;
	private String nome;
	private float salario;
	private int idade;
	
	public Pessoa(int codigo) {
		this.codigo = codigo;
	}

	public Pessoa(int codigo, String nome, float salario, int idade) {
		this.codigo = codigo;
		this.nome = nome;
		this.salario = salario;
		this.idade = idade;
	}

	public int getCodigo() { return codigo;	}
	public void setCodigo(int codigo) {	this.codigo = codigo; }

	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }

	public float getSalario() { return salario; }
	public void setSalario(float salario) { this.salario = salario;	}

	public int getIdade() {	return idade; }
	public void setIdade(int idade) { this.idade = idade; }

	@Override
	public int compareTo(Pessoa p) {
		if (p.codigo != this.codigo) {
			return p.codigo > this.codigo ? 1 : -1;
		}
		return 0;
	}	
	
}
