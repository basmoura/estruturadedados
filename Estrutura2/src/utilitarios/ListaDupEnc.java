package utilitarios;

//Lista duplamente encadeada com nó-cabeça
public class ListaDupEnc<E> {
	protected NoDupEnc<E> noCabeca;
	protected int numItens;
	
	private class ListaDupEncIterator implements MyIterator<E> {
		
		private NoDupEnc<E> noCorrente;
		
		@Override
		public E getFirst() {
			if (numItens == 0) {
				return null;
			}
			noCorrente = noCabeca.getProx();
			return noCorrente.getObj();
		}

		@Override
		public E getNext() {
			if (noCorrente.getProx() == noCabeca) {
				return null;
			}
			noCorrente = noCorrente.getProx();
			return noCorrente.getObj();
		}

		@Override
		public void remove() {
			ListaDupEnc.this.remove(noCorrente);
			noCorrente = noCorrente.getAnt();	      
		}

	}
	
	public ListaDupEnc(){
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
		new NoDupEnc<E>(obj, noCabeca, noCabeca.getProx());
		numItens++;
	}

	public void insertAtEnd(E obj) {
		new NoDupEnc<E>(obj, noCabeca.getAnt(), noCabeca);
		numItens++;
	}

	public void insertAfter(E obj, NoDupEnc<E> p) {
		new NoDupEnc<E>(obj, p, p.getProx());
		numItens++;
	}
	
	public void insertBefore(E obj, NoDupEnc<E> p){
		new NoDupEnc<E>(obj, p, p.getProx());
		numItens++;
	}

	public E removeFromBegin() {
		if (numItens == 0) {
			return null;
		}
		NoDupEnc<E> noRemovido = noCabeca.getProx();
		noCabeca.setProx(noRemovido.getProx());
		noRemovido.getProx().setAnt(noCabeca);
		numItens--;
		return noRemovido.getObj();
	}

	public E remove(NoDupEnc<E> p) {
		NoDupEnc<E> ant = p.getAnt();
		NoDupEnc<E> prox = p.getProx();
		
		ant.setProx(p.getProx());
		prox.setAnt(p.getAnt());
		numItens--;
		return p.getObj();
	}

	public E removeFromEnd() {
		if (numItens == 0) {
			return null;
		}
		NoDupEnc<E> noRemovido = noCabeca.getAnt();
		noCabeca.setAnt(noRemovido.getAnt());
		noRemovido.getAnt().setProx(noCabeca);
		numItens--;
		return noRemovido.getObj();
	}

	public int size() {
		return numItens;
	}
	
	public MyIterator<E> iterator(){
		return new ListaDupEncIterator();
	}
	

}
