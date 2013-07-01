package Biblioteca;

public class PilhaArray<E> extends Pilha<E> {

    private Vetor<E> pilha;

    public PilhaArray(int tamInicial) {
        pilha = new Vetor<>(tamInicial);
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
        if (obj != null) {
            pilha.insertAtEnd(obj);
        }
    }

    @Override
    public E desempilhe() {
        E obj = pilha.removeFromEnd();
        if (obj != null) {
        }
        return obj;
    }

    @Override
    public E getTopo() {
        return pilha.elementAt(numItens - 1);
    }

    @Override
    public int size() {
        return pilha.size();
    }
}