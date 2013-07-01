package Biblioteca;

public class PilhaEnc<E> extends Pilha<E> {

    private ListaSimpEnc<E> pilha;

    public PilhaEnc() {
        pilha = new ListaSimpEnc<>();
    }

    @Override
    public void clear() {
        pilha.clear();
    }

    @Override
    public MyIterator<E> iterator() {
        return pilha.iterator();
    }

    @Override
    public void empilhe(E obj) {
        pilha.insertAtBegin(obj);
    }

    @Override
    public E desempilhe() {
        return pilha.removeFromBegin();
    }

    @Override
    public E getTopo() {
        return pilha.getInicio().getObj();
    }

    @Override
    public int size() {
        return pilha.size();
    }
}
