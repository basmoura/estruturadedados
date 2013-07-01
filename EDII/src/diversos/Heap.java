package diversos;

@SuppressWarnings("serial")
public class Heap<E extends Comparable<E>> extends ColecaoComparavel<E> {

	public enum TipoHeap {
		MinHeap, MaxHeap
	}
	
	public class HeapIterator implements MyIterator<E> {
		
		private Heap<E> iteratorHeap;
		private E current;
		
		private void VetorCopy(Vetor<E> a) {
			for (int i = 0; i < size(); i++) {
				iteratorHeap.heapArray.insertAtEnd(a.elementAt(i));
			}
 		}
		
		@Override
		public E getFirst() {
			iteratorHeap = new Heap<E>(size() * 2, tipoHeap);
			VetorCopy(heapArray);
			current = iteratorHeap.remove();
			return current;
		}

		@Override
		public E getNext() {
			current = iteratorHeap.remove();
			return current;
		}

		@Override
		public void remove() {
			int index = 0;
			boolean found = false;
			
			for (int i = 0; i < size(); i++) {
				if (heapArray.elementAt(i).compareTo(current) == 0) {
					found = true;
					index = i;
					break;
				}
			}
			
			if (found) {
				E lastNode = heapArray.elementAt(size() - 1);
				heapArray.replace(index, lastNode);
				heapArray.removeFromEnd();
				borbulheParaBaixo(index);
			}
			
		}
		
	}

	private Vetor<E> heapArray;
	private TipoHeap tipoHeap;

	public Heap(int capInicial) {
		this(capInicial, TipoHeap.MinHeap);
	}
	
	public Heap(int capInicial, TipoHeap tipoHeap) {
		heapArray = new Vetor<E>(capInicial);
		this.tipoHeap = tipoHeap;
	}
	
	public int size() {
		return heapArray.size();
	}

	public E remove() {
		if (size() > 0) {
			E returnObj = heapArray.removeFromBegin();
			if (size() > 0) {
				E lastObj = heapArray.removeFromEnd();
				heapArray.insertAtBegin(lastObj);
				borbulheParaBaixo(0);
			}
			return returnObj;
		}
		return null;
	}

	@Override
	public boolean add(E obj) {
		heapArray.insertAtEnd(obj);
		borbulheParaCima(size()-1);
		return false;
	}

	@Override
	public void clear() {
		heapArray.clear();
		numItens = 0;
	}

	@Override
	public boolean contains(E obj) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean remove(E obj) {
		throw new UnsupportedOperationException();
	}

	@Override
	public MyIterator<E> iterator() {
		return new HeapIterator();
	}

	@Override
	public E retrieve(E obj) {
		throw new UnsupportedOperationException();
	}
	
	public void debug() {
		for (int i = 0; i < size(); i++) {
			E pai = null;
			E filhoEsquerdo = null;
			E filhoDireito = null;
			if (i != 0) {
				pai = heapArray.elementAt((i - 1) / 2);
			}
			filhoEsquerdo = heapArray.elementAt((i * 2) + 1);
			filhoDireito = heapArray.elementAt((i * 2) + 2);
			System.out.println(heapArray.elementAt(i) + "\t\tPai: " + pai + "\t\tFilho Esquerdo: " + filhoEsquerdo + "\t\tFilho Direito: " + filhoDireito);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void borbulheParaBaixo(int indice) {
		
		int filho;
		int filhoDireito, filhoEsquerdo;
		Comparable infoTemp;
		
		infoTemp = heapArray.elementAt(indice);
		
		while ((2*indice + 2) <= size()){
			filhoEsquerdo = 2* indice + 1;
			filhoDireito = filhoEsquerdo + 1;
			
			boolean firstComp = compareTipoHeap(
					filhoDireito <= (size() - 1) && (heapArray.elementAt(filhoDireito)).compareTo(heapArray.elementAt(filhoEsquerdo)) > 0,
					filhoDireito <= (size() - 1) && (heapArray.elementAt(filhoDireito)).compareTo(heapArray.elementAt(filhoEsquerdo)) < 0);
			
			if(firstComp) {
				filho = filhoDireito;
			} else {
				filho = filhoEsquerdo;
			}
			boolean secondComp = compareTipoHeap(
					infoTemp.compareTo(heapArray.elementAt(filho)) > 0,
					infoTemp.compareTo(heapArray.elementAt(filho)) < 0);
			
			if (secondComp) {
				break;
			}
			
			heapArray.replace(indice, heapArray.elementAt(filho));
			indice = filho;		
		}
		heapArray.replace(indice, (E)infoTemp);
		
	}
	
	private void borbulheParaCima(int indice) {
		int indicePai;
		E infoTemp;
		indicePai = (indice - 1) / 2;
		infoTemp = heapArray.elementAt(indice);
		
		boolean comparation = compareTipoHeap((indice > 0) && (heapArray.elementAt(indicePai).compareTo(infoTemp) < 0),
				(indice > 0) && (heapArray.elementAt(indicePai).compareTo(infoTemp) > 0));
		
		while(comparation) {
			heapArray.replace(indice, heapArray.elementAt(indicePai));
			indice = indicePai;
			indicePai = (indice - 1) / 2;
			comparation = compareTipoHeap((indice > 0) && (heapArray.elementAt(indicePai).compareTo(infoTemp) < 0),
					(indice > 0) && (heapArray.elementAt(indicePai).compareTo(infoTemp) > 0));
		}
		
		heapArray.replace(indice, infoTemp);
	}
	
	private boolean compareTipoHeap(boolean a, boolean b) {
		return tipoHeap == TipoHeap.MaxHeap ? a : b; 
	}

}
