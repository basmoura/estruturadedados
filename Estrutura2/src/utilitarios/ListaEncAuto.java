package utilitarios;

public class ListaEncAuto<E extends Comparable<E>> extends ListaEncNaoOrd<E> {
	
	@Override
	public boolean add(E obj) {
		if(obj != null) {
			lista.insertAtBegin(obj);
			return true;
		}
		return false;
	}
	
	@Override
	public E retrieve(E obj) {
		E objTemp = busque(obj);
		mudaPosicao(objTemp);
		return objTemp;
	}
	
	public boolean mudaPosicao(E obj) {
		NoSimpEnc<E> p = lista.getInicio();
		NoSimpEnc<E> pAnt = null;
		if(obj == null || p == null || obj.compareTo(p.getObj()) == 0) {
			return false;
		}
		if (obj.compareTo(p.getProx().getObj()) == 0) {
			lista.removeAfter(p);
			lista.insertAtBegin(obj);
			return true;
		}
		//procura posição
		while (p != null) {	
			if(obj.compareTo(p.getProx().getObj()) == 0) {
				lista.removeAfter(p);
				lista.insertAfter(obj, pAnt);
				return true;
			}
			pAnt = p;
			p = p.getProx();
		}			
		return false;
	}
}
