package diversos;

import diversos.*;

public class PilhaEnc<E> extends Pilha<E> {

	private ListaSimpEnc<E> pilha = new ListaSimpEnc<E>();

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
		pilha.insertAtBegin(obj);
		numItens++;
	}

	@Override
	public E desempilhe() {
		if (pilha.isEmpty())
			return null;
		E retorno = pilha.removeFromBegin();
		numItens--;
		return retorno;
	}

	@Override
	public E getTopo() {
		return pilha.getInicio().getObj();
	}

}
