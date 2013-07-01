package Biblioteca;

/**
 *
 * @author haroldofurtado
 */
public class ListaSeqNaoOrd<E extends Comparable<E>> extends ListaSeq<E> {

    public ListaSeqNaoOrd() {
    }

    public ListaSeqNaoOrd(int capacidadeInicial) {
        super(capacidadeInicial);
    }

    public ListaSeqNaoOrd(int capacidadeInicial, int incremento) {
        super(capacidadeInicial, incremento);
    }

    @Override
    protected int busque(E obj) {
        for (int i = 0; i < size(); i++) {
            if (lista.elementAt(i).compareTo(obj) == 0) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean add(E obj) {
        lista.insertAtEnd(obj);
        numItens++;
        return true;
    }
}
