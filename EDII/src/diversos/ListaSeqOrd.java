package diversos;

public class ListaSeqOrd<E extends Comparable<E>> extends ListaSeq<E> {

	public ListaSeqOrd() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ListaSeqOrd(int capacidadeInicial, int incremento) {
		super(capacidadeInicial, incremento);
		// TODO Auto-generated constructor stub
	}

	public ListaSeqOrd(int capacidadeInicial) {
		super(capacidadeInicial);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected int busque(E obj) {
		int inicio, fim, meio;
		inicio = 0;
		fim = numItens - 1;
		while (inicio <= fim) {
			meio = (inicio + fim) / 2;
			int c = obj.compareTo(lista.elementAt(meio));
			if (c == 0)
				return meio;
			if (c > 0)
				inicio = meio + 1;
			else
				fim = meio - 1;
		}
		return -1;
	}

	protected int procuraPosicao(E obj) {
		int inicio, meio, fim, c;
		inicio = 0;
		fim = numItens - 1;
		while (inicio <= fim) {
			meio = (inicio + fim) / 2;
			c = obj.compareTo(lista.elementAt(meio));
			if (c > 0)
				inicio = meio + 1;
			else
				fim = meio - 1;
		}
		return inicio;
	}

	@Override
	public boolean add(E obj) {
		if (busque(obj) != -1) {
			return false;
		} else {
			lista.insertAt(procuraPosicao(obj), obj);
			return true;
		}
	}

}
