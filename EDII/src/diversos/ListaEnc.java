package diversos;

public abstract class ListaEnc<E extends Comparable<E>> extends
		ColecaoComparavel<E> {

	protected ListaSimpEnc<E> lista;

	private class ListaIterator implements MyIterator<E> {
		private MyIterator<E> it = lista.iterator();

		@Override
		public E getFirst() {
			return it.getFirst();
		}

		@Override
		public E getNext() {
			return it.getNext();
		}

		@Override
		public void remove() {
			it.remove();
			numItens--;
		}

	}

	public ListaEnc() {
		lista = new ListaSimpEnc<E>();
	}

	public void clear() {
		lista.clear();
		numItens--;
	}

	public MyIterator<E> iterator() {
		return new ListaIterator();
	}

	public E busque(E obj) {
		NoSimpEnc<E> p;
		p = lista.getInicio();
		while (p != null) {
			if (obj.compareTo(p.getObj()) == 0)
				return p.getObj();
			p = p.getProx();
		}
		return null;
	}

	public E retrieve(E obj) {
		return busque(obj);
	}

	@Override
	public boolean remove(E obj) {
		NoSimpEnc<E> p;
		NoSimpEnc<E> pAnt = null;
		p = lista.getInicio();
		while (p != null) {
			if (obj.compareTo(p.getObj()) == 0) {
				if (pAnt == null)
					lista.removeFromBegin();
				else
					lista.removeAfter(pAnt);
				numItens--;
				return true;
			}
			pAnt = p;
			p = p.getProx();
		}
		return false;
	}
}
