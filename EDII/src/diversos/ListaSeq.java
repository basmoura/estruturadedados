package diversos;

public abstract class ListaSeq<E extends Comparable<E>> extends
		ColecaoComparavel<E> {

	protected Vetor<E> lista;

	public ListaSeq() {
		lista = new Vetor<E>();
	}

	public ListaSeq(int capacidadeInicial) {
		lista = new Vetor<E>(capacidadeInicial);
	}

	public ListaSeq(int capacidadeInicial, int incremento) {
		lista = new Vetor<E>(capacidadeInicial, incremento);
	}

	@Override
	public void clear() {
		lista.clear();
	}

	@Override
	public MyIterator<E> iterator() {
		return lista.iterator();
	}

	protected abstract int busque(E obj);

	@Override
	public boolean remove(E obj) {
		int posicao = busque(obj);
		if (posicao >= 0) {
			lista.removeAt(posicao);
			return true;
		}
		return false;
	}

	@Override
	public E retrieve(E obj) {
		int posicao = busque(obj);
		if (posicao >= 0) {
			return lista.elementAt(posicao);
		}
		return null;
	}
	
	@Override
	public int size() {
		return lista.size();
	}
}
