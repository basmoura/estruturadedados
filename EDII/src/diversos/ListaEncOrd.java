package diversos;

public class ListaEncOrd<E extends Comparable<E>> extends ListaEnc<E> {

	@Override
	public boolean add(E obj) {
		NoSimpEnc<E> p = lista.getInicio();
		NoSimpEnc<E> pAnt = null;
		while ((p != null) && (obj.compareTo(p.getObj()) > 0)) {
			pAnt = p;
			p = p.getProx();
		}
		if (pAnt == null) {
			lista.insertAtBegin(obj);
		} else {
			lista.insertAfter(obj, pAnt);
		}
		numItens++;
		return true;
	}
}
