package lista01_Mapas;

import java.io.Serializable;

public class TarefaRealizada implements Serializable , Comparable<TarefaRealizada> {
	
	private int codTarefa;
	private String descricao;
	private String dataPrevista;
	private String dataRealizada;
		
	public TarefaRealizada(int codTarefa) {
		this.codTarefa = codTarefa;
	}	

	public TarefaRealizada(int codTarefa, String descricao,
			String dataPrevista, String dataRealizada) {
		super();
		this.codTarefa = codTarefa;
		this.descricao = descricao;
		this.dataPrevista = dataPrevista;
		this.dataRealizada = dataRealizada;
	}

	public int getCodTarefa() {
		return codTarefa;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getDataPrevista() {
		return dataPrevista;
	}

	public String getDataRealizada() {
		return dataRealizada;
	}

	@Override
	public int compareTo(TarefaRealizada o) {
		if (this.codTarefa < o.getCodTarefa()) return -1;
		if (this.codTarefa == o.getCodTarefa()) return 0;
		return 1;
	}	
	
}
