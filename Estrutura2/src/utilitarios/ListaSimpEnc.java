package utilitarios;

import java.io.Serializable;

public class ListaSimpEnc<E> implements Serializable {

	protected NoSimpEnc<E> inicio;
	protected NoSimpEnc<E> fim;
	protected int numItens;

	private class ListaSimpEncIterator implements MyIterator<E> {
		private NoSimpEnc<E> p;
		private NoSimpEnc<E> pAnt;

		@Override
		public E getFirst() {
			if(inicio == null)
				return null;
			p = inicio;
			return p.getObj();
		}

		@Override
		public E getNext() {
			if (p == fim) {
				return null;
			}
			pAnt = p;
			p = p.getProx();
			return p.getObj();
		}

		@Override
		public void remove() {
			if (p == inicio) {
				inicio = p.getProx();
			} else if (p == fim) {
				fim = pAnt;
				pAnt.setProx(null);
			} else {
				pAnt.setProx(p.getProx());
			}
			numItens--;
		}

	}

	// Retorna a referencia ao início da lista
	public NoSimpEnc<E> getInicio() {
		return inicio;
	}

	// Retorna a referencia ao final da lista
	public NoSimpEnc<E> getFim() {
		return fim;

	}

	// Esvazia a lista
	public void clear() {
		inicio = fim = null;
		numItens = 0;
	}

	// Informa se a lista está vazia ou não
	public boolean isEmpty() {
		if (inicio == null) {
			return true;
		}
		return false;
	}

	// Insere um no no início da lista
	public void insertAtBegin(E obj) {
		NoSimpEnc<E> novoNo = new NoSimpEnc<E>(obj);
		if (inicio == null)
			inicio = fim = novoNo;
		else {
			novoNo.setProx(inicio);
			inicio = novoNo;
		}
		numItens++;
	}

	// Insere um no no final da lista
	public void insertAtEnd(E obj) {
		NoSimpEnc<E> novoNo = new NoSimpEnc<E>(obj);
		if (inicio == null) {
			inicio = fim = novoNo;
		} else {
			fim.setProx(novoNo);
			fim = novoNo;
		}
		numItens++;
	}

	// Insere um no apos o no apontado por p
	public void insertAfter(E obj, NoSimpEnc<E> p) {
		NoSimpEnc<E> novoNo = new NoSimpEnc<E>(obj, p.getProx());
		if (inicio == null) {
			inicio = fim = novoNo;
		}
		else if (p == fim){
			fim.setProx(novoNo);
			fim = novoNo;
			numItens++;
			return;
		}
		p.setProx(novoNo);
		numItens++;
	}

	// Remover o primeiro no da lista,
	// retornando a referência ao objeto que se
	// encontra armazenado nele.
	// Se a lista estiver vazia retorna null
	public E removeFromBegin() {
		if(inicio == null)
			fim = null;
		E objTemp = inicio.getObj();
		inicio = inicio.getProx();
		numItens--;
		return objTemp;
	}

	// Remover o no que segue o no apontado por p,
	// retornando a referencia ao objeto que se encontra
	// armazenado nele.
	// Se a lista estiver vazia ou se nao existir o
	// proximo no, retorna null
	public E removeAfter(NoSimpEnc<E> p) {
		if (inicio == null) {
			return null;
		}
		if (p.getProx() == fim) {
			fim = p;
		}
		E objTemp = p.getProx().getObj();
		p.setProx(p.getProx().getProx());
		numItens--;
		return objTemp;
	}

	// Retorna o tamanho da lista
	public int size() {
		return numItens;
	}

	public MyIterator<E> iterator() {
		return new ListaSimpEncIterator();
	}
}
