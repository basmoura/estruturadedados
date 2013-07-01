package lista07_Heap;
import java.io.Serializable; 
import utilitarios.*; 

public class Pessoa implements Serializable, Comparable<Pessoa>{

	private int codigo;
	private String nome;
	private float salario;
	private int idade;
	
	public Pessoa(int codigo, String nome, float salario, int idade) {		
		this.codigo = codigo;
		this.nome = nome;
		this.salario = salario;
		this.idade = idade;
	}
	
	public Pessoa (int codigo){
		this.codigo = codigo;
	}
	
	public float getSalario() {
		return salario;
	}

	public void setSalario(float salario) {
		this.salario = salario;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
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

	@Override
	public int compareTo(Pessoa o) {
		if (this.codigo < o.getCodigo()) return -1;
		if (this.codigo == o.getCodigo()) return 0;
		return 1;
	}
	
}
