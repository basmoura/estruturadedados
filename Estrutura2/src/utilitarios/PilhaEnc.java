package utilitarios;

@SuppressWarnings("serial")
public class PilhaEnc<E> extends Pilha<E>{

	ListaSimpEnc<E> pilha;
	
	public PilhaEnc() {
		this.pilha = new ListaSimpEnc<E>();
	}

	@Override
	public void clear() {
		pilha.clear();
	}

	@Override
	public MyIterator<E> iterator() {
		return pilha.iterator();
	}

	@Override
	public void empilhe(E obj) {
		pilha.insertAtBegin(obj);
	}

	@Override
	public E desempilhe() {
		if (pilha.isEmpty()) {
			return null;
		} else {
			return pilha.removeFromBegin();
		}
	}

	@Override
	public E getTopo() {
		if (pilha.isEmpty()) {
			return null;
		} else {
			return pilha.getInicio().getObj();
		}
	}

	@Override
	public int size() {
		return pilha.size();
	}

}
