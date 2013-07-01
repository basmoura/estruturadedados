package Biblioteca;

public class FilaEnc<E> extends Fila<E> {

    private ListaSimpEnc<E> fila = new ListaSimpEnc<>();

    @Override
    public void clear() {
        fila.clear();
    }

    @Override
    public MyIterator<E> iterator() {
        return fila.iterator();
    }

    @Override
    public E remova() {
        if (fila.isEmpty()) {
            return null;
        }

        E obj = fila.removeFromBegin();
        //numItens--;
        return obj;
    }

    @Override
    public boolean insira(E obj) {
        fila.insertAtEnd(obj);
        //numItens++;
        return true;
    }

    @Override
    public int size() {
        return fila.size();
    }
}
