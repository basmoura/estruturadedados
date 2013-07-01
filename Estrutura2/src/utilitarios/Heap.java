package utilitarios;

public class Heap<E extends Comparable<E>> extends ColecaoComparavel<E> {

    public enum TipoHeap {
        MinHeap, MaxHeap
    }
    protected Vetor<E> heapArray;
    private TipoHeap tipoHeap;

    private class HeapIterador implements MyIterator<E> {

        Heap<E> heapTemp;
        private E temp;

        @Override
        public E getFirst() {
            if (numItens > 0) {
                heapTemp = new Heap<>(numItens - 1, tipoHeap);
                MyIterator<E> it = heapArray.iterator();
                E obj = it.getFirst();
                while (obj != null) {
                    heapTemp.add(obj);
                    obj = it.getNext();
                }
                temp = heapTemp.remove();
                return temp;
            }
            return null;
        }

        @Override
        public E getNext() {
            temp = heapTemp.remove();
            return temp;
        }

        @Override
        public void remove() {
            MyIterator<E> it = heapArray.iterator();
            E obj = it.getFirst();
            while (obj != null) {
                if (obj.equals(temp)) {
                    it.remove();
                    break;
                }
                obj = it.getNext();
            }
            numItens--;
            borbulheParaBaixo(0);
        }
    }

    public Heap(int capInicial, TipoHeap tipoHeap) {
        heapArray = new Vetor<>(capInicial);
        this.tipoHeap = tipoHeap;
    }

    public E remove() {
        if (size() == 0) {
            return null;
        }
        E temp = heapArray.removeFromBegin();
        heapArray.insertAtBegin(heapArray.removeFromEnd());
        numItens--;
        borbulheParaBaixo(0);
        return temp;
    }

    @Override
    public boolean add(E obj) {
        heapArray.insertAtEnd(obj);
        numItens++;
        borbulheParaCima(size() - 1);
        return true;
    }

    @Override
    public void clear() {
        heapArray.clear();
        numItens = 0;
    }

    @Override
    public boolean contains(E obj) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(E obj) {
        throw new UnsupportedOperationException();
    }

    @Override
    public MyIterator<E> iterator() {
        return new HeapIterador();
    }

    @Override
    public E retrieve(E obj) {
        throw new UnsupportedOperationException();
    }

    public void borbulheParaCima(int indice) {
        int indicePai;
        E infoTemp;
        indicePai = (indice - 1) / 2;
        infoTemp = heapArray.elementAt(indice);
        if (tipoHeap.name().equals("MinHeap")) {
            while ((indice > 0) && (heapArray.elementAt(indicePai).compareTo(infoTemp) > 0)) {
                heapArray.replace(indice, heapArray.elementAt(indicePai));
                indice = indicePai;
                indicePai = (indice - 1) / 2;
            }
        } else {
            while ((indice > 0) && (heapArray.elementAt(indicePai).compareTo(infoTemp) < 0)) {
                heapArray.replace(indice, heapArray.elementAt(indicePai));
                indice = indicePai;
                indicePai = (indice - 1) / 2;
            }
        }
        heapArray.replace(indice, infoTemp);
    }

    public void borbulheParaBaixo(int indice) {
        int menorFilho;
        int filhoDireito, filhoEsquerdo;
        E infoTemp;
        infoTemp = heapArray.elementAt(indice);
        while ((2 * indice + 2) <= size()) {
            filhoEsquerdo = 2 * indice + 1;
            filhoDireito = filhoEsquerdo + 1;
            // Procura o menor filho
            if (tipoHeap.name().equals("MinHeap")) {
                if ((filhoDireito <= size() - 1)
                        && (heapArray.elementAt(filhoDireito).compareTo(
                        heapArray.elementAt(filhoEsquerdo)) < 0)) {
                    menorFilho = filhoDireito;
                } else {
                    menorFilho = filhoEsquerdo;
                }
                if (infoTemp.compareTo(heapArray.elementAt(menorFilho)) < 0) {
                    break;
                }
            } else {
                if ((filhoDireito <= size() - 1)
                        && (heapArray.elementAt(filhoDireito).compareTo(
                        heapArray.elementAt(filhoEsquerdo)) > 0)) {
                    menorFilho = filhoDireito;
                } else {
                    menorFilho = filhoEsquerdo;
                }
                if (infoTemp.compareTo(heapArray.elementAt(menorFilho)) > 0) {
                    break;
                }
            }
            heapArray.replace(indice, heapArray.elementAt(menorFilho));
            indice = menorFilho;
        }
        heapArray.replace(indice, infoTemp);
    }
}
