package Biblioteca;

public class FilaArray<E> extends Fila<E> {

    private E[] fila;
    private int inicio, fim = -1,
            NMaximo;

    private class FilaArrayIterator implements MyIterator<E> {

        private int indiceCorrente = -1;

        @Override
        public E getFirst() {
            if (numItens == 0) {
                return null;
            }
            indiceCorrente = inicio;
            return fila[indiceCorrente];
        }

        @Override
        public E getNext() {
            if (inicio == fim || numItens == 0 || indiceCorrente == fim) {
                return null;
            }

            indiceCorrente++;
            return fila[indiceCorrente];
        }

        @Override
        public void remove() {
            if (indiceCorrente == inicio) {
                remova();
            } else {

                while (indiceCorrente <= fim) {
                    if (indiceCorrente != fila.length - 1) {
                        fila[indiceCorrente] = fila[indiceCorrente + 1];
                        indiceCorrente++;
                    } else {
                        fila[indiceCorrente] = fila[0];
                        indiceCorrente = 0;
                    }
                }
                fim = fim - 1;
                numItens--;
            }
        }
    }

    public FilaArray(int tamFila, int nmaximo) {
        fila = (E[]) new Object[tamFila];
        NMaximo = nmaximo;
    }

    @Override
    public void clear() {
        for (int i = 0; i < fila.length; i++) {
            fila[i] = null;
        }
        numItens = 0;
        fim = -1;
    }

    @Override
    public MyIterator<E> iterator() {
        return new FilaArrayIterator();
    }

    @Override
    public E remova() {
        if (numItens == 0) {
            return null;
        } else {
            E obj = fila[inicio];
            if (inicio == NMaximo) {
                inicio = 0;
                numItens--;
                return obj;
            } else {
                inicio = inicio + 1;
                numItens--;
                return obj;
            }
        }
    }

    @Override
    public boolean insira(E obj) {
        if (NMaximo > numItens) {
            if (fim == NMaximo) {
                fim = 0;
                fila[fim] = obj;
                numItens++;
                return true;
            } else {
                fim = fim + 1;
                fila[fim] = obj;
                numItens++;
                return true;
            }
        }
        return false;
    }
}
