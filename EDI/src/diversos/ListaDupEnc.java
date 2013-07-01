//Lista duplamente encadeada com n�-cabe�a
package diversos;

public class ListaDupEnc<E> {
	protected NoDupEnc<E> noCabeca;
	protected int numItens;

	private class ListaDupEncIterator implements MyIterator<E> {
		private NoDupEnc<E> noCorrente = noCabeca.getProx();

		@Override
		public E getFirst() {
			if (noCabeca.getAnt() == noCabeca)
				return null;
			return noCabeca.getProx().getObj();
		}

		@Override
		public E getNext() {
			noCorrente = noCorrente.getProx();
			if (noCorrente == noCabeca)
				return null;
			return noCorrente.getObj();
		}

		@Override
		public void remove() {
			ListaDupEnc.this.remove(noCorrente);
		}

	}

	public ListaDupEnc() {
		noCabeca = new NoDupEnc<E>();
	}

	public NoDupEnc<E> getNoCabeca() {
		return noCabeca;
	}

	public void clear() {
		noCabeca.setProx(noCabeca);
		noCabeca.setAnt(noCabeca);
		numItens = 0;
	}

	public boolean isEmpty() {
		return (numItens == 0);
	}

	public void insertAtBegin(E obj) {
		NoDupEnc<E> novoNo = new NoDupEnc<E>(obj, noCabeca, noCabeca.getProx());
		numItens++;
	}

	public void insertAtEnd(E obj) {
		NoDupEnc<E> novoNo = new NoDupEnc<E>(obj, noCabeca.getAnt(), noCabeca);
		numItens++;
	}

	public void insertAfter(E obj, NoDupEnc<E> p) {
		NoDupEnc<E> novoNo = new NoDupEnc<E>(obj, p, p.getProx());
		p.getProx().setAnt(novoNo);
		p.setProx(novoNo);
		numItens++;
	}

	public void insertBefore(E obj, NoDupEnc<E> p) {
		NoDupEnc<E> novoNo = new NoDupEnc<E>(obj, p.getAnt(), p);
		p.getAnt().setProx(novoNo);
		p.setAnt(novoNo);
	}

	public E removeFromBegin() {
		if (isEmpty())
			return null;
		E retorno = noCabeca.getProx().getObj();
		noCabeca.setProx(noCabeca.getProx().getProx());
		noCabeca.getProx().setAnt(noCabeca);
		numItens--;
		return retorno;
	}

	public E remove(NoDupEnc<E> p) {
		if (isEmpty())
			return null;
		E retorno = p.getObj();
		p.remove();
		numItens--;
		return retorno;

	}

	public E removeFromEnd() {
		if (isEmpty())
			return null;
		E retorno = noCabeca.getAnt().getObj();
		noCabeca.setAnt(noCabeca.getAnt().getAnt());
		noCabeca.getAnt().setProx(noCabeca);
		numItens--;
		return retorno;
	}

	public int size() {
		return numItens;
	}

	public MyIterator<E> iterator() {
		return new ListaDupEncIterator();
	}
}
