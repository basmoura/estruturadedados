package Biblioteca;

/**
 *
 * @author haroldofurtado
 */
public abstract class ListaSeq<E extends Comparable<E>> extends ColecaoComparavel<E> {

    protected Vetor<E> lista;

    private class ListaIterator implements MyIterator<E> {

        private MyIterator<E> it = lista.iterator();

        @Override
        public E getFirst() {
            return it.getFirst();
        }

        @Override
        public E getNext() {
            return it.getNext();
        }

        @Override
        public void remove() {
            it.remove();
            numItens--;
        }
    }

    //Construtor vazio
    public ListaSeq() {
        lista = new Vetor<>();
    }

    //Construtor
    public ListaSeq(int capacidadeInicial) {
        lista = new Vetor<>(capacidadeInicial);
    }

    //Construtor
    public ListaSeq(int capacidadeInicial, int incremento) {
        lista = new Vetor<>(capacidadeInicial, incremento);
    }

    @Override
    public boolean remove(E obj) {
        int posicao = busque(obj);
        if (posicao > 0) {
            lista.removeAt(posicao);
            numItens--;
            return true;
        }
        return false;
    }

    @Override
    public E retrieve(E obj) {
        int posicao = busque(obj);
        if (posicao >= 0) {
            return lista.elementAt(posicao);
        }
        return null;
    }

    //OK
    @Override
    public void clear() {
        lista.clear();
        numItens = 0;
    }

    protected abstract int busque(E obj);

    @Override
    public MyIterator<E> iterator() {
        return new ListaIterator();
    }
}
