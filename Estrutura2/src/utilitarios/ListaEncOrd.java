package utilitarios;

@SuppressWarnings("serial")
public class ListaEncOrd<E extends Comparable<E>> extends ListaEnc<E> {

    public ListaEncOrd() {
        super();
    }

    @Override
    public boolean add(E obj) {
        NoSimpEnc<E> p = lista.getInicio();
        if (p == null) {
            lista.insertAtBegin(obj);
            return true;
        }
        if (obj.compareTo(p.getObj()) < 0) {
            lista.insertAtBegin(obj);
            return true;
        }
        //procura posi��o
        while (p != null) {
            if (p.getProx() != null) {
                if (obj.compareTo(p.getObj()) >= 0 && obj.compareTo(p.getProx().getObj()) <= 0) {
                    lista.insertAfter(obj, p);
                    return true;
                } else if (p.getProx().getProx() == null) {
                    lista.insertAtEnd(obj);
                    return true;
                }
            } else if (p.getProx() == null) {
                lista.insertAtEnd(obj);
                return true;
            }
            p = p.getProx();
        }
        return false;
    }
}
