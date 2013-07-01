package utilitarios;

@SuppressWarnings("serial")
public class HashDup<E extends Comparable<E>> extends ColecaoComparavel<E> {

	private E[] tabela;
	private String deletado = " ";

	public class HashEncIterator implements MyIterator<E> {
		int aux;

		@Override
		public E getFirst() {
			if (numItens == 0) {
				return null;
			} else {
				aux = 0;
				while (aux < tabela.length) {
					if (tabela[aux] != null && tabela[aux] != deletado) {
						break;
					} else {
						aux++;
					}
				}
				return tabela[aux];
			}
		}

		@Override
		public E getNext() {
			boolean achou = false;
			int temp = aux + 1;
			while (temp < tabela.length) {
				if (tabela[temp] != null && tabela[temp] != deletado) {
					achou = true;
					break;
				} else {
					temp++;
				}
			}
			if (achou) {
				aux = temp;
				return tabela[temp];
			} else {
				return null;
			}
		}

		@SuppressWarnings("unchecked")
		@Override
		public void remove() {
			tabela[aux] = (E) deletado;
			numItens--;
		}
	}

	@SuppressWarnings("unchecked")
	public HashDup(int tamTabela) {
		tamTabela = nextPrime(tamTabela);
		tabela = (E[]) new Comparable[tamTabela];
	}

	private boolean isPrime(int x) {
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

	private int nextPrime(int x) {
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

	private int funcHash(E obj) {
		return obj.hashCode() % tabela.length;
	}

	private int hash2(E obj) {
		int result = 1 + obj.hashCode() % (tabela.length - 1);
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		tabela = (E[]) new Comparable[tabela.length];
	}

	@Override
	public MyIterator<E> iterator() {
		return new HashEncIterator();
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean remove(E obj) {
		int k = funcHash(obj);
		int incremento = hash2(obj);

		while (tabela[k] != null) {
			if ((tabela[k] != deletado)
					&& (obj.hashCode() == tabela[k].hashCode())) {
				tabela[k] = (E) deletado;
				numItens--;
				return true;
			}
			k = (k + incremento) % tabela.length;
		}
		return false;
	}

	@Override
	public E retrieve(E obj) {
		int k = funcHash(obj);
		int incremento = hash2(obj);

		while (tabela[k] != null) {
			if ((tabela[k] != deletado)
					&& (obj.hashCode() == tabela[k].hashCode())) {
				return (E) tabela[k];
			}
			k = (k + incremento) % tabela.length;
		}
		return null;
	}

	@Override
	public boolean add(E obj) {
		int k = funcHash(obj);
		int inicio = k;
		int incremento = hash2(obj);
		while ((tabela[k] != null) && (tabela[k] != deletado)) {
			if (obj.hashCode() == tabela[k].hashCode()) {
				return false;
			}
			k = (k + incremento) % tabela.length;
		}

		int j = k;

		while (tabela[k] != null) {
			if (k == inicio)
				break;
			if ((tabela[k] != deletado)
					&& (obj.hashCode() == tabela[k].hashCode())) {
				return false;
			}
			k = (k + incremento) % tabela.length;
		}

		tabela[j] = obj;
		numItens++;
		if ((float) numItens / tabela.length >= 0.7f)
			redimensione();
		return true;
	}

	@SuppressWarnings("unchecked")
	private void redimensione() {
		int novoTamanho = tabela.length + tabela.length / 2;
		if (!isPrime(novoTamanho))
			novoTamanho = nextPrime(novoTamanho);

		E[] tabelaAux = tabela;
		tabela = (E[]) new Comparable[novoTamanho];

		for (int i = 0; i < tabelaAux.length; i++) {
			if (tabelaAux[i] != null & tabelaAux[i] != deletado) {
				int k = funcHash(tabelaAux[i]);

				int incremento = hash2(tabelaAux[i]);
				while ((tabela[k] != null)) {
					if (tabelaAux[i].hashCode() == tabela[k].hashCode()) {
						return;
					}
					k = (k + incremento) % tabela.length;
				}

				tabela[k] = tabelaAux[i];
			}
		}
	}

}
