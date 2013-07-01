package utilitarios;

@SuppressWarnings("serial")
public abstract class ListaEnc <E extends Comparable<E>> extends ColecaoComparavel<E> {
	
	protected ListaSimpEnc<E> lista;
		
	public ListaEnc(){
		lista = new ListaSimpEnc<E>();
	}

	@Override
	public void clear() {
		lista.clear();		
	}
	
	protected E busque(E obj) {
		MyIterator<E> it = lista.iterator();
		E objTemp = it.getFirst();
		while (objTemp != null) {
			if (obj.compareTo(objTemp) == 0) {
				return objTemp;
			}
			objTemp = it.getNext();
		}			
		return null;
	}

	@Override
	public boolean remove(E obj) {
		MyIterator<E> it = lista.iterator();
		E objTemp = it.getFirst();
		while (objTemp != null) {
			if (obj.compareTo(objTemp) == 0) {
				it.remove();
				return true;
			}
			objTemp = it.getNext();
		}			
		return false;
	}

	@Override
	public E retrieve(E obj) {
		return busque(obj);
	}
	
	@Override
	public MyIterator<E> iterator() {
		return lista.iterator();
	}
	
	@Override
	public int size() {
		return lista.size();
	}
}
