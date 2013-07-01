package utilitarios;

@SuppressWarnings("serial")
public class SkipList<E extends Comparable<E>> extends ColecaoComparavel<E> {

	protected SkipNo<E> noCabeca;
	protected int nivelAtual;
	protected int maxNivel;
	
	private int nivelDoNo() {
		int newLevel = 0;
		while (newLevel < maxNivel && Math.random() < 0.5
		      && newLevel <= nivelAtual) {
			newLevel++;
		}
		return newLevel;
	}
	
	private class SkipListIterator implements MyIterator<E> {
		SkipNo<E> p;

		@Override
		public E getFirst() {
			if (numItens == 0)
				return null;
			p = noCabeca.prox[0];
			return p.obj;
		}

		@Override
		public E getNext() {
			if (p.prox[0] != null) {
				p = p.prox[0];
					return p.obj;
			}
			return null;
		}

		@Override
		public void remove() {
			SkipList.this.remove(p.obj);
		}
	}
	
	// Construtor
	public SkipList(int nivelMaximo) {
		this.maxNivel = nivelMaximo;
		this.noCabeca = new SkipNo<E>(maxNivel, null);
		this.nivelAtual = 0;
	}

	@Override
	public void clear() {
		this.noCabeca = new SkipNo<E>(maxNivel, null);
		this.nivelAtual = 0;
	}

	@Override
	public MyIterator<E> iterator() {
		return new SkipListIterator();
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean remove(E obj) {		
		SkipNo<E> p = noCabeca;
		SkipNo<E>[] pAnt = new SkipNo[maxNivel + 1];
		for (int i = nivelAtual; i >= 0; i--) {
			while (p.prox[i] != null && p.prox[i].obj.compareTo(obj) < 0) {
				p = p.prox[i];
			}
			pAnt[i] = p;
		}

		p = p.prox[0];
		if (p != null && (p.obj.compareTo(obj) == 0)) {
			p.obj = null;
			for (int i = 0; i <= nivelAtual; i++) {
				if (pAnt[i].prox[i] != p)
					break;
				pAnt[i].prox[i] = p.prox[i];
			}

			while (nivelAtual > 0 && noCabeca.prox[nivelAtual] == null) {
				nivelAtual--;
			}
			numItens--;
			return true;
		}
		else
			return false;
	}

	@Override
	public E retrieve(E obj) {	
		SkipNo<E> p = noCabeca;
		for (int i = nivelAtual; i >= 0; i--) {
			while (p.prox[i] != null && p.prox[i].obj.compareTo(obj) < 0) {
				p = p.prox[i];
			}
		}

		p = p.prox[0];
		if (p != null && (p.obj.compareTo(obj) == 0)) {
			return p.obj;
		}
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean add(E obj) {		
		SkipNo<E> p = noCabeca;
		SkipNo<E>[] pAnt = new SkipNo[maxNivel + 1];
		for (int i = nivelAtual; i >= 0; i--) {
			while (p.prox[i] != null
			      && p.prox[i].obj.compareTo(obj) < 0) {
				p = p.prox[i];
			}
			pAnt[i] = p;
		}

		p = p.prox[0];
		if (p == null || !(p.obj.compareTo(obj) == 0)) {
			int nivel = nivelDoNo();
			if (nivel > nivelAtual) {
				pAnt[nivel] = noCabeca;
				nivelAtual = nivel;
			}
			p = new SkipNo<E>(nivel, obj);
			for (int i = 0; i <= nivel; i++) {
				p.prox[i] = pAnt[i].prox[i];
				pAnt[i].prox[i] = p;
			}
			numItens++;
			return true;
		}
		else
			return false;
	}
}
