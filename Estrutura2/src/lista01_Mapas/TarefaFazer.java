package lista01_Mapas;

import java.io.Serializable;

public class TarefaFazer implements Serializable , Comparable<TarefaFazer> {
	
	private int codTarefa;
	private String descricao;
	private String dataRealizacao;	
	
	public TarefaFazer(int codTarefa) {
		this.codTarefa = codTarefa;
	}	
	
	public TarefaFazer(int codTarefa, String descricao, String dataRealizacao) {
		super();
		this.codTarefa = codTarefa;
		this.descricao = descricao;
		this.dataRealizacao = dataRealizacao;
	}

	public int getCodTarefa() {
		return codTarefa;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public String getDataRealizacao() {
		return dataRealizacao;
	}

	@Override
	public int compareTo(TarefaFazer o) {
		if (this.codTarefa < o.getCodTarefa()) return -1;
		if (this.codTarefa == o.getCodTarefa()) return 0;
		return 1;
	}

}
