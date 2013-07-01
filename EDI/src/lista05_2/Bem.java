package lista05_2;

public class Bem {
	private int codigo;
	private String descricao;
	private String dataDeAquisicao;
	private double valor;
	
	public Bem (int codigo, String descricao, String dataDeAquisicao, double valor) {
		setCodigo(codigo);
		setDescricao(descricao);
		setDataDeAquisicao(dataDeAquisicao);
		setValor(valor);
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDataDeAquisicao() {
		return dataDeAquisicao;
	}

	public void setDataDeAquisicao(String dataDeAquisicao) {
		this.dataDeAquisicao = dataDeAquisicao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
}
