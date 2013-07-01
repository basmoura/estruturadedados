package Biblioteca;

public class ArvoreBusca<E extends Comparable<E>> extends ColecaoComparavel<E> {

    private class ArvoreBuscaIterator implements MyIterator<E> {

        FilaEnc<E> fila;
        private MyIterator<E> it;

        public void simetrica(NoArvoreBin<E> no) {
            if (no != null) {
                simetrica(no.getEsq());
                if (!no.isDeletado()) {
                    fila.insira(no.getObj());
                }
                simetrica(no.getDir());
            }
            it = fila.iterator();
        }

        @Override
        public E getFirst() {
            fila = new FilaEnc<>();
            simetrica(arvore.getRaiz());
            return it.getFirst();
        }

        @Override
        public E getNext() {
            return it.getNext();
        }

        @Override
        public void remove() {
            it.remove();
        }
    }
    protected ArvoreBin<E> arvore;

    public ArvoreBusca() {
        arvore = new ArvoreBin<>();
    }

    @Override
    public void clear() {
        numItens = 0;
    }

    @Override
    public int size() {
        return numItens;
    }

    @Override
    public MyIterator<E> iterator() {
        return new ArvoreBuscaIterator();
    }

    @Override
    public boolean remove(E obj) {
        NoArvoreBin<E> p;
        int c;
        p = arvore.getRaiz();

        while (p != null) {
            c = obj.compareTo(p.getObj());
            if (c == 0) {
                break;
            }
            if (c < 0) {
                p = p.getEsq();
            } else {
                p = p.getDir();
            }
        }

        if (p == null) {
            return false;
        }

        if ((p.getDir() != null) && (p.getEsq() != null)) {
            NoArvoreBin<E> p1;
            p1 = p.getEsq();

            while (p1.getDir() != null) {
                p1 = p1.getDir();
            }
            p.setObj(p1.getObj());
            p = p1;
        }

        arvore.delete(p);
        numItens--;

        return true;
    }

    @Override
    public E retrieve(E obj) {
        int c;
        NoArvoreBin<E> p = arvore.getRaiz();

        while (p != null) {
            c = obj.compareTo(p.getObj());
            if (!p.isDeletado()) {
                if (c == 0) {
                    return p.getObj();
                } else if (c > 0) {
                    p = p.getDir();
                } else {
                    p = p.getEsq();
                }

            } else {
                return null;
            }
        }
        return null;
    }

    @Override
    public boolean add(E obj) {
        int direcao = -1;
        NoArvoreBin<E> p, pai;
        int c;

        p = arvore.getRaiz();
        pai = null;

        if (arvore.getRaiz() != null) {
            while (true) {
                c = obj.compareTo(p.getObj());

                if (c == 0) {
                    return false;
                }
                if (c < 0) {
                    if (p.getEsq() == null) {
                        pai = p;
                        direcao = -1;
                        break;
                    } else {
                        p = p.getEsq();
                    }
                } else {
                    if (p.getDir() == null) {
                        pai = p;
                        direcao = 1;
                        break;
                    } else {
                        p = p.getDir();
                    }
                }
            }
        }

        arvore.insert(obj, pai, direcao);

        numItens++;
        return true;
    }
}
