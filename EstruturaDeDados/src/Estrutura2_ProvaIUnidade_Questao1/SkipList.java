package Estrutura2_ProvaIUnidade_Questao1;

import Biblioteca.ColecaoComparavel;
import Biblioteca.MyIterator;

/**
 *
 * @author basmoura
 */
public class SkipList<E extends Comparable<E>> extends ColecaoComparavel<E> {

    private SkipNo<E>[] lista;
    private int nivelAtual;

    private class SkipListIterator implements MyIterator<E> {

        private SkipNo<E> noAnterior, noCorrente = lista[0].prox[0];

        @Override
        public E getFirst() {
            if (noCorrente != null) {
                return noCorrente.obj;
            }

            return null;
        }

        @Override
        public E getNext() {
            if (noCorrente != null) {
                E obj = noCorrente.prox[0].obj;
                noCorrente = noCorrente.prox[0];
                return obj;
            }

            return null;
        }

        @Override
        public void remove() {
        }
    }

    // Construtor
    public SkipList(int nivelMaximo) {
        lista = new SkipNo[nivelMaximo];
    }

    @Override
    public void clear() {
        for (int i = 0; i < lista.length; i++) {
            lista[i] = null;
        }
    }

    @Override
    public MyIterator<E> iterator() {
        return new SkipListIterator();
    }

    @Override
    public boolean remove(E obj) {
        SkipNo<E> p = lista[nivelAtual];
        SkipNo<E>[] pAnt = new SkipNo[lista.length];
        for (int i = nivelAtual; i >= 0; i--) {
            while (p.prox[i] != null && p.prox[i].obj.compareTo(obj) < 0) {
                p = p.prox[i];
            }
            pAnt[i] = p;
        }
        p = p.prox[0];
        if (p != null && p.obj.compareTo(obj) == 0) {
            p.obj = null;
            for (int i = 0; i <= nivelAtual; i++) {
                if (pAnt[i].prox[i] != p) {
                    break;
                }
                pAnt[i].prox[i] = p.prox[i];
            }

            while (nivelAtual > 0 && lista[nivelAtual].prox[nivelAtual] == null) {
                nivelAtual--;
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public E retrieve(E obj) {
        E result = busca(obj);
        if (result != null) {
            return result;
        }
        return null;
    }

    private int nivelDoNo() {
        int newLevel = 0;
        while (newLevel < lista.length && Math.random() < 0.5
                && newLevel <= nivelAtual) {
            newLevel++;
        }
        return newLevel;
    }

    @Override
    public boolean add(E obj) {
        SkipNo<E> p = lista[nivelAtual];
        SkipNo<E>[] pAnt = new SkipNo[lista.length];
        for (int i = nivelAtual; i >= 0; i--) {
            while (p.prox[i] != null && p.prox[i].obj.compareTo(obj) < 0) {
                p = p.prox[i];
            }
            pAnt[i] = p;
        }

        p = p.prox[0];
        if (p == null || !(p.obj.compareTo(obj) == 0)) {
            int nivel = nivelDoNo();
            if (nivel > nivelAtual) {
                pAnt[nivel] = lista[nivelAtual];
                nivelAtual = nivel;
            }
            p = new SkipNo<>(nivel, obj);
            for (int i = 0; i <= nivel; i++) {
                p.prox[i] = pAnt[i].prox[i];
                pAnt[i].prox[i] = p;
            }
            return true;
        } else {
            return false;
        }
    }

    private E busca(E obj) {
        SkipNo<E> p = lista[nivelAtual];
        for (int i = nivelAtual; i >= 0; i--) {
            while (p.prox[i] != null && p.prox[i].obj.compareTo(obj) < 0) {
                p = p.prox[i];
            }
            if (p.obj.compareTo(obj) == 0) {
                return p.obj;
            }
        }
        return null;
    }
}
