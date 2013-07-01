package DemoCriacaoArvoreBinaria;

import java.io.Serializable;

public class NoArvoreBin<E> implements Serializable{

	/** Cont�m o objeto associado a esse n� */
	private E obj;
	
	private int altura;

	/** Refer�ncia � sub�rvore esquerda */
	private NoArvoreBin<E> esq;

	/** refer�ncia a sub�rvore direita */
	private NoArvoreBin<E> dir;

	// ** refer�ncia ao pai do n�
	private NoArvoreBin<E> pai;

	/** Cria um n� com filhos nulos (folha) */
	public NoArvoreBin(E obj, NoArvoreBin<E> pai) {
		this.obj = obj;
		this.pai = pai;
	}
	
	/** Cria um n� com as respectivas sub�rvores esquerda e direita. */
	public NoArvoreBin(E obj, NoArvoreBin<E> pai, NoArvoreBin<E> esq, NoArvoreBin<E> dir) {
		this(obj, pai);
		this.esq = esq;
		this.dir = dir;
	}

	public int getAltura() {
   	return altura;
   }

	public void setAltura(int altura) {
   	this.altura = altura;
   }

	public NoArvoreBin<E> getPai() {
		return pai;
	}

	public void setPai(NoArvoreBin<E> pai) {
		this.pai = pai;
	}

	/** Retorna o objeto associado a esse n�. */
	public E getObj() {
		return obj;
	}

	/** Retorna a refer�ncia da sub�rvore esquerda. */
	public NoArvoreBin<E> getEsq() {
		return esq;
	}

	/** Retorna a refer�ncia da sub�rvore direita. */
	public NoArvoreBin<E> getDir() {
		return dir;
	}

	/** Retorna true se o n� � uma folha. */
	public boolean ehFolha() {
		return (esq == null) && (dir == null);
	}

	/** Substitui o objeto armazenado no n� por obj. */
	public void setObj(E obj) {
		this.obj = obj;
	}

	/** Substitui a refer�ncia da sub�rvore esquerda por esq. */
	public void setEsq(NoArvoreBin<E> esq) {
		this.esq = esq;
	}

	/** Substitui a refer�ncia da sub�rvore direita por dir. */
	public void setDir(NoArvoreBin<E> dir) {
		this.dir = dir;
	}

	/**
	 * Retorna o filho do n� de acordo com o valor de direcao. Se direcao < 0
	 * retorna o filho esquerdo caso contr�rio retorna o filho direito.
	 */
	public NoArvoreBin<E> getFilho(int direcao) {
		if (direcao < 0)
			return esq;
		else
			return dir;
	}

	/**
	 * Atribui o filho do n� de acordo com o valor de direcao. Se direcao < 0
	 * atribui o filho esquerdo, caso contr�rio, atribui o filho direito.
	 */
	public void setFilho(int direcao, NoArvoreBin<E> filho) {
		if (direcao < 0)
			esq = filho;
		else
			dir = filho;
	}

}
