package Biblioteca;

public class HashEnc<E extends Comparable<E>> extends ColecaoComparavel<E> {

    private ListaEncNaoOrd<E>[] tabela;

    public class HashIterator implements MyIterator<E> {

        int indCorrente = 0;
        MyIterator<E> it;
        E obj;
        E objAux;

        @Override
        public E getFirst() {
            for (int i = indCorrente; i < tabela.length; i++) {
                if (!tabela[i].isEmpty()) {
                    indCorrente = i;
                    it = tabela[i].iterator();
                    obj = it.getFirst();
                    objAux = obj;
                    obj = it.getNext();
                    return objAux;
                }
            }
            return null;
        }

        @Override
        public E getNext() {
            if (obj == null) {
                indCorrente++;
                return getFirst();
            } else {
                objAux = obj;
                obj = it.getNext();
                return objAux;
            }
        }

        @Override
        public void remove() {
            tabela[indCorrente].remove(objAux);
            numItens--;
        }
    }

    protected int funcHash(Object obj) {
        return Math.abs(obj.hashCode()) % tabela.length;
    }

    static boolean isPrime(int x) {
        int divisor;
        if (x < 4) {
            return true;
        }

        if ((x % 2) == 0) {
            return false;
        }

        divisor = 3;

        while (!((divisor * divisor > x) || (x % divisor == 0))) {
            divisor = divisor + 2;
        }

        if (divisor * divisor > x) {
            return true;
        } else {
            return false;
        }
    }

    static int nextPrime(int x) {
        if (x < 2) {
            return 3;
        }

        if (x % 2 == 0) {
            x++;
        } else {
            x = x + 2;
        }

        while (!isPrime(x)) {
            x = x + 2;
        }

        return x;
    }

    public HashEnc(int tamTabela) {
        if (!isPrime(tamTabela)) {
            tamTabela = nextPrime(tamTabela);
        }

        tabela = new ListaEncNaoOrd[tamTabela];

        for (int i = 0; i < tabela.length; i++) {
            tabela[i] = new ListaEncNaoOrd<>();
        }
    }

    private void redimensione() {
        int numItensAux = numItens;
        int novoTamanho = tabela.length * 2;

        if (!isPrime(novoTamanho)) {
            novoTamanho = nextPrime(novoTamanho);
        }

        ListaEncNaoOrd<E>[] novaTabela = tabela;

        tabela = new ListaEncNaoOrd[novoTamanho];
        for (int i = 0; i < tabela.length; i++) {
            tabela[i] = new ListaEncNaoOrd<>();
        }

        for (int i = 0; i < novaTabela.length; i++) {
            if ((!novaTabela[i].isEmpty())) {
                MyIterator<E> it = novaTabela[i].iterator();
                E obj = it.getFirst();
                while (obj != null) {
                    add(obj);
                    obj = it.getNext();
                }
            }
        }
        numItens = numItensAux;
    }

    @Override
    public boolean add(E obj) {
        if (numItens >= 1.75 * tabela.length) {
            redimensione();
        }

        int posicao = funcHash(obj);

        if (!tabela[posicao].isEmpty()) {
            E objeto = tabela[posicao].retrieve(obj);
            if (objeto == null) {
                tabela[posicao].add(obj);
                numItens++;
                return true;
            }
            return false;
        } else {
            tabela[posicao].add(obj);
            numItens++;
            return true;
        }
    }

    @Override
    public E retrieve(E obj) {
        int posicao = funcHash(obj);

        if (!tabela[posicao].isEmpty()) {
            return tabela[posicao].retrieve(obj);
        }
        return null;
    }

    @Override
    public boolean remove(E obj) {
        int posicao = funcHash(obj);
        if (!tabela[posicao].isEmpty()) {
            E objeto = tabela[posicao].retrieve(obj);
            if (objeto == null) {
                return false;
            } else {
                tabela[posicao].remove(obj);
                numItens--;
                return true;
            }
        } 
        return false;
    }

    @Override
    public void clear() {
        numItens = 0;
        for (int i = 0; i < tabela.length; i++) {
            tabela[i] = new ListaEncNaoOrd<>();
        }
    }

    @Override
    public MyIterator<E> iterator() {
        return new HashIterator();
    }
}
