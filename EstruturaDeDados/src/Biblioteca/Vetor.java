/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Biblioteca;

/**
 *
 * @author basmoura
 */
public class Vetor<E> {

    private final int CAPACIDADE_DEFAULT = 100;
    private E[] lista;
    protected int numItens;
    protected int capacidadeInicial, incremento;

    private class VetorIterator implements MyIterator<E> {

        private int indiceCorrente;

        @Override
        public E getFirst() {
            if (numItens == 0) {
                return null;
            }
            indiceCorrente = 0;
            return lista[0];
        }

        @Override
        public E getNext() {
            if (indiceCorrente == numItens - 1) {
                return null;
            }

            indiceCorrente++;
            return lista[indiceCorrente];
        }

        @Override
        public void remove() {
            removeAt(indiceCorrente);
            indiceCorrente--;
        }
    }

    public Vetor() {
        lista = (E[]) new Object[CAPACIDADE_DEFAULT];
        capacidadeInicial = CAPACIDADE_DEFAULT;
        incremento = 10;
    }

    public Vetor(int capacidadeInicial) {
        lista = (E[]) new Object[capacidadeInicial];
        this.capacidadeInicial = capacidadeInicial;
        incremento = 10;
    }

    public Vetor(int capacidadeInicial, int incremento) {
        lista = (E[]) new Object[capacidadeInicial];
        this.capacidadeInicial = capacidadeInicial;
        this.incremento = incremento;
    }

    private void redimensione() {
        E[] listaTemp = (E[]) new Object[lista.length + incremento];

        System.arraycopy(lista, 0, listaTemp, 0, lista.length);

        lista = listaTemp;
    }

    public void insertAtBegin(E obj) {
        if (numItens == lista.length) {
            redimensione();
        }

        for (int i = numItens; i >= 0; i--) {
            lista[i + 1] = lista[i];
        }

        lista[0] = obj;
        numItens++;
    }

    public boolean insertAt(int indice, E obj) {
        if (numItens == lista.length) {
            redimensione();
        }

        if (indice < 0 || indice > numItens) {
            return false;
        }

        if (indice == 0) {
            insertAtBegin(obj);
            numItens++;
            return true;
        }

        if (indice == numItens) {
            insertAtEnd(obj);
            numItens++;
            return true;
        }

        for (int i = numItens; i >= indice; i--) {
            lista[i + 1] = lista[i];
        }

        lista[indice] = obj;
        numItens++;

        return true;
    }

    public void insertAtEnd(E obj) {
        if (numItens == lista.length) {
            redimensione();
        }

        lista[numItens] = obj;
        numItens++;
    }

    public E elementAt(int indice) {
        if (indice >= 0 && indice < numItens) {
            return lista[indice];
        }
        return null;
    }

    public E removeFromBegin() {
        E objetoRemovido = lista[0];

        if (isEmpty()) {
            return null;
        }

        for (int i = 0; i < numItens; i++) {
            lista[i] = lista[i + 1];
        }
        numItens--;

        return objetoRemovido;
    }

    public E removeAt(int indice) {
        if (isEmpty()) {
            return null;
        }

        E objetoRemovido = lista[indice];
        lista[indice] = null;

        for (int i = indice; i < numItens; i++) {
            lista[i] = lista[i + 1];
        }

        numItens--;

        return objetoRemovido;
    }

    public E removeFromEnd() {
        if (isEmpty()) {
            return null;
        }

        E objetoRemovido = lista[numItens - 1];
        lista[numItens - 1] = null;
        numItens--;

        return objetoRemovido;
    }

    public boolean replace(int indice, E obj) {
        if (indice < 0 || indice > numItens) {
            return false;
        }
        
        if (isEmpty()) {
            return false;
        }
        
        lista[indice] = obj;
        
        return true;
    }

    public boolean isEmpty() {
        return (numItens == 0);
    }

    public int size() {
        return numItens;
    }

    public void clear() {
        for (int i = 0; i < numItens; i++) {
            lista[i] = null;
        }
        lista = (E[]) new Object[CAPACIDADE_DEFAULT];
        numItens = 0;
    }

    public MyIterator<E> iterator() {
        return new VetorIterator();

    }
}
