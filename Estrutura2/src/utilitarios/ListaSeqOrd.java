package utilitarios;

public class ListaSeqOrd <E extends Comparable<E>> 
                            extends ListaSeq<E>{

	public ListaSeqOrd() {
		super();		
	}

	public ListaSeqOrd(int capacidadeInicial, int incremento) {
		super(capacidadeInicial, incremento);		
	}

	public ListaSeqOrd(int capacidadeInicial) {
		super(capacidadeInicial);		
	}

	@Override
	protected int busque(E obj) {
		int inicio = 0, fim = size() - 1, meio, c;
		while (inicio <= fim) {
			meio = (inicio + fim) / 2;
			c = obj.compareTo(lista.elementAt(meio));
			if (c == 0)
				return meio;
			if (c > 0)
				inicio = meio + 1;
			else
				fim = meio - 1;
		}
		return -1;
	}

	@Override
	public boolean add(E obj) {
		// procura a posição correta
		int inicio = 0, fim = size() - 1, meio, c;
		while (inicio <= fim) {
			meio = (inicio + fim) / 2;
			c = obj.compareTo(lista.elementAt(meio));
			if (c > 0)
				inicio = meio + 1;
			else
				fim = meio - 1;
		}
		// achada a posição correta, insere o elemento
		lista.insertAt(inicio, obj);
		return true;
	}
}
