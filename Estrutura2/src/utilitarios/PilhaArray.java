package utilitarios;

@SuppressWarnings("serial")
public class PilhaArray<E> extends Pilha<E>{
	
	private Vetor<E> pilha;
	
	public PilhaArray() {
		pilha = new Vetor<E>();
	}

	public PilhaArray(int tamanhoInicial) {
		pilha = new Vetor<E>(tamanhoInicial);
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
		pilha.insertAtEnd(obj);
	}

	@Override
	public E desempilhe() {
		if (pilha.isEmpty()) {
			return null;
		} else {
			return pilha.removeFromEnd();
		}
	}

	@Override
	public E getTopo() {
		if (pilha.isEmpty()) {
			return null;
		} else {
			return pilha.elementAt(size() - 1);
		}
	}
	
	@Override
	public int size() {
		return pilha.size();
	}
}
