package lista01_Mapas;

import java.io.Serializable;
import utilitarios.*;

public class Agenda implements Serializable, Comparable<Agenda>{
	
	private int codigo;	
	private int autoIncTarefa;
	
	private ListaEncOrd<TarefaFazer> listaDeTarefas;
	private ListaEncOrd<TarefaRealizada> listaDeTarefaRealizadas;
	
	public Agenda(int codigo) {		
		this.codigo = codigo;
		this.autoIncTarefa = 1;
		this.listaDeTarefas = new ListaEncOrd<TarefaFazer>();
		this.listaDeTarefaRealizadas = new ListaEncOrd<TarefaRealizada>();
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public int getAutoIncTarefa() {
		return autoIncTarefa;
	}

	public int autoIncTarefa(){
		return this.autoIncTarefa++; 
    }

	public ListaEncOrd<TarefaFazer> getListaDeTarefas() {
		return listaDeTarefas;
	}

	public ListaEncOrd<TarefaRealizada> getListaTarefasRealizadas() {
		return listaDeTarefaRealizadas;
	}

	@Override
	public int compareTo(Agenda o) {
		if (this.codigo < o.getCodigo()) return -1;
		if (this.codigo == o.getCodigo()) return 0;
		return 1;
	}

}
