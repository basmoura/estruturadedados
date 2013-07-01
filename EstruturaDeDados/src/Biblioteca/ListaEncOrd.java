package Biblioteca;

public class ListaEncOrd<E extends Comparable<E>> extends ListaEnc<E> {

    @Override
    public boolean add(E obj) {
        NoSimpEnc<E> noAnterior = null;
        NoSimpEnc<E> noAtual = lista.getInicio();

        while (noAtual != null) {
            if (obj.compareTo(noAtual.getObj()) == 1 || obj.compareTo(noAtual.getObj()) == 0) {
                if (noAnterior == null) {
                    lista.insertAtBegin(obj);
                    numItens++;
                    return true;
                } else {
                    lista.insertAfter(obj, noAnterior);
                    numItens++;
                    return true;
                }
            }
            noAnterior = noAtual;
            noAtual = noAtual.getProx();
        }

        lista.insertAtEnd(obj);
        numItens++;
        return true;
    }
}
