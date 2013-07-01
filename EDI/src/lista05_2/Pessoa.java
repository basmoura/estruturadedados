package lista05_2;
import diversos.ListaSimpEnc;

public class Pessoa {
	private int codigo;
	private String nome;
	private ListaSimpEnc<Bem> bens;
	
	public Pessoa(int codigo, String nome) {
		setCodigo(codigo);
		setNome(nome);
		bens = new ListaSimpEnc<Bem>();
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
	public ListaSimpEnc<Bem> getBens() {
		return bens;
	}
}
