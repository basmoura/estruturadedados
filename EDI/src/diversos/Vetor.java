package diversos;

import java.io.Serializable;

public class Vetor<E> implements Serializable{
	private E[] lista;
	private final int CAPACIDADE_DEFAULT = 100;
	private int capacidadeInicial, incremento, numItens;

	private class VetorIterator implements MyIterator<E> {
		private int indiceCorrente;

		@Override
		public E getFirst() {
			if (numItens == 0)
				return null;
			indiceCorrente = 0;
			return lista[0];
		}

		@Override
		public E getNext() {
			if (indiceCorrente < numItens) {
				indiceCorrente++;
				return lista[indiceCorrente];
			} else {
				return null;
			}
		}

		@Override
		public void remove() {
			removeAt(indiceCorrente);
			indiceCorrente--;
		}

	}

	public Vetor() {
		lista = (E[]) new Object[CAPACIDADE_DEFAULT];
		capacidadeInicial = CAPACIDADE_DEFAULT;
		incremento = 10;
	}

	public Vetor(int capacidadeInicial) {
		lista = (E[]) new Object[capacidadeInicial];
		this.capacidadeInicial = capacidadeInicial;
		incremento = 10;
	}

	public Vetor(int capacidadeInicial, int incremento) {
		lista = (E[]) new Object[capacidadeInicial];
		this.capacidadeInicial = capacidadeInicial;
		this.incremento = incremento;
	}

	private void redimensione() {
		E[] listaTemp = (E[]) new Object[lista.length + incremento];
		System.arraycopy(lista, 0, listaTemp, 0, lista.length);
		lista = listaTemp;
	}

	// Limpa o array, atribuindo null a todas as refer�ncias armazenadas nele.
	// A seguir o array dever� voltar a ter o tamanho original.
	public void clear() {
		for (int i = 0; i < size(); i++) {
			lista[i] = null;
		}
		numItens = 0;
		lista = (E[]) new Object[capacidadeInicial];
	}

	// Armazena o objeto no final do array o qual, se necess�rio,
	// dever� ser redimensionado. Ap�s a inser��o, o n�mero de elementos
	// dever� ser incrementado de 1.
	public void insertAtEnd(E obj) {
		if (numItens == lista.length)
			redimensione();
		lista[numItens] = obj;
		numItens++;
	}

	// Insere o objeto no in�cio do array. � equivalente a insertAt(0, obj)
	public void insertAtBegin(E obj) {
		if (size() == lista.length)
			redimensione();
		for (int i = size() - 1; i >= 0; i--) {
			lista[i + 1] = lista[i];
		}
		lista[0] = obj;
		numItens++;
	}

	// Insere o objeto no array na posi��o indicada por indice,
	// deslocando-se os objetos para a direita a partir da posi��o
	// indicada a fim de abrir espa�o para o novo objeto.

	public boolean insertAt(int indice, E obj) {
		if (indice > size() || indice < 0)
			return false; 
		if (size() == lista.length)
			redimensione();
		if (indice == numItens) {
			insertAtEnd(obj);
			return true;
		}
		for (int i = size() - 1; i >= indice; i--) {
			lista[i + 1] = lista[i];
		}
		lista[indice] = obj;
		numItens++;
		return true;
	}

	// Retorna o objeto que se encontra na posi��o indicada por indice
	public E elementAt(int indice) {
		if ((indice >= 0) && (indice < numItens))
			return lista[indice];
		return null;
	}

	public int size() {
		return numItens;
	}

	public MyIterator<E> iterator() {
		return new VetorIterator();
	}

	// Remove do array o objeto que se encontra na posi��o
	// indicada por indice, retornando sua refer�ncia.
	// A remo��o dever� ser feita deslocando-se todos os elementos uma
	// posi��o para a esquerda a partir da posi��o indicada por indice
	public E removeAt(int indice) {
		E retorno = lista[indice];
		for (int i = indice; i < size(); i++) {
			lista[i] = lista[i + 1];
		}
		numItens--;
		return retorno;
	}

	// Remove o objeto que se encontra no in�cio do array,
	// retornando sua refer�ncia. � equivalente a removeAt(0)
	public E removeFromBegin() {
		E retorno = lista[0];
		for (int i = 0; i < size(); i++) {
			lista[i] = lista[i + 1];
		}
		numItens--;
		return retorno;
	}

	// Remove o objeto que se encontra no final do array,
	// retornando sua refer�ncia. Se o array estiver vazio, o resultado ser�
	// null
	public E removeFromEnd() {
		if (isEmpty())
			return null;
		E retorno = lista[size() - 1];
		lista[size() - 1] = null;
		numItens--;
		return retorno;
	}

	// Substitui o objeto que se encontra na posi��o indicada por
	// indice pelo novo objeto indicado por obj
	public boolean replace(int indice, E obj) {
		lista[indice] = obj;
		return true;
	}

	// Informa se o array est� vazio ou n�o.
	public boolean isEmpty() {
		return (numItens == 0);
	}
}
