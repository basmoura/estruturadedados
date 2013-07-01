package Biblioteca;

public class ListaDupEnc<E> {

    protected NoDupEnc<E> noCabeca;
    protected int numItens;

    private class ListaDupEncIterator implements MyIterator<E> {

        NoDupEnc<E> inicio = noCabeca.getProx();

        @Override
        public E getFirst() {
            if (numItens == 0) {
                return null;
            }
            return inicio.getObj();
        }

        @Override
        public E getNext() {
            if (inicio.getProx() == noCabeca) {
                return null;
            }
            inicio = inicio.getProx();
            return inicio.getObj();
        }

        @Override
        public void remove() {
            // TODO Auto-generated method stub
        }
    }

    public ListaDupEnc() {
        noCabeca = new NoDupEnc<E>();
    }

    public NoDupEnc<E> getNoCabeca() {
        return noCabeca;
    }

    public void clear() {
    }

    public boolean isEmpty() {
        return (numItens == 0);
    }

    public void insertAtBegin(E obj) {
        NoDupEnc<E> novoNo = new NoDupEnc<>(obj, noCabeca, noCabeca.getProx());
        numItens++;
    }

    public void insertAtEnd(E obj) {
        NoDupEnc novoNo = new NoDupEnc(obj, noCabeca.getAnt(), noCabeca);
        numItens++;
    }

    public void insertAfter(E obj, NoDupEnc<E> p) {
        NoDupEnc<E> novoNo = new NoDupEnc<>(obj, p, p.getProx());
        numItens++;
    }

    public void insertBefore(E obj, NoDupEnc<E> p) {
        NoDupEnc<E> novoNo = new NoDupEnc<>(obj, p.getAnt(), p);
        numItens++;
    }

    public E removeFromBegin() {
        return null;
    }

    public E remove(NoDupEnc<E> p) {
        return null;
    }

    public E removeFromEnd() {
        return null;
    }

    public int size() {
        return numItens;
    }

    public MyIterator<E> iterator() {
        return new ListaDupEncIterator();
    }
}
