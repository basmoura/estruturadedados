package Biblioteca;

@SuppressWarnings("serial")
public abstract class ListaEnc<E extends Comparable<E>> extends ColecaoComparavel<E> {

    protected ListaSimpEnc<E> lista;

    public ListaEnc() {
        lista = new ListaSimpEnc<>();
    }

    @Override
    public void clear() {
        lista.clear();
        numItens = 0;
    }

    @Override
    public MyIterator<E> iterator() {
        return lista.iterator();
    }

    @Override
    public boolean remove(E obj) {
        MyIterator<E> it = lista.iterator();
        E item = it.getFirst();

        while (item != null) {
            if (item.compareTo(obj) == 0) {
                it.remove();
                numItens--;
                return true;
            }
            item = it.getNext();
        }
        return false;
    }

    @Override
    public E retrieve(E obj) {
        MyIterator<E> it = lista.iterator();
        E item = it.getFirst();

        while (item != null) {
            if (item.compareTo(obj) == 0) {
                return item;
            }
            item = it.getNext();
        }
        return null;
    }

    @Override
    public abstract boolean add(E obj);
}
