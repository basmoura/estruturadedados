package prova_questao2;

import java.io.Serializable; 

@SuppressWarnings("serial")
public class Turma implements Comparable<Turma>, Serializable {
	private String codigoTurma;
	private int codigoDisciplina;
	
	public Turma(String codigoTurma, int codigoDisciplina) {
		super();
		this.codigoTurma = codigoTurma;
		this.codigoDisciplina = codigoDisciplina;
	}

	public String getCodigoTurma() {
		return codigoTurma;
	}

	public int getCodigoDisciplina() {
		return codigoDisciplina;
	}
	
	@Override
	public String toString() {
		return codigoDisciplina + codigoTurma;
	}
	
	@Override
	public int compareTo(Turma o) {
		return  this.toString().compareToIgnoreCase(o.toString());
	}
}
