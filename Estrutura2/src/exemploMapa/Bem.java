package exemploMapa;

public class Bem implements Comparable<Bem> {
	
	private int codBem;
	private String descricao;
	private float valor;
	
	public Bem(int codBem) {
		super();
		this.codBem = codBem;
	}

	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public int getCodBem() {
		return codBem;
	}


	@Override
	public int compareTo(Bem o) {
		if (codBem < o.codBem)
			return -1;
		if (codBem == o.codBem)
			return 0;
		return 1;
	}	
	
	
}
