package utilitarios;

import java.io.Serializable;

public class Vetor<E> implements Serializable {
	// Valor inicial do tamanho do array (default)
	private final int CAPACIDADE_DEFAULT = 100;

	// Array onde os dados ser�o armazenados
	protected E[] lista;

	// Valores informados pelo usu�rio para a capacidade
	// inicial e para o incremento a ser utilizado quando
	// o array for redimensionado
	protected int incremento, capacidadeInicial;

	// Total de dados armazenados no array
	protected int numItens;

	private class VetorIterador implements MyIterator<E> {

		private int indiceCorrente;

		@Override
		public E getFirst() {
			if (size() == 0)
				return null;
			indiceCorrente = 0;
			return lista[0];
		}

		@Override
		public E getNext() {
			if (indiceCorrente < numItens) {
				indiceCorrente++;
				return lista[indiceCorrente];
			}
			else
				return null;
		}

		public void remove() {
			removeAt(indiceCorrente);
			indiceCorrente--;
		}

	}

	@SuppressWarnings("unchecked")
	public Vetor() {
		lista = (E[]) new Object[CAPACIDADE_DEFAULT];
		incremento = 10;
		capacidadeInicial = CAPACIDADE_DEFAULT;
	}

	@SuppressWarnings("unchecked")
	public Vetor(int capacidadeInicial) {
		lista = (E[]) new Object[capacidadeInicial];
		this.incremento = 10;
		this.capacidadeInicial = capacidadeInicial;
	}

	@SuppressWarnings("unchecked")
	public Vetor(int capacidadeInicial, int incremento) {
		lista = (E[]) new Object[capacidadeInicial];
		this.incremento = incremento;
		this.capacidadeInicial = capacidadeInicial;
	}

	@SuppressWarnings("unchecked")
	protected void redimensione() {
		if (size() == lista.length) {
			E[] novoVetor = (E[]) new Object[size() + incremento];
			System.arraycopy(lista, 0, novoVetor, 0, size());
			lista = novoVetor;
		}
	}

	// Limpa o array, atribuindo null a todas as refer�ncias armazenadas nele.
	// A seguir o array dever� voltar a ter o tamanho original.
	public void clear() {
		lista = (E[]) new Object[this.capacidadeInicial];
		this.incremento = 10;
	}

	// Retorna o objeto que se encontra na posi��o indicada por indice
	public E elementAt(int indice) {
		if (indice >= 0 && indice < numItens)
			return lista[indice];
		return null;
	}

	// Armazena o objeto no final do array o qual, se necess�rio,
	// dever� ser redimensionado. Ap�s a insers�o, o n�mero de elementos
	// dever� ser incrementado de 1.
	public void insertAtEnd(E obj) {
		redimensione();
		lista[numItens++] = obj;
	}

	// Insere o objeto no in�cio do array. equivalente a insertAt(0, obj)
	public void insertAtBegin(E obj) {
		insertAt(0, obj);
	}

	// Insere o objeto no array na posi��o indicada por indice,
	// deslocando-se os objetos para a direita a partir da posi��o
	// indicada a fim de abrir espa�o para o novo objeto.
	public boolean insertAt(int indice, E obj) {
		E objTemp;
		if(indice < 0 || indice > numItens)
			return false;
		redimensione();
		
		for (int i = indice; i <= size(); i++) {
			objTemp = lista[i];
			lista[i] = obj;
			obj = objTemp;
		}
		numItens++;
		return true;
	}

	// Remove o objeto que se encontra no in�cio do array,
	// retornando sua refer�ncia. equivalente a removeAt(0)
	public E removeFromBegin() {
		removeAt(0);
		return null;
	}

	// Remove o objeto que se encontra no final do array,
	// retornando sua refer�ncia. Se o array estiver vazio, o resultado ser� null
	public E removeFromEnd() {
		if(numItens == 0)
			return null;
		E objTemp = lista[numItens - 1];
		lista[numItens - 1] = null;
		numItens--;
		return objTemp;
	}

	// Remove do array o objeto que se encontra na posi��o
	// indicad��o dever� ser feita deslocando-se todos os elementos uma
	// posi��o para a esquerda a partir da posi��o indicada por indice
	public E removeAt(int indice) {
		E objTemp;
		if(indice < 0 || indice > numItens - 1 || numItens == 0)
			return null;
		objTemp = lista[indice];
		
		for (int i = indice; i < numItens; i++) {
			lista[i] = lista[i + 1];
		}
		numItens--;
		return objTemp;
	}

	// Substitui o objeto que se encontra na posi��o indicada por
	// indice pelo novo objeto indicado por obj
	public boolean replace(int indice, E obj) {
		if(indice < 0 || indice > numItens - 1 || numItens == 0)
			return false;
		lista[indice] = obj;
		return true;
	}

	// Informa se o array est� vazio ou n�o.  
	public boolean isEmpty() {
		return (numItens == 0);
	}

	// Informa o n�mero de itens que o array cont�m
	public int size() {
		return numItens;
	}

	// Retorna um iterador do tipo MyIterator.
	public MyIterator<E> iterator() {
		return new VetorIterador();
	}

}
