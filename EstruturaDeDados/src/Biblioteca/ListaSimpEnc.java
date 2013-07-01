package Biblioteca;

import java.io.Serializable;

public class ListaSimpEnc<E> implements Serializable {

    protected NoSimpEnc<E> inicio;
    protected NoSimpEnc<E> fim;
    protected int numItens;

    private class ListaSimpEncIterator implements MyIterator<E> {

        private NoSimpEnc<E> noAnterior, noCorrente = getInicio();

        @Override
        public E getFirst() {
            if (getInicio() != null) {
                noAnterior = null;
                noCorrente = getInicio();
                return getInicio().getObj();
            }
            return null;
        }

        @Override
        public E getNext() {
            if (noCorrente == null || noCorrente.getProx() == null) {
                return null;
            }
            noAnterior = noCorrente;
            noCorrente = noCorrente.getProx();
            return noCorrente.getObj();
        }

        @Override
        public void remove() {
            if (inicio == null) {
                return;
            }
            if (inicio == noCorrente) {
                inicio = noCorrente.getProx();
                noCorrente = noCorrente.getProx();
            } else {
                noAnterior.setProx(noCorrente.getProx());
                noCorrente = noCorrente.getProx();
            }
            numItens--;
        }
    }

//    public ListaSimpEnc() {
//        super();
//    }
    // Retorna a referencia ao in�cio da lista
    public NoSimpEnc<E> getInicio() {
        return inicio;
    }

    // Retorna a referencia ao final da lista
    public NoSimpEnc<E> getFim() {
        return fim;
    }

    // Esvazia a lista
    public void clear() {
        inicio = fim = null;
    }

    // Informa se a lista est� vazia ou n�o
    public boolean isEmpty() {
        if (size() == 0) {
            return true;
        }

        return false;
    }

    // Insere um no no in�cio da lista
    public void insertAtBegin(E obj) {

        NoSimpEnc<E> novoNo = new NoSimpEnc<>(obj);

        if (inicio == null) {
            inicio = fim = novoNo;
        } else {
            novoNo.setProx(inicio);
            inicio = novoNo;
        }
        numItens++;
    }

    // Insere um no no final da lista
    public void insertAtEnd(E obj) {
        NoSimpEnc<E> novoNo = new NoSimpEnc<>(obj);

        if (inicio == null) {
            inicio = fim = novoNo;
        } else {
            fim.setProx(novoNo);
            fim = novoNo;
        }
        numItens++;
    }

    // Insere um no ap0s o no apontado por p
    public void insertAfter(E obj, NoSimpEnc<E> p) {
        NoSimpEnc<E> novoNo = new NoSimpEnc<>(obj, p.getProx());
        if (p == fim) {
            fim = novoNo;
        }
        p.setProx(novoNo);
        numItens++;
    }

    // Remover o primeiro no da lista,
    // retornando a refer�ncia ao objeto que se 
    // encontra armazenado nele.
    // Se a lista estiver vazia retorna null
    public E removeFromBegin() {

        if (inicio == null) {
            return null;
        }

        E obj = inicio.getObj();
        inicio = inicio.getProx();
        numItens--;

        return obj;

    }

    // Remover o no que segue o no apontado por p,
    // retornando a referencia ao objeto que se encontra
    // armazenado nele.
    // Se a lista estiver vazia ou se nao existir o
    // proximo no, retorna null
    public E removeAfter(NoSimpEnc<E> p) {
        NoSimpEnc<E> noRemovido = p;
        if (p.getProx() == fim) {
            fim = p;
        }

        if (p.getProx() == null) {
            return null;
        }

        p.setProx(p.getProx().getProx());
        numItens--;
        return noRemovido.getObj();
    }

    // Retorna o tamanho da lista
    public int size() {
        return numItens;
    }

    public MyIterator<E> iterator() {
        return new ListaSimpEncIterator();
    }
}
