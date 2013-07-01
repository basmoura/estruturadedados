package utilitarios;

//Versao usando a classe ListaEncNaoOrd
@SuppressWarnings("serial")
public class HashEnc<E extends Comparable<E>> extends ColecaoComparavel<E> {

	private ListaEncNaoOrd<E>[] tabela;
	final float carga = 1.75f;

	private class HashEncIterator implements MyIterator<E> {
		private int indice;
		private MyIterator<E> it = tabela[indice].iterator();

		@Override
		public E getFirst() {
			if (numItens == 0)
				return null;
			while (it.getFirst() == null) {
				indice++;
				if (indice == tabela.length)
					return null;
				else
					it = tabela[indice].iterator();
			}
			return it.getFirst();
		}

		@Override
		public E getNext() {
			E obj = it.getNext();
			if (obj == null) {
				indice++;
				if (indice == tabela.length)
					return null;
				it = tabela[indice].iterator();
				while (it.getFirst() == null) {
					indice++;
					if (indice == tabela.length)
						return null;
					else
						it = tabela[indice].iterator();
				}
				obj = it.getFirst();
			}
			return obj;
		}

		@Override
		public void remove() {
			it.remove();
			numItens--;
		}
	}

	protected int funcHash(Object obj) {
		return Math.abs(obj.hashCode()) % tabela.length;
	}

	// Testa se x eh primo
	static boolean isPrime(int x) {
		int divisor;
		if (x < 4)
			return true;

		if ((x % 2) == 0)
			return false;

		divisor = 3;

		while (!((divisor * divisor > x) || (x % divisor == 0)))
			divisor = divisor + 2;

		if (divisor * divisor > x)
			return true;
		else
			return false;
	}

	// Retorna o proximo numero primo maior do que x
	static int nextPrime(int x) {
		if (x < 2)
			return 3;

		if (x % 2 == 0)
			x++;
		else
			x = x + 2;

		while (!isPrime(x))
			x = x + 2;

		return x;

	}

	@SuppressWarnings("unchecked")
	public HashEnc(int tamTabela) {
		if (!isPrime(tamTabela))
			tamTabela = nextPrime(tamTabela);
		tabela = new ListaEncNaoOrd[tamTabela];
		for (int i = 0; i < tabela.length; i++) {
			tabela[i] = new ListaEncNaoOrd<E>();
		}
	}

	@Override
	public boolean add(E obj) {
		if (numItens >= carga * tabela.length)
			redimensione();
		int posicao = funcHash(obj);
		if (tabela[posicao].contains(obj))
			return false;
		tabela[posicao].add(obj);
		numItens++;
		return true;
	}

	@Override
	public boolean remove(E obj) {
		int posicao = funcHash(obj);
		if (tabela[posicao].remove(obj)) {
			numItens--;
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		tabela = new ListaEncNaoOrd[tabela.length];
	}

	@Override
	public MyIterator<E> iterator() {
		return new HashEncIterator();
	}

	@SuppressWarnings({ "unchecked" })
	private void redimensione() {
		int novoTamanho = tabela.length * 2;
		if (!isPrime(novoTamanho))
			novoTamanho = nextPrime(novoTamanho);
		ListaEncNaoOrd<E>[] tabelaAux = tabela;
		tabela = new ListaEncNaoOrd[novoTamanho];
		numItens = 0;
		for (int i = 0; i < tabela.length; i++) {
			tabela[i] = new ListaEncNaoOrd<E>();
		}
		for (int i = 0; i < tabelaAux.length; i++) {
			if (tabelaAux[i].isEmpty())
				continue;
			MyIterator<E> it = tabelaAux[i].iterator();
			E obj = it.getFirst();
			while (obj != null) {
				add(obj);
				obj = it.getNext();
			}
		}
	}

	@Override
	public E retrieve(E obj) {
		int posicao = funcHash(obj);
		return tabela[posicao].retrieve(obj);
	}

}
