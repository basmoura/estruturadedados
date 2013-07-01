package utilitarios;

public class ListaEncNaoOrd<E extends Comparable<E>> extends ListaEnc<E> {
	
	public ListaEncNaoOrd() {
		super();		
	}

	@Override
	public boolean add(E obj) {
		if(obj != null) {
			lista.insertAtEnd(obj);
			return true;
		}
		return false;
	}
}
