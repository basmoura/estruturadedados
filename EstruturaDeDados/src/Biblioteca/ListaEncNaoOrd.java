package Biblioteca;

import java.io.Serializable;

public class ListaEncNaoOrd<E extends Comparable<E>> extends ListaEnc<E> implements Serializable {

    @Override
    public boolean add(E obj) {
        lista.insertAtEnd(obj);
        numItens++;
        return true;
    }
}
