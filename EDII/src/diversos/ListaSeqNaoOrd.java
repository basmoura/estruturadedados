package diversos;

public class ListaSeqNaoOrd<E extends Comparable<E>> extends ListaSeq<E> {

	public ListaSeqNaoOrd() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ListaSeqNaoOrd(int capacidadeInicial, int incremento) {
		super(capacidadeInicial, incremento);
		// TODO Auto-generated constructor stub
	}

	public ListaSeqNaoOrd(int capacidadeInicial) {
		super(capacidadeInicial);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected int busque(E obj) {
		for (int i = 0; i < size(); i++) {
			if (obj.compareTo(lista.elementAt(i)) == 0)
				return i;
		}
		return -1;
	}

	@Override
	public boolean add(E obj) {
		lista.insertAtEnd(obj);
		numItens++;
		return true;
	}
}
