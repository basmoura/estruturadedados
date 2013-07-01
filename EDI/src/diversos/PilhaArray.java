package diversos;

import diversos.*;

public class PilhaArray<E> extends Pilha<E> {

	private Vetor<E> pilha;

	public PilhaArray(int tamInicial) {
		pilha = new Vetor<E>(tamInicial);
	}

	@Override
	public void clear() {
		pilha.clear();
		numItens = 0;
	}

	@Override
	public MyIterator<E> iterator() {
		return pilha.iterator();
	}

	@Override
	public void empilhe(E obj) {
		pilha.insertAtEnd(obj);
		numItens++;
	}

	@Override
	public E desempilhe() {
		if (pilha.isEmpty())
			return null;
		E retorno = pilha.removeFromEnd();
		numItens--;
		return retorno;
	}

	@Override
	public E getTopo() {
		if (pilha.isEmpty())
			return null;
		return pilha.elementAt(pilha.size() - 1);
	}
}
