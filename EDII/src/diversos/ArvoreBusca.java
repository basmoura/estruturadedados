package diversos;

public class ArvoreBusca<E extends Comparable<E>> extends ColecaoComparavel<E> {

	protected ArvoreBin<E> arvore = new ArvoreBin<>();
	
	private class ArvoreIterator implements MyIterator<E> {
		// Implementar os métodos do iterator, usando o percurso simetrico
		private FilaEnc<NoArvoreBin<E>> fila = new FilaEnc<NoArvoreBin<E>>();
		private NoArvoreBin<E> pAnt;
		
		private void simetrica(NoArvoreBin<E> no){
			if(no != null){
				simetrica(no.getEsq());
				fila.insira(no);
				simetrica(no.getDir());
			}
		}
		
		@Override
		public E getFirst() {
			simetrica(arvore.getRaiz());
			while(!fila.isEmpty()) {
				pAnt = fila.remova();
				if(pAnt != null && !pAnt.isDeletado())
					return pAnt.getObj();
			}
			if(fila.isEmpty()){
				return null;
			}
			return null;
		}

		@Override
		public E getNext() {
			while(!fila.isEmpty()) {
				pAnt = fila.remova();
				if(pAnt != null && !pAnt.isDeletado())
					return pAnt.getObj();
			}
			if(fila.isEmpty()){
				return null;
			}
			return null;
		}

		@Override
		public void remove() {
			pAnt.setDeletado(true);
			numItens--;
		}
	}
	
	@Override
	public void clear() {
		numItens = 0;
	}

	@Override
	public MyIterator<E> iterator() {
		return new ArvoreIterator();
	}

	@Override
	public boolean remove(E obj) {
		NoArvoreBin<E> p;
		int c;
		p = arvore.getRaiz();

		while (p != null) {
			c = obj.compareTo(p.getObj());
			if (c == 0) {
				break;
			}
			if (c < 0) {
				p = p.getEsq();
			} else {
				p = p.getDir();
			}
		}

		if (p == null) {
			return false;
		}

		if ((p.getDir() != null) && (p.getEsq() != null)) {
			NoArvoreBin<E> p1;
			p1 = p.getEsq();

			while (p1.getDir() != null) {
				p1 = p1.getDir();
			}
			p.setObj(p1.getObj());
			p = p1;
		}

		arvore.delete(p);
		numItens--;

		return true;
	}

	@Override
	public E retrieve(E obj) {
		int c;
		NoArvoreBin<E> p = arvore.getRaiz();

		while (p != null) {
			c = obj.compareTo(p.getObj());
			if (c == 0) {
				if(p.isDeletado())
					return null;
					return p.getObj();
			} else {
				if (c > 0) {
					p = p.getDir();
				} else {
					p = p.getEsq();
				}
			}
		}
		return null;
	}

	@Override
	public boolean add(E obj) {
		int direcao = -1;
		NoArvoreBin<E> p, pai;
		int c;

		p = arvore.getRaiz();
		pai = null;

		if (arvore.getRaiz() != null) {
			while (true) {
				c = obj.compareTo(p.getObj());

				if (c == 0) {
					return false;
				}
				if (c < 0) {
					if (p.getEsq() == null) {
						pai = p;
						direcao = -1;
						break;
					} else {
						p = p.getEsq();
					}
				} else {
					if (p.getDir() == null) {
						pai = p;
						direcao = 1;
						break;
					} else {
						p = p.getDir();
					}
				}
			}
		}
		arvore.insert(obj, pai, direcao);
		numItens++;
		return true;
	}
	
	
	public NoArvoreBin<E> getNo(E obj) {
		NoArvoreBin<E> no = arvore.raiz;
		while(no != null) {
			if(no.getObj().compareTo(obj) == 0) {
				return no;
			} else if(no.getObj().compareTo(obj) < 0) {
					no = no.getEsq();
			} else {
				no = no.getDir();
			}
		}
		return null;
	}
}
