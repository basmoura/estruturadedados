/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Biblioteca;

/**
 *
 * @author breno
 */
public class ListaSeqOrd<E extends Comparable<E>> extends ListaSeq<E> {

    public ListaSeqOrd() {
    }

    public ListaSeqOrd(int capacidadeInicial) {
        super(capacidadeInicial);
    }

    public ListaSeqOrd(int capacidadeInicial, int incremento) {
        super(capacidadeInicial, incremento);
    }

    @Override
    protected int busque(Comparable obj) {
        int inicio, fim, meio, c;
        inicio = 0;
        fim = lista.size() - 1;

        while (inicio <= fim) {
            meio = (inicio + fim) / 2;
            c = obj.compareTo(lista.elementAt(meio));
            if (c == 0) {
                return meio;
            }
            if (c > 0) {
                inicio = meio + 1;
            } else {
                fim = meio - 1;
            }
        }
        return -1;
    }

    protected int procuraPosicao(E obj) {
        int inicio, fim, meio, c;
        inicio = 0;
        fim = lista.size() - 1;

        while (inicio <= fim) {
            meio = (inicio + fim) / 2;
            c = obj.compareTo(lista.elementAt(meio));
            if (c > 0) {
                inicio = meio + 1;
            } else {
                fim = meio - 1;
            }
        }
        return inicio;
    }

    @Override
    public boolean add(E obj) {
        if (busque(obj) != -1) {
            return false;
        } else {
            lista.insertAt(procuraPosicao(obj), obj);
            numItens++;
            return true;
        }
    }
}
